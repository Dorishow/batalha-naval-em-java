package com.battleship.board;

import com.battleship.board.enums.CoordinateStates;
import com.battleship.board.enums.Error;
import com.battleship.board.enums.Status;
import com.battleship.board.services.CoordinateService;

public class Board {

    private int gamingBoardLineLength = 10;
    private int gamingBoardColumnLength = 10;

    public int getFleetSize() {
        return fleetSize;
    }

    private int fleetSize;

    public Board(int fleetSize){
        this.fleetSize = fleetSize;
    }

    private CoordinateStates[][] gamingBoard = new CoordinateStates[this.gamingBoardLineLength][this.gamingBoardColumnLength];

    public void createEmptyBoard(){
        for (int lineIndex = 0; lineIndex < this.gamingBoardLineLength; lineIndex++) {
            for (int columnIndex = 0; columnIndex < this.gamingBoardColumnLength; columnIndex++) {
                this.gamingBoard[lineIndex][columnIndex] = CoordinateStates.EMPTY;
            }
        }
    }

    public String placeShips(Coordinate coordinate){
        if(this.hasSubmarine(coordinate)){
            return Error.COORDINATE_ALREADY_HAS_SUBMARINE.name();
        }

        this.gamingBoard[coordinate.getLine()][coordinate.getColumn()] = CoordinateStates.SUBMARINE;
        return Status.SUBMARINE_PLACED.name();
    }

    public String receivePlay(Coordinate coordinate){
        if(isPlayInvalid(coordinate)){
            return Error.INVALID_PLAY.name();
        }

        System.out.printf("Enemy shooted on (%s, %d) and %s%n",
                CoordinateService.convertNumberToLetter(coordinate.getLine()),
                coordinate.getColumn(),
                this.playResult(coordinate));

        return Status.PLAY_EXECUTED.name();
    }

    private String playResult(Coordinate coordinate){
       switch (this.gamingBoard[coordinate.getLine()][coordinate.getColumn()]){
            case EMPTY:
                this.gamingBoard[coordinate.getLine()][coordinate.getColumn()] = CoordinateStates.WATER_HIT;
                return Status.WATER_HIT.message;
            case SUBMARINE:
                this.gamingBoard[coordinate.getLine()][coordinate.getColumn()] = CoordinateStates.DESTROYED_SUBMARINE;
                this.fleetSize--;
                return Status.SUBMARINE_HIT.message;
           default: return "";
        }
    }

    private boolean isEmpty(Coordinate coordinate){
        return (this.gamingBoard[coordinate.getLine()][coordinate.getColumn()] == CoordinateStates.EMPTY);
    }

    private boolean hasSubmarine(Coordinate coordinate){
        return (this.gamingBoard[coordinate.getLine()][coordinate.getColumn()] == CoordinateStates.SUBMARINE);
    }

    private boolean isPlayInvalid(Coordinate coordinate){
        return (!(this.isEmpty(coordinate)||this.hasSubmarine(coordinate)));
    }

    public void printGamingBoard(){

        printDashedLine();
        printTableHeadder();
        for (int lineIndex=0; lineIndex<10; lineIndex++){
        printDashedLine();
        System.out.printf("|  %C  |", CoordinateService.convertNumberToLetter(lineIndex));
            for (int columnIndex=0; columnIndex<10; columnIndex++){
                System.out.printf("  %C  |", gamingBoard[lineIndex][columnIndex].symbol);
            }
            System.out.printf("%n");
        }
        printDashedLine();
        System.out.printf("%n");
    }

    public void printTableHeadder(){
        System.out.println("|     |  0  |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  |");
    }

    public void printDashedLine(){
        System.out.println("-------------------------------------------------------------------");
    }

    public static void main(String args[]){

        Board testboard = new Board(10);

        testboard.createEmptyBoard();
        testboard.printGamingBoard();

        Coordinate coordinate = new Coordinate(0,0);

        System.out.println(testboard.isPlayInvalid(coordinate));

        testboard.receivePlay(coordinate);

        System.out.println(testboard.isPlayInvalid(coordinate));

        testboard.printGamingBoard();

        Coordinate coordinate2 = new Coordinate(4,2);

        testboard.placeShips(coordinate2);

        testboard.printGamingBoard();

        testboard.receivePlay(coordinate2);

        testboard.printGamingBoard();
    }
}
