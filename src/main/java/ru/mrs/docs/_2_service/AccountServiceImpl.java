package ru.mrs.docs._2_service;

import ru.mrs.base.service.account.AccountService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class AccountServiceImpl implements AccountService<UserProfile> {

    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;

    public AccountServiceImpl() {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
    }

    @Override
    public void addNewUser(UserProfile userProfile) {
        throw new NotImplementedException();
    }

    @Override
    public void deleteUserUnregister(String login) {
        throw new NotImplementedException();
    }

    @Override
    public UserProfile getUserByLogin(String login) {
        throw new NotImplementedException();
    }

    @Override
    public void addSession(String sessionId, UserProfile userProfile) {
        throw new NotImplementedException();
    }

    @Override
    public void deleteSession(String sessionId) {
        throw new NotImplementedException();
    }

    @Override
    public UserProfile getUserBySessionId(String sessionId) {
        throw new NotImplementedException();
    }

}
