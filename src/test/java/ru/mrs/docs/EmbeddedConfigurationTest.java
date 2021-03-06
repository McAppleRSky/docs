package ru.mrs.docs;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class EmbeddedConfigurationTest {

    @Test
    void getResource() {
        String actual = null;
        Properties properties = new Properties();
        try (InputStream input = EmbeddedConfigurationTest.class.getClassLoader().getResourceAsStream("testConfig/test.properties")) {
            properties.load(input);
            actual = properties.getProperty("test");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertNotNull(actual);
        assertEquals("test1", actual);
    }

}
