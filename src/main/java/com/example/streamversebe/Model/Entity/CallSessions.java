package com.example.streamversebe.Model.Entity;

import com.example.streamversebe.Model.BaseModel;
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
@Document(collection = "CallSessions")
public class CallSessions extends BaseModel {

    @DBRef
    @Field(name = "channelId")
    private Channels channelId;

    @DBRef
    @Field(name = "participants")
    private List<Users> participants;

    @Field(name = "startedAt")
    private LocalDateTime startedAt;

    @Field(name = "endedAt")
    private LocalDateTime endedAt;
}
