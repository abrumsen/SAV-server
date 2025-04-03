package be.masimart.sav.controller;

import be.masimart.sav.model.ChatMessage;
import be.masimart.sav.service.ChatMessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/webchat")
public class ChatMessageController {
    private final ChatMessageService chatMessageService;

    public ChatMessageController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    // GET /webchat/conversation/{chatId} → Get messages for a chatroom
    @GetMapping("/conversation/{chatId}")
    public ResponseEntity<List<ChatMessage>> getConversation(@PathVariable int chatId) {
        List<ChatMessage> messages = chatMessageService.getMessagesByChatId(chatId);
        return ResponseEntity.ok(messages);
    }

    // POST /webchat → Send a message
    @PostMapping
    public ResponseEntity<Map<String, Long>> sendMessage(@RequestBody Map<String, Object> payload) {
        int chatId = Integer.parseInt(payload.get("chatId").toString());
        String username = (String) payload.get("username");
        String message = (String) payload.get("message");

        ChatMessage savedMessage = chatMessageService.addMessage(chatId, username, message);

        Map<String, Long> response = new HashMap<>();
        response.put("messageId", savedMessage.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // PUT /webchat/{messageId} → Edit a message
    @PutMapping("/{messageId}")
    public ResponseEntity<ChatMessage> updateMessage(
            @PathVariable Long messageId,
            @RequestBody Map<String, String> payload) {

        String message = payload.get("message");

        return chatMessageService.updateMessage(messageId, message)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /webchat/{messageId} → Delete a message
    @DeleteMapping("/{messageId}")
    public ResponseEntity<Map<String, Long>> deleteMessage(@PathVariable Long messageId) {
        return chatMessageService.deleteMessage(messageId)
                .map(deletedId -> {
                    Map<String, Long> response = new HashMap<>();
                    response.put("messageId", deletedId);
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}