package ru.mrs.docs._1_frontend;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import ru.mrs.docs.main.ConfigHide;
import ru.mrs.docs.main.Main;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MainServlet extends HttpServlet {

    public static final String URL = "/*";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Configuration freemarkerConfiguration = (Configuration) Main.context.get(Configuration.class);
        Template template = null;
        String moduleName = ((ConfigHide) Main.context.get(ConfigHide.class)).getMODULE_NAME();
        Map<String, String> data = new HashMap<>();
        data.put("module.name", moduleName);
        try ( PrintWriter writer = response.getWriter() ) {
            template = freemarkerConfiguration.getTemplate("greeting.ftl");
            response.setStatus(HttpServletResponse.SC_OK);
            template.process(data, writer);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

}
