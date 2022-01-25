package ru.mrs.docs.frontend;

import ru.mrs.base.service.account.AccountService;
import ru.mrs.docs.service.account.AuthState;
import ru.mrs.docs.service.account.UserProfile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class ParentServlet extends HttpServlet implements Servletable{

    protected final Map<Object, Object> context;

    protected AccountService accountService;

    protected UserProfile userProfile;

    public ParentServlet(Map<Object, Object> context) {
        this.context = context;
        accountService = (AccountService)context.get(AccountService.class);
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userProfile = (UserProfile) accountService.getUserBySessionId(request.getSession().getId());
        if (userProfile == null) {
            throw new NullPointerException(AuthState.UNAUTH.toString());
        }
    }

}
