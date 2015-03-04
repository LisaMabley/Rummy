package com.Lisa;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Initialize deck
        Deck newDeck = new Deck();

//        Doesn't this just create a new instance of each class?
//        Don't we want a LinkedList?
//        Book humanBook = new Book();
//        Book computerBook = new Book();
//
//        Run humanRun = new Run();
//        Run computerRun = new Run();

        // Initialize hands
        HumanPlayer humanPlayer = new HumanPlayer("You");
        ComputerPlayer computerPlayer = new ComputerPlayer("Your cunning opponent");

        // Opening deal
        newDeck.dealCards(10, humanPlayer.getHandGroup());
        newDeck.dealCards(10, computerPlayer.getHandGroup());
        newDeck.dealCards(1, newDeck.getDiscardPile());

        humanPlayer.outputGameStatus(newDeck, computerPlayer);
        draw(humanPlayer, newDeck);
        meld(humanPlayer, newDeck);
        newDeck.discard(humanPlayer);
    }

//    public static void startRound() {
//        // check if discard pile is empty
//        // interactWithHuman()
//        // aiStrategize()
//        // check if any player's hand is empty (they have won)
//    }

    public static void draw(Player player, Deck newDeck) {
        System.out.println("\nDRAW:");
        int drawFromPile = 0;

        // Get valid response from user
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press 1 to draw from the stock pile, or 2 to draw from the discard pile.");

            try {
                drawFromPile = scanner.nextInt();

            } catch (InputMismatchException ime) {
                // User did not input an integer
                continue;
            }

            if (drawFromPile != 1 && drawFromPile != 2) {
                // User input an invalid integer
                continue;
            }

            break;
        }

        // Draw card from selected pile
        switch (drawFromPile) {
            case 1:
                newDeck.drawFromStockPile(player.hand);
                break;
            case 2:
                newDeck.drawFromDiscardPile(player.hand);
                break;
        }
    }

    public static void meld(HumanPlayer player, Deck newDeck) {
        System.out.println("MELD:\nWould you like to meld any runs or books?");
        LinkedList<Card> handCards = player.getHand();

        LinkedList<Card> meldList = new LinkedList<Card>();
        while (true) {
//            outputGameStatus(hand, newDeck, books, runs);
            Card cardToMeld = player.selectCardFromPlayerHand();
            meldList.add(cardToMeld);
            System.out.println("Any more? Enter 0 if you're done");
            Scanner scanner = new Scanner(System.in);
            if (scanner.nextInt() == 0) {
                cardToMeld.outputCardToTerminalInColor();
                break;
            }
            scanner.close();
            System.out.println("CURRENT MELD: ");
            for (Card card : meldList) {
                card.outputCardToTerminalInColor();
                if (card != meldList.getLast()) {
                    System.out.print(", ");
                }
            }
        }

        // Output action
        System.out.print("\nMelded \n");
    }
}

//    public static void calculateScore() {
//        // TODO
//    }
//}
