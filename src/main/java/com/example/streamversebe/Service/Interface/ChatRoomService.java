package com.example.streamversebe.Service.Interface;

import java.util.Optional;

public interface ChatRoomService {
    String createChatRoom(String senderId, String recipientId);
    Optional<String> getChatRoomId(String senderId, String recipientId, boolean createNewRoomIfNotExist);
}
