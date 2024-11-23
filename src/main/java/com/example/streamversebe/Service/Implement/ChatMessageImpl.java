package com.example.streamversebe.Service.Implement;

import com.example.streamversebe.Model.Entity.ChatMessage;
import com.example.streamversebe.Repository.Interface.ChatMessageRepository;
import com.example.streamversebe.Service.Interface.ChatMessageService;
import com.example.streamversebe.Service.Interface.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ChatMessageImpl implements ChatMessageService {
    private final ChatMessageRepository repository;
    private final ChatRoomService chatRoomService;

    @Override
    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
        return chatId.map(repository::findByChatId).orElse(new ArrayList<>());
    }

    @Override
    public ChatMessage saveMessage(ChatMessage chatMessage) {
        var chatId = chatRoomService
                .getChatRoomId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true)
                .orElseThrow(); // You can create your own dedicated exception
        chatMessage.setChatId(chatId);
        repository.save(chatMessage);
        return chatMessage;
    }
}
