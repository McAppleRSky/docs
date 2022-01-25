package ru.mrs.docs.service.account;

public enum AuthState {

    AUTH(                         // auth
            "Authorized" ),
    UNAUTH(                      // un auth
            "Unauthorized" );

    private final String text;

    /**
     * @param text
     */
    AuthState(final String text) {
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
