package com.battleship.players;

import com.battleship.board.Board;
import com.battleship.board.Coordinate;
import com.utils.RandomNumbersGenerator;

public class Bot extends Player{

    private String name = "Bot";

    @Override
    public String getName() { return name; }

    public void setupBoard(){
       this.getBoard().createEmptyBoard();

        for (int i = 0; i < this.fleetSize; i++) {
            Coordinate coordinate;
            do coordinate = this.generateRandomCoordinate();
            while(this.hasSubmarineOnCoordinate(coordinate));
            this.getBoard().placeShips(coordinate);
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
}

