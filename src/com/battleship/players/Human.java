package com.battleship.players;

import com.battleship.board.Board;
import com.battleship.board.Coordinate;
import com.battleship.board.enums.Error;
import com.battleship.board.services.CoordinateService;
import com.utils.InputScanner;

import java.util.Scanner;

public class Human {

    private final int fleetSize = 3;
    private String name = "Player";
    private final Board Board = new Board(this.fleetSize);

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public com.battleship.board.Board getBoard() { return Board; }

    public void setupBoard(){
        Board.createEmptyBoard();

        for (int i = 0; i < this.fleetSize;) {
            System.out.printf("Set a coordinate to place the %dº ship: %n", i + 1);
            Coordinate coordinate = CoordinateService.createCoordinateByInput();
            if (coordinate.isValid())
                if(!this.hasSubmarineOnCoordinate(coordinate)){
                    Board.placeShips(coordinate);
                    System.out.printf("Ship positioned at coordinate (%s, %d)%n%n",
                            CoordinateService.convertNumberToLetter(coordinate.getLine()),
                            coordinate.getColumn());
                    i++;
                } else System.out.println("This position already has a submarine");;
        }
    }

    private boolean hasSubmarineOnCoordinate(Coordinate coordinate){
        return Board.placeShips(coordinate) == Error.COORDINATE_ALREADY_HAS_SUBMARINE.name();
    }

    private boolean hasPlayAlreadyBeenMade(Coordinate attackedCoordinate, Board opponentBoard){
        return opponentBoard.receivePlay(attackedCoordinate) == Error.INVALID_PLAY.name();
    }

    public Coordinate makePlay(Board opponentBoard){
        Coordinate attackCoordinate;
        System.out.printf("%nSet a coordinate to attack the enemy's Board: %n");
        attackCoordinate = CoordinateService.createCoordinateByInput();
        if (attackCoordinate.isValid())
            if(!this.hasPlayAlreadyBeenMade(attackCoordinate, opponentBoard))
                return attackCoordinate;
            else System.out.println("You already played that coordinate");
        System.out.println("This play is not valid, set a new coordinate to attack the enemy's Board");
        return makePlay(opponentBoard);
    }

    public void printPlayerBoard(){
        getBoard().printDashedLine();
        System.out.println("Player");
        getBoard().printGamingBoard();
    }

    public static void main(String args[]) {
        Human Test = new Human();
        System.out.println("What's your name Soldier?");
        Test.setName(InputScanner.scan.next());
        Bot BotTest = new Bot();

        Test.setupBoard();
        BotTest.setupBoard();

        while (Test.getBoard().getFleetSize() != 0 && BotTest.getBoard().getFleetSize() != 0) {
            System.out.printf("The player attacks %n");
            Coordinate Jogada = Test.makePlay(BotTest.getBoard());
            BotTest.getBoard().receivePlay(Jogada);
            BotTest.getBoard().printGamingBoard();

            System.out.printf("The bot attacks %n");
            Coordinate JogadaBot = BotTest.makePlay(Test.getBoard());
            Test.Board.receivePlay(JogadaBot);
            Test.printPlayerBoard();
        }

        if (Test.getBoard().getFleetSize() == 0)
            System.out.println("Bot ganhou o jogo");
        else
            System.out.printf("%s ganhou o jogo", Test.getName());
    }


}
