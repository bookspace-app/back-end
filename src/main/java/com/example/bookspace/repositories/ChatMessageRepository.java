package com.example.bookspace.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

import com.example.bookspace.enums.MessageStatus;
import com.example.bookspace.models.ChatMessage;

public interface ChatMessageRepository
        extends MongoRepository<ChatMessage, String> {

    long countBySenderIdAndRecipientIdAndStatus(
            String senderId, String recipientId, MessageStatus status);

    List<ChatMessage> findByChatId(String chatId);
}
