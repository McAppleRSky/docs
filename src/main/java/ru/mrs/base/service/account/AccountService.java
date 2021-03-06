package ru.mrs.base.service.account;

public interface AccountService<T> {

    void addNewUser(T userProfile);

    void deleteUserUnregister(String login);

    T getUserByLogin(String login);

    void addSession(String sessionId, T userProfile);

    void deleteSession(String sessionId);

    T getUserBySessionId(String sessionId);

}
