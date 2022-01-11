package ru.mrs.docs.service;


import lombok.*;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
//@NoArgsConstructor
public class UserProfile implements Serializable {
    private final String login;
    private final String pass;
    private final String email;
}
