package ru.mrs.docs.service.account;

import ru.mrs.base.service.account.AccountService;
import ru.mrs.base.service.account.AccountServiceAbstract;
import org.apache.commons.lang3.NotImplementedException;

public class AccountServiceImpl extends AccountServiceAbstract<UserProfile> implements AccountService<UserProfile> {

    public AccountServiceImpl() {
        super();
    }
    public AccountServiceImpl(String login, UserProfile userProfile) {
        this();
        loginToProfile.put(login, userProfile);
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
//        throw new NotImplementedException();
        return loginToProfile.get(login);
    }

    @Override
    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
//        throw new NotImplementedException();
    }

    @Override
    public void deleteSession(String sessionId) {
//        throw new NotImplementedException();
        sessionIdToProfile.get(sessionId);
    }

    @Override
    public UserProfile getUserBySessionId(String sessionId) {
//        throw new NotImplementedException();
        return sessionIdToProfile.get(sessionId);
    }

}
