package com.battleship.board;

import com.battleship.board.enums.CoordinateStates;
import com.battleship.board.enums.Error;
import com.battleship.board.enums.Status;
import com.battleship.board.services.CoordinateService;

public class Board {

    private int gamingBoardLineLength = 10;
    private int gamingBoardColumnLength = 10;
    private int fleetSize;

    public Board(int fleetSize){ this.fleetSize = fleetSize; }

    public int getFleetSize() { return fleetSize; }

    private CoordinateStates[][] gamingBoard = new CoordinateStates[this.gamingBoardLineLength][this.gamingBoardColumnLength];

    public void createEmptyBoard(){
        for (int lineIndex = 0; lineIndex < this.gamingBoardLineLength; lineIndex++)
            for (int columnIndex = 0; columnIndex < this.gamingBoardColumnLength; columnIndex++)
                this.gamingBoard[lineIndex][columnIndex] = CoordinateStates.EMPTY;
    }

    public String placeShips(Coordinate coordinate){
        if(this.hasSubmarine(coordinate)) return Error.COORDINATE_ALREADY_HAS_SUBMARINE.name();
        this.gamingBoard[coordinate.getLine()][coordinate.getColumn()] = CoordinateStates.SUBMARINE;
        return Status.SUBMARINE_PLACED.name();
    }

    public String receivePlay(Coordinate coordinate){
        if(isPlayInvalid(coordinate)) return Error.INVALID_PLAY.name();
        System.out.printf("A shot hitted the board on (%s, %d) and %s%n",
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

    public void printGamingBoard(boolean hideSubmarines){
        printDashedLine();
        printTableHeadder();
        if (hideSubmarines)
            printCensoredBoard();
        else
            printCompleteBoard();
        printDashedLine();
        System.out.printf("%n");
    }

    private void printCensoredBoard(){
        for (int lineIndex = 0; lineIndex < this.gamingBoardLineLength; lineIndex++){
            printDashedLine();
            System.out.printf("|  %C  |", CoordinateService.convertNumberToLetter(lineIndex));
            for (int columnIndex=0; columnIndex<10; columnIndex++)
                if (gamingBoard[lineIndex][columnIndex].symbol != CoordinateStates.SUBMARINE.symbol)
                    System.out.printf("  %C  |", gamingBoard[lineIndex][columnIndex].symbol);
                else System.out.printf("  %C  |", CoordinateStates.EMPTY.symbol);
            System.out.printf("%n");
        }
    }

    private void printCompleteBoard(){
        for (int lineIndex = 0; lineIndex < this.gamingBoardLineLength; lineIndex++){
            printDashedLine();
            System.out.printf("|  %C  |", CoordinateService.convertNumberToLetter(lineIndex));
            for (int columnIndex=0; columnIndex<10; columnIndex++)
                System.out.printf("  %C  |", gamingBoard[lineIndex][columnIndex].symbol);
            System.out.printf("%n");
        }
    }

    public void printTableHeadder(){
        System.out.println("|     |  0  |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  |");
    }

    public void printDashedLine(){
        System.out.println("-------------------------------------------------------------------");
    }
}
