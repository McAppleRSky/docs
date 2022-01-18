package ru.mrs.docs.frontend;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.mrs.base.service.account.AccountService;
import ru.mrs.docs.Main;
import ru.mrs.docs.service.account.UserProfile;
import ru.mrs.docs.service.db.DBService;
import ru.mrs.docs.service.db.MainService;
import ru.mrs.docs.service.db.dataSet.MainColumns;
import ru.mrs.docs.service.db.dataSet.MainEntity;
import ru.mrs.docs.service.db.dataSet.OldTableColumns;
import ru.mrs.docs.service.db.dataSet.OldTableDataSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainServlet extends HttpServlet implements Servletable {

    private static final Logger LOGGER = LogManager.getLogger(MainServlet.class);

    public static final String PATH_SPEC = "/main";

    private final AccountService accountService = (AccountService) Main.context.get(AccountService.class);

    private final MainService mainService = (MainService) Main.context.get(MainService.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(COMMON_CONTENT_TYPE);
        UserProfile userProfile = (UserProfile) accountService.getUserBySessionId(request.getSession().getId());
        if (userProfile != null) {
            LOGGER.info(AUTHORISED_BEFORE + PATH_SPEC + " method Get");
            List<MainEntity> entities = mainService.findAll();
            entities.sort((a, b) -> b.getId() - a.getId());
            Map<String, Object> data = new HashMap<>();
            data.put("main_entities", entities);
            freemarker.template.Configuration freemarkerConfiguration =
                    (freemarker.template.Configuration) Main.context.get(
                            freemarker.template.Configuration.class);
            try (PrintWriter writer = response.getWriter()) {
                Template template = freemarkerConfiguration.getTemplate("main.ftl");
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

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(COMMON_CONTENT_TYPE);
        UserProfile userProfile = (UserProfile) accountService.getUserBySessionId(request.getSession().getId());
        Map<MainColumns, String> columnToValues;
        if (userProfile != null) {
            columnToValues = new HashMap<>();
            LOGGER.info(AUTHORISED_BEFORE + PATH_SPEC + " method Post");
            for (MainColumns columns : MainColumns.values()) {
                columnToValues.put(columns, request.getParameter(columns.toString()));
            }
            PostVars postVar = columnToValues.get(MainColumns.ID)==null ? PostVars.CREATE : PostVars.UPDATE;
            switch (postVar) {
                case CREATE:
                    int created = mainService.create(columnToValues);
                    LOGGER.info(PATH_SPEC + " created " + created);
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.sendRedirect(PATH_SPEC);
                    break;
                case UPDATE:
                    int opened = mainService.update(columnToValues);
                    LOGGER.warn(PATH_SPEC + " opened " + opened);
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.sendRedirect(PATH_SPEC);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + postVar);
            }

            /*List<MainEntity> entities = mainService.findAll();
*//*
            oldTableDataSets.sort((a, b) -> {
                return a.getId() - b.getId();
            });
*//*
            Map<String, Object> data = new HashMap<>();
            data.put("old_docs_tables", oldTableDataSets);
            freemarker.template.Configuration freemarkerConfiguration =
                    (freemarker.template.Configuration) Main.context.get(
                            freemarker.template.Configuration.class);

            Template template = null;
            try (PrintWriter writer = response.getWriter()) {
                template = freemarkerConfiguration.getTemplate("oldtable.ftl");
                response.setContentType(COMMON_CONTENT_TYPE);
                response.setStatus(HttpServletResponse.SC_OK);
                template.process(data, writer);
            } catch (TemplateException e) {
                e.printStackTrace();
            }*/
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.sendRedirect(REDIR_ROOT);
            LOGGER.warn(UNAUTHORISED_NOW + PATH_SPEC);
        }
    }

}

enum PostVars {
    CREATE, UPDATE
}
