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

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GreetingServlet extends HttpServlet implements Servletable{

    private static final Logger LOGGER = LogManager.getLogger(GreetingServlet.class);

    public static final String PATH_SPEC = "/greeting";

    private final String moduleName = Main.context.get(PropertyKeys.MODULE_NAME).toString();

    private final AccountService accountService = (AccountService)Main.context.get(AccountService.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(COMMON_CONTENT_TYPE);
        UserProfile userProfile = (UserProfile) accountService.getUserBySessionId(request.getSession().getId());
        if (userProfile!=null) {
            LOGGER.info(AUTHORISED_BEFORE + PATH_SPEC);
            Configuration freemarkerConfiguration = (Configuration) Main.context.get(Configuration.class);
            Template template = null;
            Map<String, String> data = new HashMap<>();
            data.put("login", userProfile.getLogin());
            data.put(PropertyKeys.MODULE_NAME.toString(), moduleName);
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
    }

}
