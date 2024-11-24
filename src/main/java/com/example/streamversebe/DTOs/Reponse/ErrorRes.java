package com.example.streamversebe.DTOs.Reponse;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ErrorRes {
    private int statusCode;
    private String error;
    private String message;
}
