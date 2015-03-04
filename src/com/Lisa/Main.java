package com.Lisa;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Initialize deck
        Deck newDeck = new Deck();

//        JESSE Doesn't this just create a new instance of each class?
//        Don't we want a LinkedList?
//        Book humanBook = new Book();
//        Book computerBook = new Book();
//
//        Run humanRun = new Run();
//        Run computerRun = new Run();

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
        while (true) {
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
