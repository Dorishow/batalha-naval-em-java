package com.battleship.game;

import com.battleship.board.Coordinate;
import com.battleship.game.enums.GameStates;
import com.battleship.players.Bot;
import com.battleship.players.Human;
import com.utils.InputScanner;

public class main{
    private static boolean isGameFinished(Human playerHuman, Bot bot){
        return !(playerHuman.getBoard().getFleetSize() != 0 && bot.getBoard().getFleetSize() != 0);
    }

    private static void declareWinner(Bot bot, Human playerHuman){
        if (playerHuman.getBoard().getFleetSize() == 0)
            System.out.println("Bot WINS");
        else
            System.out.printf("%s WINS", playerHuman.getName());
    }

    public static void main(String args[]){
        GameStates gameState = GameStates.INITIALIZING_GAME;

        Human playerHuman = new Human();
        System.out.println("What's your name Soldier?");
        playerHuman.setName(InputScanner.scan.next());
        Bot bot = new Bot();

        playerHuman.setupBoard();
        bot.setupBoard();

        gameState = GameStates.GAME_RUNNING;

        while (gameState == GameStates.GAME_RUNNING) {
            System.out.printf("The player attacks %n");
            Coordinate Jogada = playerHuman.makePlay(bot.getBoard());
            bot.getBoard().receivePlay(Jogada);
            bot.printPlayerBoard();

            System.out.printf("The bot attacks %n");
            Coordinate JogadaBot = bot.makePlay(playerHuman.getBoard());
            playerHuman.getBoard().receivePlay(JogadaBot);
            playerHuman.printPlayerBoard();

            if (isGameFinished(playerHuman, bot))
            {
                gameState = GameStates.GAME_OVER;
            }
        }
        declareWinner(bot, playerHuman);
}
}
