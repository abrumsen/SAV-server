package be.masimart.sav.service;

import be.masimart.sav.model.ChatMessage;
import be.masimart.sav.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;

    public ChatMessageService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    // Get all messages for a chatroom
    public List<ChatMessage> getMessagesByChatId(Integer chatId) {
        return chatMessageRepository.findByChatId(chatId);
    }

    // Add a new message
    @Transactional
    public ChatMessage addMessage(Integer chatId, String username, String message) {
        ChatMessage chatMessage = new ChatMessage(
                chatId,
                username,
                message,
                LocalDateTime.now()
        );
        return chatMessageRepository.save(chatMessage);
    }

    // Update a message
    @Transactional
    public Optional<ChatMessage> updateMessage(Long messageId, String message) {
        return chatMessageRepository.findById(messageId).map(existingMessage -> {
            existingMessage.setMessage(message);
            existingMessage.setTimestamp(LocalDateTime.now());
            return chatMessageRepository.save(existingMessage);
        });
    }

    // Delete a message
    @Transactional
    public Optional<Long> deleteMessage(Long messageId) {
        return chatMessageRepository.findById(messageId).map(message -> {
            chatMessageRepository.delete(message);
            return messageId;
        });
    }
}