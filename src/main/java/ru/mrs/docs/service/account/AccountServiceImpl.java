package ru.mrs.docs.service.account;

import ru.mrs.base.service.account.AccountService;
import ru.mrs.base.service.account.AccountServiceAbstract;
import org.apache.commons.lang3.NotImplementedException;
import ru.mrs.base.service.file.ObjectWriter;

public class AccountServiceImpl extends AccountServiceAbstract<UserProfile> implements AccountService<UserProfile> {

    public AccountServiceImpl() {
        super();
    }
    public AccountServiceImpl(Object login, Object defaultProfile) {
        this();
        loginToProfile.put(login.toString(), (UserProfile)ObjectWriter.readResource(defaultProfile.toString()));
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
