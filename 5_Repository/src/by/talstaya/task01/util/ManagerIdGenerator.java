package by.talstaya.task01.util;

public class ManagerIdGenerator {
    private static long lastId = -1;

    public static long generateId() {
        return ++lastId;
    }
}
