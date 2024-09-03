package com.example.websocketsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@EnableWebSocketMessageBroker
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE+99)
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer{
    private final AuthChannelInterceptorAdapter authChannel;

    public WebsocketConfig(AuthChannelInterceptorAdapter authChannel) {
        this.authChannel = authChannel;
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setUserDestinationPrefix("/user");
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/user","/app");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(authChannel);
    }

}
