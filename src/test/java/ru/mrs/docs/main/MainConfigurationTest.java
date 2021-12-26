package ru.mrs.docs.main;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class MainConfigurationTest {

    @Test
    void getResource() {
        String actual = null;
        Properties properties = new Properties();
        try (InputStream input = MainConfigurationTest.class.getClassLoader().getResourceAsStream("testConfig/test.properties")) {
            properties.load(input);
            actual = properties.getProperty("test");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertNotNull(actual);
        assertEquals("test1", actual);
    }

}
