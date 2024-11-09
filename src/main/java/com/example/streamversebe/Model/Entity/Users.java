package com.example.streamversebe.Model.Entity;

import com.example.streamversebe.Model.BaseModel;
import com.example.streamversebe.Model.Enum.ERole;
import com.example.streamversebe.Model.Enum.EStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "Users")
public class Users extends BaseModel {

    @NotBlank(message = "Username is required")
    @Field(name = "username")
    private String username;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    @Field(name = "email")
    private String email;

    @NotBlank(message = "Password is required")
    @Field(name = "password")
    private String password;

    @Field(name = "phoneNumber")
    private String phoneNumber;

    @DBRef
    @Field(name = "role")
    private ERole role;

    @Field(name = "profileImg")
    private String profileImg;

    @Field(name = "status")
    private EStatus status;

    @DBRef
    @Field(name = "servers")
    private List<Servers> servers;

    @DBRef
    @Field(name = "friends")
    private List<Users> friends;

    @Field(name = "requestFriends")
    private List<RequestFriend> requestFriends;

    @DBRef
    @Field(name = "blockedUsers")
    private List<Users> blockedUsers;

    @Field(name = "isPremium")
    private boolean isPremium;

    @Field(name = "isEnable")
    private boolean isEnable;

    @Field(name = "lastActive")
    private LocalDateTime lastActive;
}