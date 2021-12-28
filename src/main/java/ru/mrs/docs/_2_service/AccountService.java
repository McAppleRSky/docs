package ru.mrs.docs._2_service;

import java.util.List;

public interface AccountService {

    // post user
    void createUser(String login, String password);

    // get user
    List<Object> getUserAll();

    // get user/id
    Object getUser(String login);

    // patch user/id
    void updateUser(String login, String login1, String password);

    // delete user/id
    void deleteUser(String login);

    // post session
//    boolean addSession(String sessionId, String login);
    void addSession(String sessionId, UserProfile userProfile);

    // delete session
    void deleteSession(String sessionId);

    Object getUserByLogin(String login);

//    String getUserBySessionId(String sessionId);
    UserProfile getUserBySessionId(String sessionId);

    int getUsersCount();

    int getUsersLimit();

    void setUsersLimit(int usersLimit);

}
