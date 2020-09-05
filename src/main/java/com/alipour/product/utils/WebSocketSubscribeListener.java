package com.alipour.product.utils;

import com.alipour.product.services.MarketStatusService;
import com.fasterxml.jackson.databind.JsonNode;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class WebSocketSubscribeListener extends WebSocketAdapter {

    private JsonUtil jsonUtil;
    private MarketStatusService statusService;

    @Value("${socket.state.subscribe}")
    private String subscribeMethod;

    public WebSocketSubscribeListener(JsonUtil jsonUtil, MarketStatusService statusService) {
        this.jsonUtil = jsonUtil;
        this.statusService = statusService;
    }

    @Override
    public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {
        log.info(" ** Socket successfully connected ** ");

        SocketMessage socketMessage = SocketMessage.builder()
                .method(subscribeMethod)
                .params(new String[0])
                .id(5252L)
                .build();
        websocket.sendText(jsonUtil.toJson(socketMessage).toString());
    }

    @Override
    public void onTextMessage(WebSocket websocket, String text) throws Exception {
        JsonNode jsonNode = jsonUtil.readAsJson(text);
        if (jsonNode.hasNonNull("method")) {
            String method = jsonNode.get("method").asText();
            if (method.equalsIgnoreCase("state.update")) {

                boolean b = true;
                for (JsonNode node : jsonNode.get("params")) {
                    if (b) {
                        log.info(jsonUtil.mapFromJson(node).toString());
                        b=false;
                    }
                }
            }
        }
    }

    @Override
    public void onError(WebSocket websocket, WebSocketException cause) throws Exception {
        log.info(" ** Error!!!! ** ", cause);
    }
}
