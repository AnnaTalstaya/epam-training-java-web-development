package by.talstaya.crackertracker.command;

import by.talstaya.crackertracker.entity.UserType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This enum contains all possible command than can come from a request
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public enum CommandType {
    VISIT_SIGN_IN (Collections.singletonList(UserType.ANONYMOUS)),
    SIGN_IN (Collections.singletonList(UserType.ANONYMOUS)),
    SIGN_OUT (Arrays.asList(UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR)),
    VISIT_REGISTRATION (Collections.singletonList(UserType.ANONYMOUS)),
    REGISTRATION (Collections.singletonList(UserType.ANONYMOUS)),
    TRANSLATE (Arrays.asList(UserType.ANONYMOUS, UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR)),
    VISIT_SETTINGS (Arrays.asList(UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR)),
    SETTINGS (Arrays.asList(UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR)),
    ADD_MEAL (Arrays.asList(UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR)),
    VISIT_PRODUCT_LIST (Arrays.asList(UserType.ANONYMOUS, UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR)),
    PRODUCT_LIST (Arrays.asList(UserType.ANONYMOUS, UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR)),
    SEARCH (Arrays.asList(UserType.ANONYMOUS, UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR)),
    SHOW_PRODUCT_DETAILS (Arrays.asList(UserType.ANONYMOUS, UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR)),
    DIET (Arrays.asList(UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR)),
    SHOW_DIET (Arrays.asList(UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR)),
    DELETE_MEAL (Arrays.asList(UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR)),
    UPDATE_QUANTITY_IN_DIET (Arrays.asList(UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR)),
    USER_LIST (Collections.singletonList(UserType.ADMINISTRATOR)),
    SHOW_USER_DETAILS (Arrays.asList(UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR)),
    CHANGE_USER_TYPE (Collections.singletonList(UserType.ADMINISTRATOR)),
    DELETE_USER (Collections.singletonList(UserType.ADMINISTRATOR)),
    SHOW_SUPERVISOR (Arrays.asList(UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR)),
    SUPERVISOR_LIST (Arrays.asList(UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR)),
    SEND_REQUEST_FOR_SUPERVISOR (Arrays.asList(UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR)),
    DELETE_REQUEST_FOR_SUPERVISOR (Arrays.asList(UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR)),
    USER_LIST_OF_SUPERVISOR (Collections.singletonList(UserType.SUPERVISOR)),
    SHOW_REQUESTS_FOR_SUPERVISOR (Collections.singletonList(UserType.SUPERVISOR)),
    SUPERVISOR_ACCEPTS_REQUEST (Collections.singletonList(UserType.SUPERVISOR)),
    SUPERVISOR_REJECTS_REQUEST (Collections.singletonList(UserType.SUPERVISOR)),
    DELETE_USER_OF_SUPERVISOR (Collections.singletonList(UserType.SUPERVISOR)),
    DELETE_SUPERVISOR (Arrays.asList(UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR)),
    RATE_SUPERVISOR (Arrays.asList(UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR)),
    COMMENT (Arrays.asList(UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR)),
    DELETE_COMMENT (Arrays.asList(UserType.USER, UserType.SUPERVISOR, UserType.ADMINISTRATOR)),
    USER_DIET_FOR_SUPERVISOR (Collections.singletonList(UserType.SUPERVISOR)),
    SHOW_USER_DIET_FOR_SUPERVISOR (Collections.singletonList(UserType.SUPERVISOR));

    /**
     * userTypeList contains all types of users that can use corresponding command
     */
    private List<UserType> userTypeList;

    CommandType(List<UserType> userTypeList) {
        this.userTypeList = userTypeList;
    }

    public List<UserType> getUserTypeList() {
        return userTypeList;
    }}
