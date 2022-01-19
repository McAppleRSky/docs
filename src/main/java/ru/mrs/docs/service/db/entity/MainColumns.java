package ru.mrs.docs.service.db.entity;

public enum MainColumns {

    ID(
            "id" ),
    URL_INPUT(
            "url_input" ),
    GEN_ORG_NUMB(
            "gen_org_numb" ),
    GEN_ORG_DATE(
            "gen_org_date" ),
    OUTPUT_NUMB(
            "output_numb" ),
    OUTPUT_DATE(
            "output_date" ),
    FROM_OWNER(
            "from_owner" ),
    INPUT_DATE(
            "input_date" ),
    INPUT_NUMB(
            "input_numb" ),
    WORKER(
            "worker" ),
    HAND_PASS(
            "hand_pass" ),
    ANSWER_COMP(
            "answer_comp" ),
    ANSWER_DATE(
            "answer_date" ),
    ANSWER_NUMB(
            "answer_numb" ),
    URL_OUTPUT(
            "url_output" ),
    NOTE(
            "note" );

    private final String text;

    /**
     * @param text
     */
    MainColumns(final String text) {
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
