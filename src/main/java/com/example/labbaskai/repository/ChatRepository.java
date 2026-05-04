package com.example.labbaskai.repository;

import com.example.labbaskai.model.ChatMessage;
import com.google.genai.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<ChatMessage, Long> {
}
