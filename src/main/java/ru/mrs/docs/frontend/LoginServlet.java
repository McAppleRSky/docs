package ru.mrs.docs.frontend;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.mrs.docs.Main;
import ru.mrs.base.service.account.AccountService;
import ru.mrs.docs.service.account.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
@SuppressWarnings("unchecked")
public class LoginServlet extends HttpServlet implements Servletable{

    public static final String PATH_SPEC = "/login";

    private static final Logger LOGGER = LogManager.getLogger(LoginServlet.class);

    private final AccountService accountService = (AccountService)Main.context.get(AccountService.class);

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(COMMON_CONTENT_TYPE);
        UserProfile userProfile = (UserProfile) accountService.getUserBySessionId(request.getSession().getId());
        if (userProfile!=null ) {
            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                if (parameterNames.nextElement().equals("unauthorise")){
                    accountService.deleteSession(request.getSession().getId());
                }
            }
        }
        response.setStatus(HttpServletResponse.SC_OK);
        LOGGER.info(UNAUTHORISED_NOW);
        response.sendRedirect(REDIR_ROOT);
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(COMMON_CONTENT_TYPE);
        UserProfile userProfile = (UserProfile) accountService.getUserBySessionId(request.getSession().getId());
        if (userProfile!=null) {
            response.setStatus(HttpServletResponse.SC_OK);
            LOGGER.info(AUTHORISED_BEFORE + PATH_SPEC);
            response.sendRedirect(REDIR_ROOT + "greeting");
//            return;
        } else {
            String login = request.getParameter("login");
            String pass = request.getParameter("password");
            if (login == null || pass == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                LOGGER.warn("login params is null");
                response.sendRedirect(REDIR_ROOT);
//                return;
            } else {
                userProfile = (UserProfile) accountService.getUserByLogin(login);
                if ( userProfile == null || !userProfile.getPass().equals(pass) ) {
                    response.getWriter().println("Unauthorized");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    LOGGER.warn("login or password is incorrect. Profile equal null ? " + userProfile==null);
                    response.sendRedirect(REDIR_ROOT);
//                    return;
                } else {
                    accountService.addSession(request.getSession().getId(), userProfile);
//                response.getWriter().println("Authorized: "+profile.getLogin());
                    //response.getWriter().println(CALL_BACK_HTEXPR);
                    response.setStatus(HttpServletResponse.SC_OK);
                    LOGGER.info("User authorised now");
                    response.sendRedirect(REDIR_ROOT + "greeting");
//                    return;
                }
            }
        }
    }

}
