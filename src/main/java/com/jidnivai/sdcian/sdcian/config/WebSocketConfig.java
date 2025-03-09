// package com.jidnivai.sdcian.sdcian.config;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.messaging.simp.config.MessageBrokerRegistry;
// import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
// import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
// import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

// @Configuration
// @EnableWebSocketMessageBroker
// public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

//     @Override
//     public void registerStompEndpoints(StompEndpointRegistry registry) {
//         registry.addEndpoint("/ws") // WebSocket endpoint
//                 .setAllowedOriginPatterns("http://localhost:4200", "https://myapp.com") // Allow CORS for testing
//                 .withSockJS(); // Enable SockJS fallback
//     }

//     @Override
//     public void configureMessageBroker(MessageBrokerRegistry registry) {
//         registry.enableSimpleBroker("/topic"); // Clients subscribe to this
//         registry.setApplicationDestinationPrefixes("/app"); // Prefix for messages sent to server
//     }
// }
