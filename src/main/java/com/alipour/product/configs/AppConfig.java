package com.alipour.product.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.Collections;
import java.util.concurrent.CountDownLatch;

@Configuration
@Slf4j
public class AppConfig {

    @Value("${socket.address}")
    private String socketAddress;
//    CountDownLatch downLatch = new CountDownLatch(1);

    @Bean
    public WebSocketStompClient webSocketClient(MySessionHandler handler) throws InterruptedException {


        SockJsClient socketClient = new SockJsClient(Collections.singletonList(new WebSocketTransport(new StandardWebSocketClient())));
        WebSocketStompClient webSocketStompClient = new WebSocketStompClient(socketClient);
        webSocketStompClient.setMessageConverter(new MappingJackson2MessageConverter());


        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.afterPropertiesSet();
        webSocketStompClient.setTaskScheduler(taskScheduler); // for heartbeats


        ListenableFuture<StompSession> connect = webSocketStompClient.connect(socketAddress, handler);
        connect.addCallback(new ListenableFutureCallback<StompSession>() {
            @Override
            public void onFailure(Throwable ex) {
                log.info("Connection Failed");
            }

            @Override
            public void onSuccess(StompSession result) {
                log.info("Connection Successful");
            }
        });
//        webSocketStompClient.setAutoStartup(true);
//        webSocketStompClient.start();
//        downLatch.await();
        return webSocketStompClient;
    }
}
