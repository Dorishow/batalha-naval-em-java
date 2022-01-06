package com.battleship.board;

public class Coordinate {

    private final int line;
    private final int column;

    private final int lineFloor = 0;
    private final int lineRoof = 9;
    private final int columnFloor = 0;
    private final int columnRoof = 9;

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public Coordinate(int line, int column){
        this.line = line;
        this.column = column;
    }

    public boolean isOnRoof( ){
        return (this.line <= this.lineRoof && this.column <= this.columnRoof);
    }

    public boolean isOnFloor(){
        return (this.line >= this.lineFloor && this.column >= this.columnFloor);
    }

    public boolean isValid(){
        return (this.isOnFloor() && this.isOnRoof());
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
