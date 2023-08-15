package larry.ramirez.controller;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import larry.ramirez.controller.model.CommentModel;
import larry.ramirez.controller.model.PostModel;
import larry.ramirez.serializer.JSONMapper;
import larry.ramirez.serializer.JSONMapperImpl;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@ServerEndpoint("/retrieve/{correlationId}")
public class SocketController {

    private static final Logger logger = Logger.getLogger(SocketController.class.getName());
    private static Map<String, Map<String, Session>> sessions;
    private final JSONMapper eventSerializer = new JSONMapperImpl();


    public SocketController() {
        if (Objects.isNull(sessions)) {
            sessions = new ConcurrentHashMap<>();
        }
    }


    @OnOpen
    public void onOpen(Session session, @PathParam("correlationId") String correlationId) {
        logger.info("Connected from: " + correlationId);
        var map = sessions.getOrDefault(correlationId, new HashMap<>());
        map.put(session.getId(), session);
        sessions.put(correlationId, map);
    }

    @OnClose
    public void onClose(Session session, @PathParam("correlationId") String correlationId) {
        sessions.get(correlationId).remove(session.getId());
        logger.info("Disconnected by: " + correlationId);
    }

    @OnError
    public void onError(Session session, @PathParam("correlationId") String correlationId, Throwable throwable) {
        sessions.get(correlationId).remove(session.getId());
        logger.log(Level.SEVERE, throwable.getMessage());
    }


    public void sendPostCreated(String correlationId, PostModel model) {
        String message = eventSerializer.writeToJson(model);
        if (Objects.nonNull(correlationId) && sessions.containsKey(correlationId)) {
            logger.info("Sent from: " + correlationId);
            System.out.println(message);
            sessions
                    .get(correlationId)
                    .values()
                    .forEach(session -> {
                        try {
                            session.getAsyncRemote().sendText(message);
                        } catch (RuntimeException e) {
                            logger.log(Level.SEVERE, e.getMessage(), e);
                        }
                    });
        }
    }

    public void sendCommentAdded(String correlationId, CommentModel model) {
        String message = eventSerializer.writeToJson(model);
        if (Objects.nonNull(correlationId) && sessions.containsKey(correlationId)) {
            logger.info("Sent from: " + correlationId);
            sessions
                    .get(correlationId)
                    .values()
                    .forEach(session -> {
                        try {
                            session.getAsyncRemote().sendText(message);
                        } catch (RuntimeException e) {
                            logger.log(Level.SEVERE, e.getMessage(), e);
                        }
                    });
        }
    }
}