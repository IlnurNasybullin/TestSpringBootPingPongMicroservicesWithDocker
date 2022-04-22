package org.example.pingapp.service;

public interface PingPongGameService {

    void startPing();

    GameState currentState();

    void stopPing();

}
