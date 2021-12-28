package ru.mrs.docs._2_service;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
//import org.apache.commons.lang3.NotImplementedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountServiceImpl implements AccountService {

    protected final Map<String, UserProfile> loginToProfile;
    protected final Map<String ,UserProfile> sessionIdToProfile;

    public AccountServiceImpl() {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
    }

    @Override
    public void createUser(String login, String password) {
        // TODO: need impl
        throw new NotImplementedException();
    }

    @Override
    public List<Object> getUserAll() {
        // TODO: need impl
        throw new NotImplementedException();
    }

    @Override
    public Object getUser(String login) {
        // TODO: need impl
        throw new NotImplementedException();
    }

    @Override
    public void updateUser(String login, String login1, String password) {
        // TODO: need impl
        throw new NotImplementedException();
    }

    @Override
    public void deleteUser(String login) {
        // TODO: need impl
        throw new NotImplementedException();
    }

    @Override
    public void addSession(String sessionId, UserProfile userProfile) {
//        throw new NotImplementedException();
        sessionIdToProfile.put(sessionId, userProfile);
    }

    @Override
    public void deleteSession(String sessionId) {
//        throw new NotImplementedException();
        sessionIdToProfile.remove(sessionId);
    }

    @Override
    public UserProfile getUserByLogin(String login) {
        // throw new NotImplementedException();
        return loginToProfile.get(login);
    }

    @Override
    public UserProfile getUserBySessionId(String sessionId) {
//        throw new NotImplementedException();
        return sessionIdToProfile.get(sessionId);
    }

    @Override
    public int getUsersCount() {
        // TODO: need impl
        throw new NotImplementedException();
    }

    @Override
    public int getUsersLimit() {
        // TODO: need impl
        throw new NotImplementedException();
    }

    @Override
    public void setUsersLimit(int usersLimit) {
        // TODO: need impl
        throw new NotImplementedException();
    }

}
