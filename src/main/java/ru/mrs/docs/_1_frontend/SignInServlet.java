package ru.mrs.docs._1_frontend;

import ru.mrs.docs._2_service.AccountServiceFake;
import ru.mrs.docs.base.service.account.AccountService;
import ru.mrs.docs.main.Main;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet implements Servletable{

    public static final String URL = "/signin";
    private final AccountService accountService = (AccountService)Main.context.get(AccountServiceFake.class);

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
