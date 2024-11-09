package com.example.streamversebe.Model.Entity;

import com.example.streamversebe.Model.BaseModel;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "ChannelMessages")
public class ChannelMessages extends BaseModel {

    @DBRef
    @Field(name = "channelId")
    private Channels channelId;

    @DBRef
    @Field(name = "senderId")
    private Users senderId;

    @Field(name = "message")
    private String message;

    @Field(name = "attachment")
    private Attachment attachment;
}