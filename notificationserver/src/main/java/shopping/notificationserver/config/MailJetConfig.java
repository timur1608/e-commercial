package shopping.notificationserver.config;

import lombok.extern.slf4j.Slf4j;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.ClientOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MailJetConfig {
    @Bean
    public MailjetClient getClient(@Value("${mailjet.api.sk}") String secret_key,
                                   @Value("${mailjet.api.pk}") String public_key) {
        log.info(secret_key);
        log.info(public_key);
        return new MailjetClient(ClientOptions.builder().apiKey(public_key).apiSecretKey(secret_key).build());
    }
}
