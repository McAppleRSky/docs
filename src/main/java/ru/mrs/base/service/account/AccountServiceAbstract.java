package ru.mrs.base.service.account;

import java.util.HashMap;
import java.util.Map;

public abstract class AccountServiceAbstract<T> {

    protected final Map<String, T> loginToProfile;
    protected final Map<String, T> sessionIdToProfile;

    public AccountServiceAbstract() {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
    }

}
