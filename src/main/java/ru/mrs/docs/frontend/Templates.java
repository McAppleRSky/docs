package ru.mrs.docs.frontend;

public enum Templates {
    GREETING(
            "greeting.ftl" ),;
    private final String text;

    /**
     * @param text
     */
    Templates(final String text) {
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
