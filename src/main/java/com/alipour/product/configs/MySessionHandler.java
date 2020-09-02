package com.alipour.product.configs;

import com.alipour.product.utils.SocketMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
@Slf4j
public class MySessionHandler extends StompSessionHandlerAdapter {

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
//        super.afterConnected(session, connectedHeaders);
        log.info("######### Socket connected");
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return SocketMessage.class;

    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        System.out.println(payload);
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        log.error("Exception occurred on socket", exception);
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        log.error("handleTransportError occurred on socket", exception);
    }
}
