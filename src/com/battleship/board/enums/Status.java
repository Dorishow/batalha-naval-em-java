package com.battleship.board.enums;

public enum Status {
    SUBMARINE_PLACED("Submarine successfully  placed"),
    PLAY_EXECUTED("Play successfully executed"),
    WATER_HIT("The shot hit the water..."),
    SUBMARINE_HIT("The shot hit a submarine!");

    public String message;

    Status(String message) {
        this.message = message;
    }
}
