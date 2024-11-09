package com.example.streamversebe.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @DBRef
    @Field(name = "user_id")
    private Users user_id;

    @DBRef
    @Field(name = "role_id")
    private Roles role_id;
}