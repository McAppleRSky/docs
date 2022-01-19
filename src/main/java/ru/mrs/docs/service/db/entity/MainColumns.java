package ru.mrs.docs.service.db.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum MainColumns {

    ID(                             // 0
            "id" ),
    URL_INPUT(                      // 1
            "url_input" ),
    GEN_ORG_NUMB(                   // 2
            "gen_org_numb" ),
    GEN_ORG_DATE(                   // 3
            "gen_org_date" ),
    OUTPUT_NUMB(                    // 4
            "output_numb" ),
    OUTPUT_DATE(                    // 5
            "output_date" ),
    FROM_OWNER(                     // 6
            "from_owner" ),
    INPUT_DATE(                     // 7
            "input_date" ),
    INPUT_NUMB(                     // 8
            "input_numb" ),
    WORKER(                         // 9
            "worker" ),
    HAND_PASS(                      // 10
            "hand_pass" ),
    ANSWER_COMP(                    // 11
            "answer_comp" ),
    ANSWER_DATE(                    // 12
            "answer_date" ),
    ANSWER_NUMB(                    // 13
            "answer_numb" ),
    URL_OUTPUT(                     // 14
            "url_output" ),
    NOTE(                           // 15
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

    private static List<String> names;
    private static List<String> texts;
    private static Map<String, String> mapNameText;
    private static Map<MainColumns, String> beautify;

    public static Map<String, String> mapNameText () {
        if (mapNameText==null) {
            mapNameText = new HashMap<>();
            for (MainColumns column : MainColumns.values()) {
                mapNameText.put(column.name(), column.toString());
            }
        }
        return mapNameText;
    }

    public static List<String> getNames () {
        if (names==null) {
            names = new ArrayList<>();
            for (MainColumns column : MainColumns.values()) {
                names.add(column.name());
            }
        }
        return names;
    }

    public static List<String> getTexts () {
        if (texts==null) {
            texts = new ArrayList<>();
            for (MainColumns column : MainColumns.values()) {
                texts.add(column.toString());
            }
        }
        return texts;
    }

    public static Map<MainColumns, String> getBeautify() {
        return beautify;
    }

    public static void setBeautify (Map<MainColumns, String> beautified) {
        beautify = beautified;
    }

}
