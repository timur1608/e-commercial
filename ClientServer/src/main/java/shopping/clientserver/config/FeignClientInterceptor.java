package shopping.clientserver.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shopping.clientserver.services.TokenExtractor;

@Slf4j
@Component
public class FeignClientInterceptor implements RequestInterceptor {
    private final TokenExtractor tokenExtractor;

    @Autowired
    public FeignClientInterceptor(TokenExtractor tokenExtractor) {
        this.tokenExtractor = tokenExtractor;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String accessToken = tokenExtractor.getAccessToken();
        requestTemplate.header("Authorization", "Bearer " + accessToken);
    }
}
