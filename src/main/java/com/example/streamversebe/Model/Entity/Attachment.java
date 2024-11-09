package com.example.streamversebe.Model.Entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attachment {
    private String file_url;

    private String file_type;
}