package shopping.notificationserver.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationService {
    private SimpMessagingTemplate messagingTemplate;
    private EmailService emailService;
    @Autowired
    public NotificationService(SimpMessagingTemplate simpMessagingTemplate, EmailService emailService) {
        this.messagingTemplate = simpMessagingTemplate;
        this.emailService = emailService;
    }

    public void sendMessage(String user, String message)  {
        log.info(message);
        emailService.sendReceipt("hello", user, message);
        messagingTemplate.convertAndSendToUser(user, "/queue/notifications", message);
    }
}
