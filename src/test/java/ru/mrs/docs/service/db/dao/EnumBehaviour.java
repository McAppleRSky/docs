package ru.mrs.docs.service.db.dao;

import org.junit.jupiter.api.Test;
import ru.mrs.docs.service.db.entity.MainColumns;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnumBehaviour {

    @Test
    void enumTest() {
        MainColumns column = MainColumns.ID;
        String actualString = column.toString();
        String actualName = column.name();
        assertEquals("id", actualString);
        assertEquals("ID", actualName);
    }

}
