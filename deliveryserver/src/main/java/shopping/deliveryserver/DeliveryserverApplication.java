package shopping.deliveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DeliveryserverApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeliveryserverApplication.class, args);
    }
}
