package com.example.streamversebe.Model.Entity;

import com.example.streamversebe.Model.BaseModel;
import com.example.streamversebe.Model.Enum.ETypeChannel;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "Channels")
public class Channels extends BaseModel {

    @DBRef
    @Field(name = "serverId")
    private Servers serverId;

    @Field(name = "name")
    @NotBlank(message = "Name is required")
    private String name;

    @Field(name = "type")
    private ETypeChannel type; // TextChannel - VoiceChannel
}