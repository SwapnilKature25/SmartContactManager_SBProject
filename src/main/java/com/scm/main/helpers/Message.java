package com.scm.main.helpers;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Message {

    private String content;
    @Builder.Default
    private MessageType type=MessageType.blue;
}
