package com.helen.services.websocket;


import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/chat")
public class ChatServer {

    private static final Set<Session> clients = new HashSet<>();

    @OnOpen
    public void onOpen(Session session) {
        clients.add(session);
        System.out.println("New connection: " + session.getId());
    }

    @OnMessage
    public String onMessage(String message, Session session) {
        System.out.println("Message from client " + session.getId() + ": " + message);
        return "Server received: " + message; // Echo message back to the client
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        clients.remove(session);
        System.out.println("Connection closed: " + session.getId() + ", Reason: " + closeReason);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.err.println("Error on connection " + session.getId() + ": " + throwable.getMessage());
    }
    // Send a message to all connected clients
    public static void broadcast(String message) {
        for (Session client : clients) {
            if (client.isOpen()) {
                try {
                    client.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
