package org.example;

import java.util.ArrayList;
import java.util.Collections;

public class Deck implements DeckActions {
    private ArrayList<Card> myCards;
    private int numCards;

    public Deck() {
        myCards = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        for (String suit : suits) {
            for (String value : values) {
                myCards.add(new Card(suit, value));
            }
        }
        numCards = myCards.size();
    }

    @Override
    public void shuffle() {
        Collections.shuffle(myCards);
    }

    @Override
    public Card dealNextCard() {
        return myCards.remove(0);
    }

    @Override
    public void printDeck(int numToPrint) {
        for (int i = 0; i < numToPrint; i++) {
            System.out.println(myCards.get(i));
        }
    }
}
