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
public class RequestFriend {

    @DBRef
    @Field(name = "idSender")
    private Users idSender;

    @Field(name = "isAccepted")
    private boolean isAccepted;
}