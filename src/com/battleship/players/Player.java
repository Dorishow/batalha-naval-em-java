package com.battleship.players;

import com.battleship.board.Board;
import com.battleship.board.Coordinate;
import com.battleship.board.enums.Error;

public class Player {
    private final int fleetSize = 3;
    private String name = "Player";
    private final com.battleship.board.Board Board = new Board(this.fleetSize);

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public com.battleship.board.Board getBoard() { return Board; }


    protected boolean hasSubmarineOnCoordinate(Coordinate coordinate){
        return Board.placeShips(coordinate) == Error.COORDINATE_ALREADY_HAS_SUBMARINE.name();
    }

    protected boolean hasPlayAlreadyBeenMade(Coordinate attackedCoordinate, Board opponentBoard){
        return opponentBoard.receivePlay(attackedCoordinate) == Error.INVALID_PLAY.name();
    }

    public void printPlayerBoard(){
        getBoard().printDashedLine();
        System.out.println(this.getName());
        getBoard().printGamingBoard();
    }
}
