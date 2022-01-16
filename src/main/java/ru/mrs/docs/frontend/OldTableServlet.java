package ru.mrs.docs.frontend;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.mrs.base.service.account.AccountService;
import ru.mrs.docs.Main;
import ru.mrs.docs.PropertyKeys;
import ru.mrs.docs.service.account.UserProfile;
import ru.mrs.docs.service.db.DBService;
import ru.mrs.docs.service.db.DBServiceImpl;
import ru.mrs.docs.service.db.dao.OldTableDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class OldTableServlet extends HttpServlet implements Servletable{

    private static final Logger LOGGER = LogManager.getLogger(OldTableServlet.class);

    public static final String PATH_SPEC = "/oldtable";
    
    private final AccountService accountService = (AccountService)Main.context.get(AccountService.class);

    private final DBService dbService = new DBServiceImpl(null,null,null);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(COMMON_CONTENT_TYPE);
        UserProfile userProfile = (UserProfile) accountService.getUserBySessionId(request.getSession().getId());
        if (userProfile!=null) {
            LOGGER.info(AUTHORISED_BEFORE + PATH_SPEC);
            dbService.allDocuments();
            Configuration freemarkerConfiguration = (Configuration) Main.context.get(Configuration.class);


            Template template = null;
            Map<String, String> data = new HashMap<>();
            data.put("login", userProfile.getLogin());
//            data.put(PropertyKeys.module_name.toString(), moduleName);
            try ( PrintWriter writer = response.getWriter() ) {
                template = freemarkerConfiguration.getTemplate("greeting.ftl");
                response.setContentType(COMMON_CONTENT_TYPE);
                response.setStatus(HttpServletResponse.SC_OK);
                template.process(data, writer);
            } catch (TemplateException e) {
                e.printStackTrace();
            }

        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.sendRedirect(REDIR_ROOT);
            LOGGER.warn(UNAUTHORISED_NOW + PATH_SPEC);
        }




        Configuration freemarkerConfiguration = (Configuration) Main.context.get(Configuration.class);
        Template template = null;
//        String moduleName = ((ConfigHide) Main.context.get(ConfigHide.class)).getMODULE_NAME();
        Map<String, String> data = new HashMap<>();
//        data.put("moduleName", moduleName);
        try ( PrintWriter writer = response.getWriter() ) {
            template = freemarkerConfiguration.getTemplate("main.ftl");
            response.setContentType(COMMON_CONTENT_TYPE);
            response.setStatus(HttpServletResponse.SC_OK);
            template.process(data, writer);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

}
