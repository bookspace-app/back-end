package com.example.bookspace.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

import com.example.bookspace.models.ChatRoom;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
