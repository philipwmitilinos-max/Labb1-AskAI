package com.example.labbaskai.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.checkerframework.checker.signature.qual.Identifier;

@Data
@Entity
public class ChatMessage {

    @Id
    @GeneratedValue
    private Long id;

    private String question;
    private String answer;
}
