package com.example.websocketsecurity.config;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Objects;

@Component
public class AuthChannelInterceptorAdapter implements ChannelInterceptor {
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        final StompHeaderAccessor accessor= MessageHeaderAccessor.getAccessor(message,StompHeaderAccessor.class);
        if(StompCommand.CONNECT==accessor.getCommand()){
            final UsernamePasswordAuthenticationToken user=new UsernamePasswordAuthenticationToken("vdt", null, Collections.singleton((GrantedAuthority) ()->"ROLE_ADMIN"));
            accessor.setUser(user);
        }
        if(StompCommand.DISCONNECT==accessor.getCommand()){
            System.out.println("disconnect roi ne");
        }
        if(StompCommand.SUBSCRIBE==accessor.getCommand()){
            String actionSubscribe= accessor.getDestination();
            assert actionSubscribe != null;
            String userId=actionSubscribe.split("/")[1];
            var user=accessor.getUser();
            assert user != null;
            if(!Objects.equals(userId, user.getName())){
                return null;
            }
        }
        return message;
    }
}
