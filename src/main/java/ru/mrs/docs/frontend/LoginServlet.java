package ru.mrs.docs.frontend;

import ru.mrs.docs.Main;
import ru.mrs.base.service.account.AccountService;
import ru.mrs.docs.service.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet implements Servletable
{

    public static final String URL = "/login";

    private final AccountService accountService = (AccountService)Main.context.get(AccountService.class);

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        if (login == null || pass == null) {
            response.setContentType(COMMON_CONTENT_TYPE);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.sendRedirect("redirect:/greeting");
            return;
        }

        UserProfile profile = (UserProfile) accountService.getUserByLogin(login);
        if ( profile == null || !profile.getPass().equals(pass) ) {
//            response.setContentType(COMMON_CONTENT_TYPE);
//            response.getWriter().println("Unauthorized");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.sendRedirect("redirect:/greeting");
            return;
        }
        accountService.addSession(request.getSession().getId(), profile);
        /*Gson gson = new Gson();
        String json = gson.toJson(profile);*/
        response.setContentType(COMMON_CONTENT_TYPE);
        response.getWriter().println("Authorized: "+profile.getLogin()
                //json
        );
        //response.getWriter().println(CALL_BACK_HTEXPR);
        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("redirect:/main");

    }

}
