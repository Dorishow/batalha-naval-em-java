package com.battleship.players;

import com.battleship.board.Board;
import com.battleship.board.Coordinate;
import com.battleship.board.enums.Error;

public class Player {
    protected final int fleetSize = 3;
    private String name = "Player";
    private final Board board = new Board(this.fleetSize);

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public com.battleship.board.Board getBoard() { return board; }


    protected boolean hasSubmarineOnCoordinate(Coordinate coordinate){
        return board.placeShips(coordinate) == Error.COORDINATE_ALREADY_HAS_SUBMARINE.name();
    }

    protected boolean hasPlayAlreadyBeenMade(Coordinate attackedCoordinate, Board opponentBoard){
        return opponentBoard.receivePlay(attackedCoordinate) == Error.INVALID_PLAY.name();
    }

    public void printPlayerBoard(boolean hideSubmarines){
        getBoard().printDashedLine();
        System.out.println(this.getName() + "'s Board");
        getBoard().printGamingBoard(hideSubmarines);
    }
}
