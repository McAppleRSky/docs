package ru.mrs.docs.service.db.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class MainHelper {

    private static final Logger LOGGER = LogManager.getLogger(MainHelper.class);

    public static MainEntity toEntity (Map<MainColumns, String> mainColumnToValues) {
//        LOGGER.info("get(MainColumns.ID) " + mainColumnToValues.get(MainColumns.ID));
        return new MainEntity()
                .setId(
                        mainColumnToValues.get(MainColumns.ID) == null ? null : Long.valueOf(
                                mainColumnToValues.get(
                                        MainColumns.ID ) ) )
                .setUrlInput(
                        mainColumnToValues.get(
                                MainColumns.URL_INPUT) )
                .setGenOrgNumb(
                        mainColumnToValues.get(
                                MainColumns.GEN_ORG_NUMB) )
                /*.setGenOrgDate(
                        mainColumnToValues.get(
                                MainColumns.GEN_ORG_DATE
                        )
                )*/
                .setOutputNumb(
                        mainColumnToValues.get(
                                MainColumns.OUTPUT_NUMB ) )
                .setFromOwner(
                        mainColumnToValues.get(
                                MainColumns.FROM_OWNER ) )
                /*.setInputDate(
                        mainColumnToValues.get(
                                MainColumns.INPUT_DATE ) )*/
                .setInputNumb(
                        mainColumnToValues.get(
                                MainColumns.INPUT_NUMB
                        )
                )
                /*.setOutputDate(
                        mainColumnToValues.get(
                                MainColumns.OUTPUT_DATE
                        )
                )*/
                .setWorker(
                        mainColumnToValues.get(
                                MainColumns.WORKER ) )
                /*.setHandPass(
                        mainColumnToValues.get(
                                MainColumns.HAND_PASS ) )
                .setAnswerComp(
                        mainColumnToValues.get(
                                MainColumns.ANSWER_COMP
                        )
                )
                .setAnswerDate(
                        mainColumnToValues.get(
                                MainColumns.ANSWER_DATE ) )*/
                .setAnswerNumb(
                        mainColumnToValues.get(
                                MainColumns.ANSWER_NUMB ) )
                .setUrlOutput(
                        mainColumnToValues.get(
                                MainColumns.URL_OUTPUT ) )
                .setNote(
                        mainColumnToValues.get(
                                MainColumns.NOTE ) );
    }

    /*public static Map<String, String> columns () {
        Map<String, String> result = new HashMap<>();
        for (MainColumns column : MainColumns.values()) {
            result.put(column.name(), column.toString());}
        return result;}*/

}
