package ru.mrs.docs.frontend;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.mrs.base.service.account.AccountService;
import ru.mrs.docs.Main;
import ru.mrs.docs.service.account.UserProfile;
import ru.mrs.docs.service.db.DBService;
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

public class OldTableServlet extends HttpServlet implements Servletable {

    private static final Logger LOGGER = LogManager.getLogger(OldTableServlet.class);

    public static final String PATH_SPEC = "/oldtable";

    private final AccountService accountService = (AccountService) Main.context.get(AccountService.class);

    private final DBService dbService = (DBService) Main.context.get(DBService.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(COMMON_CONTENT_TYPE);
        UserProfile userProfile = (UserProfile) accountService.getUserBySessionId(request.getSession().getId());
        if (userProfile != null) {
            LOGGER.info(AUTHORISED_BEFORE + PATH_SPEC + " method Get");
            List<OldTableDataSet> oldTableDataSets = dbService.allDocs();
            oldTableDataSets.sort((a, b) -> b.getId() - a.getId());
            Map<String, Object> data = new HashMap<>();
            data.put("old_docs_tables", oldTableDataSets);
            freemarker.template.Configuration freemarkerConfiguration =
                    (freemarker.template.Configuration) Main.context.get(
                            freemarker.template.Configuration.class);
            try (PrintWriter writer = response.getWriter()) {
                Template template = freemarkerConfiguration.getTemplate("oldtable.ftl");
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
        Map<OldTableColumns, String> columnToValues = new HashMap<>();
        if (userProfile != null) {
            LOGGER.info(AUTHORISED_BEFORE + PATH_SPEC + " method Post");
            for (OldTableColumns oldTableColumns : OldTableColumns.values()) {
                columnToValues.put(oldTableColumns, request.getParameter(oldTableColumns.toString()));
            }
            PostVars postVar = columnToValues.get(OldTableColumns.ID)==null ? PostVars.CREATE : PostVars.UPDATE;
            switch (postVar) {
                case CREATE:
                    int created = dbService.createDocsOldTable(columnToValues);
                    LOGGER.info(PATH_SPEC + " created " + created);
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.sendRedirect(PATH_SPEC);
                    break;
                case UPDATE:
                    int opened = dbService.updateOldTable(columnToValues);
                    LOGGER.warn(PATH_SPEC + " opened " + opened);
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.sendRedirect(PATH_SPEC);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + postVar);
            }

            List<OldTableDataSet> oldTableDataSets = dbService.allDocs();
/*
            oldTableDataSets.sort((a, b) -> {
                return a.getId() - b.getId();
            });
*/
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
            }
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
