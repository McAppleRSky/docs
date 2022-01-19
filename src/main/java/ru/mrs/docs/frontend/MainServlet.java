package ru.mrs.docs.frontend;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.mrs.base.service.account.AccountService;
import ru.mrs.docs.Main;
import ru.mrs.docs.service.account.UserProfile;
import ru.mrs.docs.service.db.MainService;
import ru.mrs.docs.service.db.entity.MainColumns;
import ru.mrs.docs.service.db.entity.MainEntity;
import ru.mrs.docs.service.db.entity.MainHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
            List<MainEntity> entities = mainService.getAll();
            entities.sort((a, b) -> (int) (b.getId() - a.getId()));
            Map<String, Object> data = new HashMap<>();
            data.put("main_values", entities);
            data.put("col_names", MainColumns.getNames());
            data.put("col_texts", MainColumns.getTexts());
            data.put("col_beautify", Main.context.get(MainColumns.class));
            freemarker.template.Configuration freemarkerConfiguration =
                    (freemarker.template.Configuration) Main.context.get(
                            freemarker.template.Configuration.class );
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
        if (userProfile != null) {
            LOGGER.info(AUTHORISED_BEFORE + PATH_SPEC + " method Post");
            MainEntity entity = MainHelper.toEntity(mapByColumns(request));
            /*Map<MainColumns, String> mainColumnToValues = Helper.fetchByEnum(request);
            PostVars postVar = mainColumnToValues.get(MainColumns.ID)==null ? PostVars.CREATE : PostVars.UPDATE;*/
            StoreMethod storeMethod = entity.getId() == null ? StoreMethod.CREATE : StoreMethod.UPDATE;
            switch (storeMethod) {
                case CREATE:
                    mainService.add(entity);
                    LOGGER.warn(PATH_SPEC + " created");
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.sendRedirect(PATH_SPEC);
                    break;
                case UPDATE:
                    mainService.update(entity);
                    LOGGER.warn(PATH_SPEC + " updated");
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.sendRedirect(PATH_SPEC);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + storeMethod);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.sendRedirect(REDIR_ROOT);
            LOGGER.warn(UNAUTHORISED_NOW + PATH_SPEC);
        }
    }

    Map<MainColumns, String> mapByColumns (HttpServletRequest request) {
        Map<MainColumns, String> result = new HashMap<>();
        for (MainColumns columns : MainColumns.values()) {
            result.put(columns, request.getParameter(columns.name()));
        }
        return result;
    }

}

enum StoreMethod {
    CREATE, UPDATE
}
