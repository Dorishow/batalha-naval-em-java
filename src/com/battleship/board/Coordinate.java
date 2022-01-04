package com.battleship.board;

public class Coordinate {

    private int line;
    private int column;

    private final int lineFloor = 0;
    private final int lineRoof = 9;
    private final int columnFloor = 0;
    private final int columnRoof = 9;

    public Coordinate(int line, int column){
        this.line = line;
        this.column = column;
    }

    public boolean isOnRoof( ){
        if(this.line <= this.lineRoof && this.column <= this.columnRoof) return true;
        return false;
    }

    public boolean isOnFloor(){
        if(this.line >= this.lineFloor && this.column >= this.columnFloor) return true;
        return false;
    }

    public boolean isValid(){
        if(this.isOnFloor() && this.isOnRoof()) return true;
        return false;
    }

    @Override
    public String toString() {
        return "Coordinate{ " +
                "line: " + line +
                ", column: " + column +
                " }";
    }

    public static void main(String[] args) {

    }
}
