package org.example.pingapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
public class PingPongGameServiceImpl implements PingPongGameService {

    private final AtomicReference<GameState> currentState;

    @Value("${app.msPause}")
    private int msPause;

    @Value("${app.pongServiceUrl}")
    private String pongServiceUrl;

    public PingPongGameServiceImpl() {
        currentState = new AtomicReference<>(GameState.INITIALIZED);
    }

    @Override
    public void startPing() {
        currentState.set(GameState.STARTED);

        try {
            while (currentState() == GameState.STARTED) {
                ping();
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
            stopPing();
        }
    }

    private void pause() throws InterruptedException {
        msPause = 1_000;
        TimeUnit.MILLISECONDS.sleep(msPause);
    }

    private void ping() {
        log.info("ping");
        String result = sendPing();
        log.info(result);
    }

    private String sendPing() {
        log.debug("sending request");
        RestTemplate template = new RestTemplate();
        return template.postForObject(pongServiceUrl, "ping", String.class);
    }

    @Override
    public GameState currentState() {
        return currentState.get();
    }

    @Override
    public void stopPing() {
        currentState.set(GameState.STOPPED);
    }
}
