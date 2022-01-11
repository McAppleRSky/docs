package ru.mrs.base.service.account;

import ru.mrs.docs.service.UserProfile;

import java.util.HashMap;
import java.util.Map;

public abstract class AccountServiceAbstract<T> {

    private final Map<String, T> loginToProfile;
    private final Map<String, T> sessionIdToProfile;

    public AccountServiceAbstract() {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
    }

}
