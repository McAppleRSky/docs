package ru.mrs.docs._1_frontend;

import java.io.*;
import java.net.URL;
import java.util.Map;
import java.util.HashMap;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class TemplateTest {

    @Test
    void doResource() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL template = classLoader.getResource("templates");
        assertNotNull(template);
        classLoader = this.getClass().getClassLoader();
        template = classLoader.getResource("templates/test.ftl");
        assertNotNull(template);
        classLoader = TestTemplate.class.getClassLoader();
        template = classLoader.getResource("templates/test.ftl");
        assertNotNull(template);
    }

    @Test
    void doTemplate() {
        // Конфигурация
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_27);
        URL templates = this.getClass().getClassLoader().getResource("templates");
        // модель данных
        Map<String, Object> data = new HashMap<>();
        String key = "name", value = "testValue",
                expected = "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\">"
         + "<title>testValue!</title></head><body><h2>Hello testValue!</h2></body></html>";
        data.put(key, value);
        // шаблон
        Template template = null;
        try ( StringWriter stringWriter = new StringWriter() ) {
            configuration.setDirectoryForTemplateLoading(new File(templates.getPath()));
            template = configuration.getTemplate("test.ftl");
            template.process(data, stringWriter);
            assertEquals(expected, stringWriter.toString());
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

}
