package org.example;

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand;
    private int balance;

    public Player(int initialBalance) {
        hand = new ArrayList<>();
        balance = initialBalance;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getTotalValue() {
        int totalValue = 0;
        int aceCount = 0;
        for (Card card : hand) {
            String value = card.getValue();
            if ("Jack".equals(value) || "Queen".equals(value) || "King".equals(value)) {
                totalValue += 10;
            } else if ("Ace".equals(value)) {
                aceCount++;
                totalValue += 11;
            } else {
                totalValue += Integer.parseInt(value);
            }
        }
        while (aceCount > 0 && totalValue > 21) {
            totalValue -= 10;
            aceCount--;
        }
        return totalValue;
    }

    public int getBalance() {
        return balance;
    }

    public void updateBalance(int amount) {
        balance += amount;
    }
}
