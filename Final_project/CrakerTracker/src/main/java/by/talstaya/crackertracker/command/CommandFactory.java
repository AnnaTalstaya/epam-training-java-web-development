package by.talstaya.crackertracker.command;

import by.talstaya.crackertracker.command.impl.*;
import by.talstaya.crackertracker.command.impl.administrator.ChangeUserTypeCommand;
import by.talstaya.crackertracker.command.impl.administrator.DeleteUserCommand;
import by.talstaya.crackertracker.command.impl.administrator.ShowUserDetailsCommand;
import by.talstaya.crackertracker.command.impl.administrator.UserListCommand;
import by.talstaya.crackertracker.command.impl.anonymous.RegistrationCommand;
import by.talstaya.crackertracker.command.impl.anonymous.SignInCommand;
import by.talstaya.crackertracker.command.impl.anonymous.VisitRegistrationCommand;
import by.talstaya.crackertracker.command.impl.anonymous.VisitSignInCommand;
import by.talstaya.crackertracker.command.impl.supervisor.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class is used to correlate the command that came from the request with the corresponding class
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class CommandFactory {

    private static final Logger LOGGER = LogManager.getLogger("name");

    private static CommandFactory instance;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean create = new AtomicBoolean(false);

    private EnumMap<CommandType, Command> commandMap = new EnumMap<CommandType, Command>(CommandType.class);

    private CommandFactory() {
        commandMap.put(CommandType.VISIT_SIGN_IN, new VisitSignInCommand());
        commandMap.put(CommandType.SIGN_IN, new SignInCommand());
        commandMap.put(CommandType.SIGN_OUT, new SignOutCommand());
        commandMap.put(CommandType.VISIT_REGISTRATION, new VisitRegistrationCommand());
        commandMap.put(CommandType.REGISTRATION, new RegistrationCommand());
        commandMap.put(CommandType.TRANSLATE, new TranslateCommand());
        commandMap.put(CommandType.VISIT_SETTINGS, new VisitSettingsCommand());
        commandMap.put(CommandType.SETTINGS, new SettingsCommand());
        commandMap.put(CommandType.ADD_MEAL, new AddMealCommand());
        commandMap.put(CommandType.VISIT_PRODUCT_LIST, new VisitProductListCommand());
        commandMap.put(CommandType.PRODUCT_LIST, new ProductListCommand());
        commandMap.put(CommandType.SEARCH, new SearchCommand());
        commandMap.put(CommandType.SHOW_PRODUCT_DETAILS, new ShowProductDetailsCommand());
        commandMap.put(CommandType.DIET, new DietCommand());
        commandMap.put(CommandType.SHOW_DIET, new ShowDietCommand());
        commandMap.put(CommandType.DELETE_MEAL, new DeleteMealCommand());
        commandMap.put(CommandType.UPDATE_QUANTITY_IN_DIET, new UpdateQuantityInDietCommand());
        commandMap.put(CommandType.USER_LIST, new UserListCommand());
        commandMap.put(CommandType.SHOW_USER_DETAILS, new ShowUserDetailsCommand());
        commandMap.put(CommandType.CHANGE_USER_TYPE, new ChangeUserTypeCommand());
        commandMap.put(CommandType.DELETE_USER, new DeleteUserCommand());
        commandMap.put(CommandType.SHOW_SUPERVISOR, new ShowSupervisorCommand());
        commandMap.put(CommandType.SUPERVISOR_LIST, new SupervisorListCommand());
        commandMap.put(CommandType.SEND_REQUEST_FOR_SUPERVISOR, new SendRequestForSupervisorCommand());
        commandMap.put(CommandType.DELETE_REQUEST_FOR_SUPERVISOR, new DeleteRequestForSupervisorCommand());
        commandMap.put(CommandType.USER_LIST_OF_SUPERVISOR, new UserListOfSupervisorCommand());
        commandMap.put(CommandType.SHOW_REQUESTS_FOR_SUPERVISOR, new ShowRequestsForSupervisorCommand());
        commandMap.put(CommandType.SUPERVISOR_ACCEPTS_REQUEST, new SupervisorAcceptsRequestCommand());
        commandMap.put(CommandType.SUPERVISOR_REJECTS_REQUEST, new SupervisorRejectsRequestCommand());
        commandMap.put(CommandType.DELETE_USER_OF_SUPERVISOR, new DeleteUserOfSupervisorCommand());
        commandMap.put(CommandType.DELETE_SUPERVISOR, new DeleteSupervisorCommand());
        commandMap.put(CommandType.RATE_SUPERVISOR, new RateSupervisorCommand());
        commandMap.put(CommandType.COMMENT, new CommentCommand());
        commandMap.put(CommandType.DELETE_COMMENT, new DeleteCommentCommand());
        commandMap.put(CommandType.USER_DIET_FOR_SUPERVISOR, new UserDietForSupervisorCommand());
        commandMap.put(CommandType.SHOW_USER_DIET_FOR_SUPERVISOR, new ShowUserDietForSupervisorCommand());
    }

    public static CommandFactory getInstance() {
        if (!create.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new CommandFactory();
                    create.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Command receiveCommand(String commandType) {
        Command command = null;

        try {
            if(commandType!=null){
                command = commandMap.get(CommandType.valueOf(commandType.toUpperCase()));
            } else {
                LOGGER.error("commandType is null. Error!!!");
            }
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return command;
    }

    public Command receiveCommand(CommandType commandType) { //factory method
        return commandMap.get(commandType);
    }



}
