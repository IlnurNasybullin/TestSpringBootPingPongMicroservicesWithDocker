package org.example.pongapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.pongapp.service.PongPingGameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/pong-service")
public class PongController {

    private final PongPingGameService pongPingGame;

    @PostConstruct
    public void init() {
        log.info("PingController is initialized");
    }

    @PostMapping("/start")
    public void start() {
        log.info("Start pong-ping game");
        CompletableFuture.runAsync(pongPingGame::startPong);
    }

    @PostMapping("/stop")
    public void stop() {
        log.info("Stopping pong-ping game");
        pongPingGame.stopPong();
        log.info("Pong-ping game is stopped");
    }

    @PostMapping("/pong")
    public String pong(@RequestBody String ping) {
        log.info("pong");
        log.info(ping);
        return "pong";
    }

}

