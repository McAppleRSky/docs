package ru.mrs.docs.frontend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mrs.base.service.account.AccountService;
import ru.mrs.docs.service.account.UserProfile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class GreetingServletTest {

    private Map context;

    private AccountService accountServiceMock;
    private HttpServletRequest requestMock;
    private HttpServletResponse responseMock;
    private HttpSession httpSessionMock;
    private UserProfile userProfileMock;
    private GreetingServlet greetingServletSpy;

    @BeforeEach
    void prepare() {
        context = new HashMap();
        accountServiceMock = mock(AccountService.class);
        context.put(AccountService.class, accountServiceMock);
        requestMock = mock(HttpServletRequest.class);
        responseMock = mock(HttpServletResponse.class);
        httpSessionMock  = mock(HttpSession.class);
        greetingServletSpy = spy(new GreetingServlet(context));
    }

    @Test
    void doGetUnauthorizedTest() throws IOException {
        /*doNothing()
                .when(responseMock)
                .setContentType(
                        eq(Servletable.COMMON_CONTENT_TYPE) );*/
        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);
            assertEquals(Servletable.COMMON_CONTENT_TYPE, arg0);
            return null;
        }).when(responseMock).setContentType(anyString());
        /*doNothing()
                .when(responseMock)
                .setStatus(
                        eq(HttpServletResponse.SC_UNAUTHORIZED) );*/
        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);
            assertEquals(HttpServletResponse.SC_UNAUTHORIZED, arg0);
            return null;
        }).when(responseMock).setStatus(eq(HttpServletResponse.SC_UNAUTHORIZED));
        /*doNothing()
                .when(responseMock)
                .sendRedirect(
                        eq(Servletable.REDIR_ROOT) );*/
        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);
            assertEquals(Servletable.REDIR_ROOT, arg0);
            return null;
        }).when(responseMock).sendRedirect(anyString());

        userProfileMock = null;
        String testSessionId = randomAlphabetic(3);
        when(httpSessionMock.getId()).thenReturn(testSessionId);
        when(requestMock.getSession()).thenReturn(httpSessionMock);
        when(accountServiceMock.getUserBySessionId(anyString())).thenReturn(userProfileMock);

        greetingServletSpy.doGet(requestMock, responseMock);

        /*doAnswer(invocation -> {  //NOVA
            Object arg0 = invocation.getArgument(0);
            assertEquals(Servletable.COMMON_CONTENT_TYPE, arg0);
            return null;
        }).when(responseMock).setContentType(anyString());
        doAnswer(invocation -> {  //NOVA
            Object arg0 = invocation.getArgument(0);
            assertEquals(Servletable.COMMON_CONTENT_TYPE, arg0);
            return null;
        }).when(responseMock).setContentType(anyString());


        when((ParentServlet)greetingServletSpy.).

        when()*/

    }

}
