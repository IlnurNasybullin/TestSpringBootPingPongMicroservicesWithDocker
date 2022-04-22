package org.example.pongapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
public class PongPingGameServiceImpl implements PongPingGameService {

    private final AtomicReference<GameState> currentState;

    @Value("${app.msPause}")
    private int msPause;

    @Value("${app.pingServiceUrl}")
    private String pingServiceUrl;

    public PongPingGameServiceImpl() {
        currentState = new AtomicReference<>(GameState.INITIALIZED);
    }

    @Override
    public void startPong() {
        currentState.set(GameState.STARTED);

        try {
            while (currentState() == GameState.STARTED) {
                pong();
                pause();
            }
        } catch (InterruptedException e) {
            throw new IllegalStateException(
                    String.format(
                            "Thread %s is interrupted while game is set on pause",
                            Thread.currentThread().getName()
                    ), e
            );
        } finally {
            stopPong();
        }
    }

    private void pause() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(msPause);
    }

    private void pong() {
        log.info("pong");
        String result = sendPong();
        log.info(result);
    }

    private String sendPong() {
        log.debug("sending request");
        RestTemplate template = new RestTemplate();
        return template.postForObject(pingServiceUrl, "pong", String.class);
    }

    @Override
    public GameState currentState() {
        return currentState.get();
    }

    @Override
    public void stopPong() {
        currentState.set(GameState.STOPPED);
    }
}
