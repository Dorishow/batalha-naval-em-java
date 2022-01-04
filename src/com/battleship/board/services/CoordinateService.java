package com.battleship.board.services;

import com.battleship.board.Coordinate;
import com.battleship.board.Error;
import com.utils.InputScanner;

public class CoordinateService {
    public static Coordinate createCoordinateByInput(){

        String formatedInput = coordinateInputHandler(InputScanner.scan.next());
        if(formatedInput != Error.INVALID_INPUT.name()){
            int line = Character.getNumericValue(formatedInput.charAt(0));
            int column = Character.getNumericValue(formatedInput.charAt(1));
            return new Coordinate(line, column);
        }
        else System.out.println("Invalid input, please insert another value");

        return new Coordinate(-1, -1);
    }


    public static String coordinateInputHandler(String coordinateString){

        if (coordinateString.isBlank() || coordinateString.length() < 2)
            return Error.INVALID_INPUT.name();

        char line = Character.toUpperCase(coordinateString.charAt(0));
        int column = Character.getNumericValue(coordinateString.charAt(1));

        if (column < 0 || column > 9)
            return Error.INVALID_INPUT.name();

        if(convertLetterToNumber(line) != Error.INVALID_LETTER.name())
            return convertLetterToNumber(line) + coordinateString.charAt(1);

        return Error.INVALID_INPUT.name();
    }


    public static String convertLetterToNumber(char letter){
        switch (letter){
            case 'A': return  "0";
            case 'B': return "1";
            case 'C': return "2";
            case 'D': return "3";
            case 'E': return "4";
            case 'F': return "5";
            case 'G': return "6";
            case 'H': return "7";
            case 'I': return "8";
            case 'J': return "9";
            default: return Error.INVALID_LETTER.name();
        }
    }


    public static void main(String[] args) {
        System.out.println("Indique a coordenada");
        Coordinate NewCoordinate = createCoordinateByInput();
        System.out.println(NewCoordinate);
        System.out.println("is valid: " + NewCoordinate.isValid());
    }
}
