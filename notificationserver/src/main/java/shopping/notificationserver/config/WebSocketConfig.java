package shopping.notificationserver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Slf4j
@Configuration
@EnableWebSocketMessageBroker
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final ApplicationContext applicationContext;
//    private final AuthorizationManager<Message<?>> authorizationManager;


    public WebSocketConfig(JwtDecoder jwtDecoder, JwtAuthenticationConverter jwtAuthenticationConverter,
                           ApplicationContext applicationContext) {
        this.jwtAuthenticationProvider = new JwtAuthenticationProvider(jwtDecoder);
        this.jwtAuthenticationProvider.setJwtAuthenticationConverter(jwtAuthenticationConverter);
        this.applicationContext = applicationContext;
//        this.authorizationManager = authorizationManager;
    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic", "/user");
        registry.setApplicationDestinationPrefixes("/app");
        registry.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    String token = accessor.getFirstNativeHeader("token");
                    if (token != null) {
                        Authentication auth = jwtAuthenticationProvider.authenticate(new BearerTokenAuthenticationToken(token));
                        accessor.setUser(auth);
                    }
                }
                return message;
            }
        });
    }
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
//        argumentResolvers.add(new AuthenticationPrincipalArgumentResolver());
//    }
//    @Override
//    public void configureClientOutboundChannel(ChannelRegistration registration) {
//        AuthorizationChannelInterceptor authz = new AuthorizationChannelInterceptor(authorizationManager);
//        AuthorizationEventPublisher eventPublisher = new SpringAuthorizationEventPublisher(applicationContext);
//        authz.setAuthorizationEventPublisher(eventPublisher);
//        registration.interceptors(new SecurityContextChannelInterceptor(), authz);
//    }

}
