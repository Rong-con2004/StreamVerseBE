package com.example.streamversebe.DTOs.Reponse;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MessRes<T> {
    private String message;
    private T data;
}
