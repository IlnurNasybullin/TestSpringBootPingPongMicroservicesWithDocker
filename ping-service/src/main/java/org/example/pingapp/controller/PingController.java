package org.example.pingapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.pingapp.service.PingPongGameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/ping-service")
@RequiredArgsConstructor
public class PingController {

    private final PingPongGameService pingPongGame;

    @PostConstruct
    public void init() {
        log.info("PingController is initialized");
    }

    @PostMapping("/start")
    public void start() {
        log.info("Start ping-pong game");
        CompletableFuture.runAsync(pingPongGame::startPing);
    }

    @PostMapping("/stop")
    public void stop() {
        log.info("Stopping ping-pong game");
        pingPongGame.stopPing();
        log.info("Ping-pong game is stopped");
    }

    @PostMapping("/ping")
    public String ping(@RequestBody String pong) {
        log.info("ping");
        log.info(pong);
        return "ping";
    }

}

