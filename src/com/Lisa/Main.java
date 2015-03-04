package com.Lisa;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Initialize deck
        Deck newDeck = new Deck();

        // Initialize players
        HumanPlayer humanPlayer = new HumanPlayer("You");
        ComputerPlayer computerPlayer = new ComputerPlayer("Your cunning opponent");
        Player[] players = {humanPlayer, computerPlayer};

        // Opening deal
        openingDeal(players, newDeck);

        // Game play
        takeTurns(players, newDeck);
    }

    public static void openingDeal(Player[] players, Deck newDeck) {
        for (Player player : players) {
            newDeck.dealCards(10, player.getHandGroup());
        }
    }

    public static void takeTurns(Player[] players, Deck newDeck) {
        while (!newDeck.getStockPile().isEmpty()) {
            for (Player activePlayer : players) {
                activePlayer.outputGameStatus(newDeck);
                newDeck.draw(activePlayer);
                newDeck.meld(activePlayer);
                newDeck.discard(activePlayer);
            }
        }
    }
}

//    public static void calculateScore() {
//        // TODO
//    }
