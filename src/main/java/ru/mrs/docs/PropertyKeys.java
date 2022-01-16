package ru.mrs.docs;

public enum PropertyKeys {
    DEFAULT_PROF(
            "default.prof" ),
    DEFAULT_USER(
            "default.user" ),
    DEFAULT_PASS(
            "default.pass" ),
    MODULE_NAME(
            "module.name" ),
    DB_USR_NAME(
            "db.usr.name" ),
    DB_USR_PASSWORD(
            "db.usr.password" ),
    DB_DATA_PATH(
            "db.data.path" ),
    SERVER_PORT(
            "server.port" ),
    RESOURCE_BASE(
            "resource.base" ),
    CONTEXT_PATH(
            "context.path" );

    private final String text;

    /**
     * @param text
     */
    PropertyKeys(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }

}
