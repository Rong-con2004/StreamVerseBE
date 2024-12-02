package com.example.streamversebe.Service.Interface;

import com.example.streamversebe.Model.Entity.ChatMessage;

import java.util.List;

public interface ChatMessageService {
    List<ChatMessage> findChatMessages(String senderId, String recipientId);
    ChatMessage saveMessage(ChatMessage chatMessage);
}
