package org.example;

import java.util.Scanner;

public class GameRunner {

    public static void main(String[] args) {
        // Play some music
        String filepath = "src\\main\\java\\org\\example\\CasinoJazz.wav";
        PlayMusic music = new PlayMusic();
        music.playMusic(filepath);

        GameRunner game = new GameRunner();
        game.play();
    }

    private Deck deck;
    private Player player;
    private Player dealer;

    public GameRunner() {
        deck = new Deck();
        deck.shuffle();
        player = new Player(1000); // initial balance
        dealer = new Player(0); // dealer does not need a balance
        dealInitialCards();
    }

    private void dealInitialCards() {
        player.addCard(deck.dealNextCard());
        player.addCard(deck.dealNextCard());
        dealer.addCard(deck.dealNextCard());
        dealer.addCard(deck.dealNextCard());
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Your hand: " + player.getHand());
        System.out.println("Dealer's hand: [" + dealer.getHand().get(0) + ", ?]");
        while (player.getTotalValue() < 21) {
            System.out.println("Do you want to hit or stand? (h/s)");
            String action = scanner.nextLine();
            if ("h".equals(action)) {
                player.addCard(deck.dealNextCard());
                System.out.println("Your hand: " + player.getHand());
            } else if ("s".equals(action)) {
                break;
            }
        }
        while (dealer.getTotalValue() < 17) {
            dealer.addCard(deck.dealNextCard());
        }
        System.out.println("Dealer's hand: " + dealer.getHand());
        determineWinner();
        updatePlayerBalance();
        System.out.println("Your new balance is: " + player.getBalance());
    }

    private void determineWinner() {
        int playerTotal = player.getTotalValue();
        int dealerTotal = dealer.getTotalValue();
        if (playerTotal > 21) {
            System.out.println("You bust! Dealer wins.");
        } else if (dealerTotal > 21 || playerTotal > dealerTotal) {
            System.out.println("You win!");
        } else if (playerTotal < dealerTotal) {
            System.out.println("Dealer wins.");
        } else {
            System.out.println("It's a tie.");
        }
    }

    private void updatePlayerBalance() {
        int playerTotal = player.getTotalValue();
        int dealerTotal = dealer.getTotalValue();
        if (playerTotal > 21) {
            player.updateBalance(-100); // loss
        } else if (dealerTotal > 21 || playerTotal > dealerTotal) {
            player.updateBalance(100); // win
        }
    }
}
