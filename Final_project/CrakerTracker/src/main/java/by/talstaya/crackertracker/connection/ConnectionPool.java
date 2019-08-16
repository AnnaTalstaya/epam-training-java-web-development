package by.talstaya.crackertracker.connection;

import by.talstaya.crackertracker.exception.PoolConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class is used to store, give and receive back connections
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger("name");

    private static ConnectionPool instance;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean create = new AtomicBoolean(false);

    private static final int MAX_POOL_SIZE;
    private static final String CONNECTION_URL;

    private BlockingQueue<ProxyConnection> availableConnections;
    private BlockingQueue<ProxyConnection> usedConnections;

    static {
        MAX_POOL_SIZE = ConnectorDb.obtainMaxPoolSize();
        CONNECTION_URL = ConnectorDb.obtainConnectionURL();
    }

    private ConnectionPool() throws PoolConnectionException {
        try {
            Class.forName(ConnectorDb.obtainDriver()); //register driver

            init();

        } catch (ClassNotFoundException e) {
            throw new PoolConnectionException(e);
        }
    }

    private void init() throws PoolConnectionException {
        availableConnections = new LinkedBlockingQueue<>(MAX_POOL_SIZE);
        usedConnections = new LinkedBlockingQueue<>(MAX_POOL_SIZE);

        for (int i = 0; i < MAX_POOL_SIZE; i++) {
            try {
                availableConnections.put(new ProxyConnection(DriverManager.getConnection(CONNECTION_URL, ConnectorDb.obtainProperties())));
            } catch (InterruptedException | SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        if (availableConnections.isEmpty()) {
            LOGGER.fatal("There is no connection in the pool.");
            throw new PoolConnectionException("There is no connection in the pool.");
        }

        startCheckingTimer();
    }

    /**
     * This method checks every period if connections are lost
     */
    private void startCheckingTimer() {

        TimerTask check = new TimerTask() {
            public void run() {
                if (availableConnections.size() + usedConnections.size() != MAX_POOL_SIZE) {
                    LOGGER.error("Lost " + (MAX_POOL_SIZE - availableConnections.size() - usedConnections.size()) + " connections in total.");
                }
            }
        };

        Timer timer = new Timer("Timer");

        final long delay = 0L;
        final long period = 60000L;
        timer.scheduleAtFixedRate(check, delay, period);
    }

    public static ConnectionPool getInstance() {
        if (!create.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    create.set(true);
                }
            } catch (PoolConnectionException e) {
                LOGGER.fatal(e.getMessage(), e);
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    /**
     * This method extract connection from availableConnections and put it into usedConnections
     * @return extracted connection
     */
    public Connection takeConnection() {

        ProxyConnection connection = null;

        lock.lock();
        try {
            connection = availableConnections.take();
            usedConnections.put(connection);

            LOGGER.debug("================= Take =================");
            LOGGER.debug("Available connections = " + availableConnections.size());
            LOGGER.debug("Used connections = " + usedConnections.size());
            LOGGER.debug("MAX_POOL_SIZE = " + MAX_POOL_SIZE);

        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
            Thread.currentThread().interrupt();
        }
        lock.unlock();

        return connection;
    }

    /**
     * This method removes connection from usedConnections and put it into availableConnections
     * @param connection that should be returned to availableConnections
     */
    public void returnConnection(Connection connection) {
        try {
            if (connection instanceof ProxyConnection) {
                ProxyConnection proxyConnection = (ProxyConnection) connection;
                proxyConnection.setAutoCommit(true);

                usedConnections.remove(proxyConnection);
                availableConnections.offer(proxyConnection);

                LOGGER.debug("================= Return =================");
                LOGGER.debug("Available connections = " + availableConnections.size());
                LOGGER.debug("Used connections = " + usedConnections.size());
                LOGGER.debug("MAX_POOL_SIZE = " + MAX_POOL_SIZE);
            } else {
                LOGGER.error("Connection does not belong to this pool");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);

            try {
                connection.close();
            } catch (SQLException e1) {
                LOGGER.error(e1.getMessage(), e1);
            }
        }
    }

    /**
     * This method close all connection from pool and deregister driver
     */
    public void closePool() throws PoolConnectionException {
        try {
            lock.lock();
            for (int i = 0; i < MAX_POOL_SIZE; i++) {
                ProxyConnection connection = null;

                try {
                    connection = availableConnections.take();
                    connection.realClose();

                    DriverManager.deregisterDriver(DriverManager.getDriver(ConnectorDb.obtainDriver()));
                } catch (InterruptedException e) {
                    LOGGER.error(e.getMessage(), e);
                    Thread.currentThread().interrupt();
                } catch (SQLException e) {
                    throw new PoolConnectionException("There's a problem with connection closing.", e);
                }
            }
        } finally {
            lock.unlock();
        }
    }

}
