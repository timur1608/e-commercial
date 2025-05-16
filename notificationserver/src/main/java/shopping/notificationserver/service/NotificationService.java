package shopping.notificationserver.service;

import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.resource.Email;
import com.mailjet.client.resource.Emailv31;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationService {
    private SimpMessagingTemplate messagingTemplate;
    private MailjetClient mailjetClient;
    @Autowired
    public NotificationService(SimpMessagingTemplate simpMessagingTemplate, MailjetClient mailjetClient, ResourceLoader resourceLoader){
        this.messagingTemplate = simpMessagingTemplate;
        this.mailjetClient = mailjetClient;
    }

    public void sendMessage(String user, String message) throws MailjetException {
        log.info(message);
        if (message.equals("order is ready")) {
            TransactionalEmail message1 = TransactionalEmail.builder()
                    .to(new SendContact)
//            MailjetRequest mailjetRequest = new MailjetRequest(Emailv31.resource)
//                    .property(Emailv31.MESSAGES, new JSONObject()
//                            .put(Emailv31.Message.FROM, new JSONObject().
//                                    put("Email", "timur160879@gmail.com"))
//                            .put(Emailv31.Message.TO, new JSONObject()
//                                    .put("Email", "timur1608@list.ru"))
//                            .put(Emailv31.Message.TEXTPART, "Dear passenger 1, welcome to Mailjet! May the delivery force be with you!")
//                    );
//            MailjetResponse mailjetResponse = mailjetClient.post(mailjetRequest);
            log.info(String.valueOf(mailjetResponse.getStatus()));
            log.info(String.valueOf(mailjetResponse.getData()));
        }
        messagingTemplate.convertAndSendToUser(user, "/queue/notifications", message);
    }
}
