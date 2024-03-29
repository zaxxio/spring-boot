package com.avaand.app.config;

import com.avaand.app.signaling.impl.SignalHandler;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final WebSocketHandler webSocketHandler = new WebSocketHandler() {

        final List<WebSocketSession> webSocketSessions = Collections.synchronizedList(new ArrayList<>());

        @Override
        public void afterConnectionEstablished(@NotNull WebSocketSession session) throws Exception {
            webSocketSessions.add(session);
        }

        @Override
        public void handleMessage(@NotNull WebSocketSession session, @NotNull WebSocketMessage<?> message) throws Exception {
            for (WebSocketSession webSocketSession : webSocketSessions) {
                webSocketSession.sendMessage(message);
            }
        }

        @Override
        public void handleTransportError(@NotNull WebSocketSession session, @NotNull Throwable exception) throws Exception {

        }

        @Override
        public void afterConnectionClosed(@NotNull WebSocketSession session, @NotNull CloseStatus closeStatus) throws Exception {
            webSocketSessions.remove(session);
        }

        @Override
        public boolean supportsPartialMessages() {
            return false;
        }

    };

    private final SignalHandler signalHandler;

    public WebSocketConfig(SignalHandler signalHandler) {
        this.signalHandler = signalHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/connectToBroker")
                .setAllowedOrigins("http://localhost:3000","*");

        registry.addHandler(signalHandler, "/connectTo")
                .setAllowedOrigins("http://localhost:5173", "*");
    }

}
