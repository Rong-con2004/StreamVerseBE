package com.example.streamversebe.DTOs.Reponse;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRes {
    private String email;
    private String message;
    private boolean status;
}
