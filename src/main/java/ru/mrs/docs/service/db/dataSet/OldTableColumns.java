package ru.mrs.docs.service.db.dataSet;

public enum OldTableColumns {
    ID(
            "ID" ),
    URL_SED_INPUT(
            "URL_SED_INPUT" ),
    GENERAL_ORG_NUMB(
            "GENERAL_ORG_NUMB" ),
    GENERAL_ORG_DATE(
            "GENERAL_ORG_DATE" ),
    OUTPUT_NUMB(
            "OUTPUT_NUMB" ),
    OUTPUT_DATE(
            "OUTPUT_DATE" ),
    FROM_OWNER(
            "FROM_OWNER" ),
    INPUT_DATE(
            "INPUT_DATE" ),
    INPUT_NUMB(
            "INPUT_NUMB" ),
    WORKER(
            "WORKER" ),
    HAND_PASS(
            "HAND_PASS" ),
    ANSWER_COMPLETE(
            "ANSWER_COMPLETE" ),
    ANSWER_DATE(
            "ANSWER_DATE" ),
    ANSWER_NUMB(
            "ANSWER_NUMB" ),
    URL_SED_OUTPUT(
            "URL_SED_OUTPUT" ),
    NOTE(
            "NOTE" );

    private final String text;

    /**
     * @param text
     */
    OldTableColumns(final String text) {
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
