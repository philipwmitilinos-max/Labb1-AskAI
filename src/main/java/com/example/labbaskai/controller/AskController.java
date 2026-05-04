package com.example.labbaskai.controller;

import com.example.labbaskai.model.ChatMessage;
import com.example.labbaskai.repository.ChatRepository;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class AskController {
    @Autowired
    private ChatRepository chatRepository;

    @GetMapping("/")
    public String home(Model model) {

        List<ChatMessage> history = chatRepository.findAll();
        model.addAttribute("history", history);

        return "index";
    }

    @GetMapping("/ask")
    public String ask(@RequestParam String prompt, Model model) {
        Client client = new Client();

        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-3-flash-preview",
                        prompt,
                        null);

        String answer = response.text();

        ChatMessage chat = new ChatMessage();
        chat.setQuestion(prompt);
        chat.setAnswer(answer);
        chatRepository.save(chat);

        List<ChatMessage> history = chatRepository.findAll();

        model.addAttribute("question", prompt);
        model.addAttribute("answer", answer);
        model.addAttribute("history", history);

        return "index";
    }
}
