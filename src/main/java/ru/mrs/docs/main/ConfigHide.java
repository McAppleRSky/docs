package ru.mrs.docs.main;

public class ConfigHide {
    private final String MODULE_NAME;
    private final String USER_NAME;
    private final String USER_PASSWORD;
    private final String DB_USR_NAME;
    private final String DB_USR_PASSWORD;

    ConfigHide(String module_name, String user_name, String user_password, String db_usr_name, String db_usr_password) {
        MODULE_NAME = module_name;
        USER_NAME = user_name;
        USER_PASSWORD = user_password;
        DB_USR_NAME = db_usr_name;
        DB_USR_PASSWORD = db_usr_password;
    }

    public String getMODULE_NAME() {
        return MODULE_NAME;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public String getUSER_PASSWORD() {
        return USER_PASSWORD;
    }

    public String getDB_USR_NAME() {
        return DB_USR_NAME;
    }

    public String getDB_USR_PASSWORD() {
        return DB_USR_PASSWORD;
    }

}
