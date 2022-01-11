package ru.mrs.docs.service;

import ru.mrs.base.service.account.AccountService;
import ru.mrs.base.service.account.AccountServiceAbstract;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class AccountServiceImpl extends AccountServiceAbstract<UserProfile> implements AccountService<UserProfile> {

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
