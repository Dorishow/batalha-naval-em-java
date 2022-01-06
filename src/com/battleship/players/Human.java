package com.battleship.players;

import com.battleship.board.Board;
import com.battleship.board.Coordinate;
import com.battleship.board.enums.Error;
import com.battleship.board.services.CoordinateService;

public class Human {
    private int fleetSize = 3;
    private Board HumanBoard = new Board();
    private Coordinate coordinate;

    public void setupBoard(){
        HumanBoard.createEmptyBoard();

        for (int i = 0; i < this.fleetSize;) {
            System.out.printf("Set a coordinate to place the %dº ship: %n", i + 1);
            coordinate = CoordinateService.createCoordinateByInput();
            if (coordinate.isValid()){
                if(!this.isPositionAlreadyOccupied(coordinate)){
                    HumanBoard.placeShips(coordinate);
                    i++;
                }
            }
        }
    }

    private boolean isPositionAlreadyOccupied(Coordinate coordinate){
        return HumanBoard.placeShips(coordinate) == Error.COORDINATE_ALREADY_HAS_SUBMARINE.name();
    }


    private boolean hasPlayAlreadyBeenMade(Coordinate attackedCoordinate, Board opponentBoard){
        return opponentBoard.receivePlay(attackedCoordinate)==Error.INVALID_PLAY.name();
    }


    private Coordinate makePlay(Board opponentBoard){
        Coordinate attackCoordinate;

        do {
            System.out.printf("Set a coordinate to attack the enemy's Board: %n");
            attackCoordinate = CoordinateService.createCoordinateByInput();
        }
        while(this.hasPlayAlreadyBeenMade(attackCoordinate,opponentBoard));
        return attackCoordinate;
    }

    public static void main(String args[]) {
        Human Test = new Human();
        Bot BotTest = new Bot();

        Test.setupBoard();

        Test.HumanBoard.printGamingBoard();

        for(int i=0; i<40; i++) {
            Coordinate JogadaBot = Test.makePlay(Test.HumanBoard);
            Test.HumanBoard.receivePlay(JogadaBot);

            Test.HumanBoard.printGamingBoard();
        }
    }
}
