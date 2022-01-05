package com.battleship.board;

import com.battleship.board.enums.CoordinateStates;
import com.battleship.board.enums.Error;
import com.battleship.board.enums.Status;
import com.battleship.board.services.CoordinateService;

public class Board {

    private int gamingBoardLineLength = 10;
    private int gamingBoardColumnLength = 10;

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

    private String receivePlay(Coordinate coordinate){
        if(isPlayInvalid(coordinate)){
            return Error.INVALID_PLAY.name();
        }

        System.out.println(this.playResult(coordinate));

        return Status.PLAY_EXECUTED.name();
    }

    private String playResult(Coordinate coordinate){
       switch (this.gamingBoard[coordinate.getLine()][coordinate.getColumn()]){
            case EMPTY:
                this.gamingBoard[coordinate.getLine()][coordinate.getColumn()] = CoordinateStates.WATER_HIT;
                return Status.WATER_HIT.name();
            case SUBMARINE:
                this.gamingBoard[coordinate.getLine()][coordinate.getColumn()] = CoordinateStates.DESTROYED_SUBMARINE;
                return Status.SUBMARINE_HIT.name();
           default: return "";
        }
    }

    private boolean isEmpty(Coordinate coordinate){
        if (this.gamingBoard[coordinate.getLine()][coordinate.getColumn()] == CoordinateStates.EMPTY){
            return true;
        } else{
            return false;
        }
    }

    private boolean hasSubmarine(Coordinate coordinate){
        if(this.gamingBoard[coordinate.getLine()][coordinate.getColumn()] == CoordinateStates.SUBMARINE){
            return true;
        } else {
            return false;
        }
    }

    private boolean isPlayInvalid(Coordinate coordinate){
        if(!(this.isEmpty(coordinate)||this.hasSubmarine(coordinate))){
            return true;
        } else{
            return false;
        }
    }

    public void printGamingBoard(){
        for (int lineIndex=0; lineIndex<10; lineIndex++){
//            Print line headers and board lines
            System.out.printf(" %C ", CoordinateService.convertNumberToLetter(lineIndex));
            for (int columnIndex=0; columnIndex<10; columnIndex++){
                System.out.printf(" |%C| ", gamingBoard[lineIndex][columnIndex].symbol);
            }
            System.out.printf("%n");
        }
    }


    public static void main(String args[]){

        Board testboard = new Board();

        testboard.createEmptyBoard();
        testboard.printGamingBoard();

    }

}
