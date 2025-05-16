package shopping.notificationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.socket.EnableWebSocketSecurity;
import shopping.notificationserver.controller.NotificationController;

@SpringBootApplication
public class NotificationserverApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationserverApplication.class, args);
    }
}
