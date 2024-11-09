package com.example.streamversebe.Model.Entity;

import com.example.streamversebe.Model.Enum.ERole;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "Roles")
public class Roles {

    @Id
    @MongoId(value = FieldType.STRING)
    @Field(name = "role_id")
    private ObjectId role_id;

    @Field(name = "role_name")
    private ERole role_name;

    @Field(name = "permissions")
    private String[] permissions;
}
