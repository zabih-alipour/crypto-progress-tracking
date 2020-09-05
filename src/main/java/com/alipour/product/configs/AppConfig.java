package com.alipour.product.configs;

import com.alipour.product.models.MarketStatus;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.neovisionaries.ws.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Configuration
@Slf4j
public class AppConfig {

    @Value("${socket.address}")
    private String socketAddress;

    private MarketStatusDeserializer marketStatusDeserializer;

    public AppConfig(MarketStatusDeserializer marketStatusDeserializer) {
        this.marketStatusDeserializer = marketStatusDeserializer;
    }

    @Bean
    public Optional<WebSocket> webSocket(List<WebSocketListener> adapters) {
        Optional<WebSocket> webSocket = Optional.empty();
        try {
            webSocket = Optional.ofNullable(new WebSocketFactory()
                    .createSocket(socketAddress)
                    .addListeners(adapters)
                    .connect());
        } catch (WebSocketException | IOException e) {
            e.printStackTrace();
        }
        return webSocket;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
                new SimpleModule("MarketStatusDeSerializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(MarketStatus.class, marketStatusDeserializer);
        mapper.registerModule(module);

        return mapper;
    }
}
