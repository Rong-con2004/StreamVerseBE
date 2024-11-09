package com.example.streamversebe.DTOs.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDTO {
    private String id;
    private String username;
    private String email;
    private String password;
    private String profileImg;
    private String status;
}
