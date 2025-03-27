package shopping.notificationserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping
@RestController
public class NotificationController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @SendTo("/topic/notifications")
    public String sendMessage(String message) {
        return message;
    }
    @MessageMapping("/notify")
    public void notify(String message) {
        log.info("Notifying message {}", message);
    }
    @PostMapping
    public void index(@RequestBody String message) {
        messagingTemplate.convertAndSend("/topic/notifications", message);
    }
}
