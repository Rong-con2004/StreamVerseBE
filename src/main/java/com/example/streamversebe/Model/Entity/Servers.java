package com.example.streamversebe.Model.Entity;

import com.example.streamversebe.Model.BaseModel;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "Servers")
public class Servers extends BaseModel {

    @Field(name = "name")
    @NotBlank(message = "Name is required")
    private String name;

    @Field(name = "description")
    private String description;

    @Field(name = "profileImg")
    private String profileImg;

    @DBRef
    @Field(name = "hosts")
    private List<Users> hosts;

    @Field(name = "members")
    private List<Member> members;

    @DBRef
    @Field(name = "channels")
    private List<Channels> channels;

}
