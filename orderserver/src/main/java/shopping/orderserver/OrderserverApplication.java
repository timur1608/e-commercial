package shopping.orderserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderserverApplication.class, args);
    }

}
