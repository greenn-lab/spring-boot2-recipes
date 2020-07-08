package study.spring.springboot2recipes.ch04async.websocket.echo;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class EchoHandler extends TextWebSocketHandler {
  
  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    session.sendMessage(new TextMessage("CONNECTION ESTABLISHED!"));
  }
  
  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    final String payload = message.getPayload();
    session.sendMessage(new TextMessage("RECEIVED: " + payload));
  }
}
