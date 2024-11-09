package com.example.streamversebe.DTOs.Reponse;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AuthRes {
    private String token;
    private String username;
    private String email;
    private String message;
    private boolean status;
}
