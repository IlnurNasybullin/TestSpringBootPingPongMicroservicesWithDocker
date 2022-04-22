package org.example.pongapp.service;

public interface PongPingGameService {

    void startPong();

    GameState currentState();

    void stopPong();

}
