package com.battleship.players;

import com.battleship.board.Board;
import com.battleship.board.Coordinate;
import com.battleship.board.enums.Error;
import com.battleship.board.services.CoordinateService;

public class Human {
    public int getFleetSize() {
        return fleetSize;
    }

    private int fleetSize = 3;

    public com.battleship.board.Board getBoard() {
        return Board;
    }

    private Board Board = new Board(this.fleetSize);
    private Coordinate coordinate;

    public void setupBoard(){
        Board.createEmptyBoard();

        for (int i = 0; i < this.fleetSize;) {
            System.out.printf("Set a coordinate to place the %dº ship: %n", i + 1);
            coordinate = CoordinateService.createCoordinateByInput();
            if (coordinate.isValid()){
                if(!this.hasSubmarineOnCoordinate(coordinate)){
                    Board.placeShips(coordinate);
                    i++;
                }
            }
        }
    }

    private boolean hasSubmarineOnCoordinate(Coordinate coordinate){
        return Board.placeShips(coordinate) == Error.COORDINATE_ALREADY_HAS_SUBMARINE.name();
    }


    private boolean hasPlayAlreadyBeenMade(Coordinate attackedCoordinate, Board opponentBoard){
        return opponentBoard.receivePlay(attackedCoordinate)==Error.INVALID_PLAY.name();
    }


    public Coordinate makePlay(Board opponentBoard){
        Coordinate attackCoordinate;
        do {
            System.out.printf("Set a coordinate to attack the enemy's Board: %n");
            attackCoordinate = CoordinateService.createCoordinateByInput();
        }
        while(this.hasPlayAlreadyBeenMade(attackCoordinate,opponentBoard));

        return attackCoordinate;
    }

    public void printPlayerBoard(){
        getBoard().printDashedLine();
        System.out.println("Player");
        getBoard().printGamingBoard();
    }

    public static void main(String args[]) {
        Human Test = new Human();
        Bot BotTest = new Bot();

        Test.setupBoard();
        BotTest.setupBoard();

//        Test.HumanBoard.printGamingBoard();

        while (Test.getBoard().getFleetSize() != 0 && BotTest.getBoard().getFleetSize() != 0) {
            System.out.printf("The player attacks %n");

            Coordinate Jogada = Test.makePlay(BotTest.getBoard());
            BotTest.getBoard().receivePlay(Jogada);
            BotTest.getBoard().printGamingBoard();

            System.out.printf("The bot attacks %n");

            Coordinate JogadaBot = BotTest.makePlay(Test.getBoard());
            Test.Board.receivePlay(JogadaBot);
            Test.printPlayerBoard();

            System.out.println(BotTest.getFleetSize());

        }
    }
}
