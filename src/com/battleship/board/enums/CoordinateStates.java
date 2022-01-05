package com.battleship.board.enums;

public enum CoordinateStates {
    EMPTY(' '),
    SUBMARINE('N'),
    DESTROYED_SUBMARINE('*'),
    WATER_HIT('-');

    public char symbol;

    CoordinateStates(char symbol) {
        this.symbol = symbol;
    }
}
