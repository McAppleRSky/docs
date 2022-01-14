package ru.mrs.docs.service.account;


import lombok.*;

import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile implements Serializable {
    private String login;
    private String pass;
    private String email;
}
