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

        // Initialize hands
        HumanPlayer humanPlayer = new HumanPlayer("You");
        ComputerPlayer computerPlayer = new ComputerPlayer("Your cunning opponent");

        // Opening deal
        newDeck.dealCards(10, humanPlayer.getHandGroup());
        newDeck.dealCards(10, computerPlayer.getHandGroup());

        humanPlayer.outputGameStatus(newDeck);
        newDeck.draw(humanPlayer);
//        meld(humanPlayer, newDeck);
        newDeck.discard(humanPlayer);
        newDeck.draw(computerPlayer);
        newDeck.discard(computerPlayer);
    }

//    public static void startRound() {
//        // check if discard pile is empty
//        // interactWithHuman()
//        // aiStrategize()
//        // check if any player's hand is empty (they have won)
//    }

//    public static void meld(HumanPlayer player, Deck newDeck) {
//        System.out.println("MELD:\nWould you like to meld any runs or books?");
//        LinkedList<Card> handCards = player.getHand();
//
//        LinkedList<Card> meldList = new LinkedList<Card>();
//        while (true) {
////            outputGameStatus(hand, newDeck, books, runs);
//            Card cardToMeld = player.selectCardFromPlayerHand();
//            meldList.add(cardToMeld);
//            System.out.println("Any more? Enter 0 if you're done");
//            Scanner scanner = new Scanner(System.in);
//            if (scanner.nextInt() == 0) {
//                cardToMeld.outputCardToTerminalInColor();
//                break;
//            }
//            scanner.close();
//            System.out.println("CURRENT MELD: ");
//            for (Card card : meldList) {
//                card.outputCardToTerminalInColor();
//                if (card != meldList.getLast()) {
//                    System.out.print(", ");
//                }
//            }
//        }
//
//        // Output action
//        System.out.print("\nMelded \n");
//    }
}

//    public static void calculateScore() {
//        // TODO
//    }
//}
