package com.battleship.players;

import com.battleship.board.Board;
import com.battleship.board.Coordinate;
import com.battleship.board.enums.Error;
import com.utils.RandomNumbersGenerator;

public class Bot {

    public int getFleetSize() {
        return fleetSize;
    }

    private int fleetSize = 10;

    public Board getBoard() {
        return Board;
    }

    private Board Board = new Board(this.fleetSize);
    private Coordinate coordinate;

    public void setupBoard(){
       Board.createEmptyBoard();

        for (int i = 0; i < this.fleetSize; i++) {
            do coordinate = this.generateRandomCoordinate();
            while(this.isPositionAlreadyOccupied(coordinate));
            Board.placeShips(coordinate);
        }
    }


    public Coordinate makePlay(Board opponentBoard){
        Coordinate attackCoordinate = this.generateRandomCoordinate();
        while(this.hasPlayAlreadyBeenMade(attackCoordinate,opponentBoard)){
            attackCoordinate = this.generateRandomCoordinate();
        }
        return attackCoordinate;
    }


    private Coordinate generateRandomCoordinate(){
        return new Coordinate(RandomNumbersGenerator.generateRandomNumber.nextInt(10),RandomNumbersGenerator.generateRandomNumber.nextInt(10));
    }


    private boolean isPositionAlreadyOccupied(Coordinate coordinate){
        return Board.placeShips(coordinate) == Error.COORDINATE_ALREADY_HAS_SUBMARINE.name();
    }


    private boolean hasPlayAlreadyBeenMade(Coordinate attackedCoordinate, Board opponentBoard){
        return opponentBoard.receivePlay(attackedCoordinate) == Error.INVALID_PLAY.name();
    }


    public static void main(String args[]) {
        Bot botTest = new Bot();

        botTest.setupBoard();

        botTest.Board.printGamingBoard();

        for(int i=0; i<40; i++) {
            Coordinate JogadaBot = botTest.makePlay(botTest.Board);
            botTest.Board.receivePlay(JogadaBot);

            botTest.Board.printGamingBoard();
        }
    }
}

