package ru.mrs.docs._2_service;


import lombok.*;

@Getter
@RequiredArgsConstructor
//@NoArgsConstructor
public class UserProfile {
    private final String login;
    private final String pass;
    private final String email;
}
