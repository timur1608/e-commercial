package shopping.clientserver.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TokenExtractor {
    private final OAuth2AuthorizedClientManager authorizedClientManager;

    @Autowired
    public TokenExtractor(OAuth2AuthorizedClientManager authorizedClientManager) {
        this.authorizedClientManager = authorizedClientManager;
    }

    public String getAccessToken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof OAuth2AuthenticationToken oauthToken) {
            OAuth2AuthorizedClient auth2AuthorizedClient = authorizedClientManager.authorize(
                    OAuth2AuthorizeRequest.withClientRegistrationId(oauthToken.getAuthorizedClientRegistrationId())
                            .principal(auth)
                            .build()
            );
            if (auth2AuthorizedClient != null) {
//                log.info("token: {}", auth2AuthorizedClient.getAccessToken().getTokenValue());
                return auth2AuthorizedClient.getAccessToken().getTokenValue();
            }
        }
        return null;
    }
}
