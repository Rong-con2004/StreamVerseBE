package com.example.streamversebe.DTOs.Reponse;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LoginRes {
    private String accessToken;
    private String refreshToken;
    private String email;
    private String message;
    private boolean status;
}
