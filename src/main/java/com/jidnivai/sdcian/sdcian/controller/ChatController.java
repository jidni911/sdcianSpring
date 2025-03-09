// package com.jidnivai.sdcian.sdcian.controller;

// import org.springframework.messaging.handler.annotation.MessageMapping;
// import org.springframework.messaging.handler.annotation.SendTo;
// import org.springframework.stereotype.Controller;

// @Controller
// public class ChatController {

//     @MessageMapping("/sendMessage") // When a message is sent to /app/sendMessage
//     @SendTo("/topic/messages") // Broadcast to /topic/messages
//     public String handleMessage(String message) {
//         return message; // Simply return the message to broadcast it
//     }
// }
