package ru.mrs.docs.service.db.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

class EntityBehaviour {

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    @Test
    void dateNullTest() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        Map<MainColumns, String> mainColumnToValue = new HashMap<>();
        mainColumnToValue.put(MainColumns.OUTPUT_DATE, "");
        IMainEntity entity = new MainEntityImpl();
        try {
            entity.setOutputDate(
                    mainColumnToValue.get(MainColumns.OUTPUT_DATE).isEmpty() ? null :
                            simpleDateFormat.parse(
                                    mainColumnToValue.get(
                                            MainColumns.OUTPUT_DATE) ) );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Assertions.assertNull(entity.getOutputDate());
    }

}