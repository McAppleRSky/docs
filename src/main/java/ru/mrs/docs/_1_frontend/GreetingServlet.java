package ru.mrs.docs._1_frontend;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.mrs.docs.main.ConfigHide;
import ru.mrs.docs.main.Main;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GreetingServlet extends HttpServlet implements Servletable{

    private static final Logger LOGGER = LogManager.getLogger(GreetingServlet.class);

    public static final String URL = "/*";
    private final String moduleName = ((ConfigHide) Main.context.get(ConfigHide.class)).getMODULE_NAME();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Configuration freemarkerConfiguration = (Configuration) Main.context.get(Configuration.class);
        Template template = null;
//        String moduleName = ((ConfigHide) Main.context.get(ConfigHide.class)).getMODULE_NAME();
        Map<String, String> data = new HashMap<>();
        data.put("moduleName", moduleName);
        try ( PrintWriter writer = response.getWriter() ) {
            template = freemarkerConfiguration.getTemplate("greeting.ftl");
            response.setContentType(COMMON_CONTENT_TYPE);
            response.setStatus(HttpServletResponse.SC_OK);
            template.process(data, writer);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

}
