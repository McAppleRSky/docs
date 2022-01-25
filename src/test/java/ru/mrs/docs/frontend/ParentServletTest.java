package ru.mrs.docs.frontend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mrs.base.service.account.AccountService;
import ru.mrs.docs.service.account.AuthState;
import ru.mrs.docs.service.account.UserProfile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ParentServletTest {

    private Map context;
    private AccountService accountServiceMock;
    private HttpServletRequest requestMock;
    private HttpServletResponse responseMock;
    private HttpSession httpSessionMock;
    private UserProfile userProfileMock;
    private ParentServlet parentServletTest;

    @BeforeEach
    void prepare() {
        context = new HashMap();
        accountServiceMock = mock(AccountService.class);
        context.put(AccountService.class, accountServiceMock);
        requestMock = mock(HttpServletRequest.class);
        responseMock = mock(HttpServletResponse.class);
        httpSessionMock  = mock(HttpSession.class);
        parentServletTest = new ParentServlet(context);
    }

    @Test
    void doGetUnauthorized() throws IOException {
        userProfileMock = null;
        String testSessionId = randomAlphabetic(3);
        when(httpSessionMock.getId()).thenReturn(testSessionId);
        when(requestMock.getSession()).thenReturn(httpSessionMock);
        when(accountServiceMock.getUserBySessionId(anyString())).thenReturn(userProfileMock);
        Exception exception = assertThrows(NullPointerException.class, () -> {
            parentServletTest.doGet(requestMock, responseMock);
        });
        assertEquals(AuthState.UNAUTH.toString(), exception.getMessage());
    }

    @Test
    void doGetAuthorized() throws IOException {
        userProfileMock = mock(UserProfile.class);
        String testSessionId = randomAlphabetic(3);
        when(httpSessionMock.getId()).thenReturn(testSessionId);
        when(requestMock.getSession()).thenReturn(httpSessionMock);
        when(accountServiceMock.getUserBySessionId(anyString())).thenReturn(userProfileMock);
        assertDoesNotThrow(() -> {
            parentServletTest.doGet(requestMock, responseMock);
        });
    }


}