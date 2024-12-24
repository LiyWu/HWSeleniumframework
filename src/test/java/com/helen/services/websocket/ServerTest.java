package com.helen.services.websocket;

import org.glassfish.tyrus.server.Server;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class ServerTest {

    public static void main(String[] args) {
        Server server = new Server("localhost", 8765, "/", Map.of(), ChatServer.class);

        try {
            System.out.println("Starting WebSocket server...");
            server.start();
            System.out.println("Server started on ws://localhost:8765/chat");
            System.out.println("Press Ctrl+C to stop the server...");

            Timer timer = new Timer(true);
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    String message = "Message from server at " + System.currentTimeMillis();
                    ChatServer.broadcast(message);
                    System.out.println("Broadcasting: " + message);
                }
            }, 0, 5000);

            Thread.currentThread().join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.stop();
        }
    }
}
