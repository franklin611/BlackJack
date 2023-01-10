/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlackJackFinalGame;

import java.util.Scanner;
import java.util.ArrayList;
//The Computer will always know how mnay points I have. It will know my cards. But I will not know one of their cards. 

public class BlackJackFinalGame {

    public static int intNumber;
    public static int counter;

    public static String[] cardsList = {
        "HA", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10", "HJ", "HQ", "HK",
        "CA", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "CJ", "CQ", "CK",
        "SA", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "S10", "SJ", "SQ", "SK",
        "DA", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10", "DJ", "DQ", "DK"};      //This is an arry the reflects a deck of 52 cards. 13 cards from 1 to Ace of Hearts,
    //Clubs, Spades, and Diamonds. 

    public static int[] cardListValues = { //This is an array that reflects the values of the cards above. In this array, we initially set the Ace to 1
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, //However, I will create if statements that change the value to 1 or 11 depending on the situation
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10,
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10,
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10,};

    public static int playingNumber() {
        Scanner input = new Scanner(System.in);
        System.out.print("How many cards would you like to play with : ");
        int intNumber = input.nextInt();
        return intNumber;

    }

    public static ArrayList<String> playerHand = new ArrayList<String>();    //This will be the player's hand
    public static ArrayList<String> DealerHand = new ArrayList<String>();
    public static ArrayList<Integer> playerHandValues = new ArrayList<Integer>();
    public static ArrayList<Integer> DealerHandValues = new ArrayList<Integer>();

    public static int getRandomCardDealer() {
        int intCardIndex = (int) (Math.random() * cardListValues.length);        //This gets a random number to be pulled between 0 and cardsList.length
        //This strCard will hold the string at cardsList[intX]

        int intTemp = cardListValues[intCardIndex];
        if (intTemp == 1) {
            intTemp = 11;

            int intTotal = DealerTotal() + intTemp;
            if (intTotal <= 21) {
                intTemp = 11;
            } else {
                intTemp = 1;
            }
        }
        cardListValues[intCardIndex] = -1;
        if (intTemp != -1) {
            DealerHandValues.add(intTemp);
            return intCardIndex;
        } else {
            getRandomCardDealer();
            return intCardIndex;
        }
    }

    public static int getRandomCardPlayer() {
        int intCardIndex = (int) (Math.random() * cardListValues.length);        //This gets a random number to be pulled between 0 and cardsList.length
        int intTemp = cardListValues[intCardIndex];
        if (intTemp == 1) {
            handDisplay();
            Scanner input = new Scanner(System.in);
            int intCounter = 1;
            while (intCounter != 0) {
                System.out.println("Would you like the ace to be 11 or 1? :");
                intNumber = input.nextInt();
                if (intNumber == 1 || intNumber == 11) {
                    intCounter = 0;
                }
                System.out.println("Please enter a valid Answer : ");
            }
            intTemp = intNumber;
        }
        cardListValues[intCardIndex] = -1;
        if (intTemp > 0) {
            playerHandValues.add(intTemp);
            return intCardIndex;
        } else {
            getRandomCardPlayer();
            return intCardIndex;
        }
    }

    public static int PlayerTotal() {
        int intTotal = 0;
        for (int i = 0; i < playerHandValues.size(); i++) {
            intTotal = intTotal + playerHandValues.get(i);
        }
        return intTotal;
    }

    public static int DealerTotal() {
        int intTotal = 0;
        for (int i = 0; i < DealerHandValues.size(); i++) {
            intTotal = intTotal + DealerHandValues.get(i);
        }
        return intTotal;
    }

    public static void addCardPlayer() {
        playerHand.add(cardsList[getRandomCardPlayer()]);
    }

    public static void addCardComputer() {
        DealerHand.add(cardsList[getRandomCardDealer()]);
    }

    public static void handDisplay() {
        if (intNumber == 1) {
            System.out.println("");
            System.out.println("Dealer : ");
            System.out.println("[" + DealerHand.get(0) + "]");
            System.out.println("Dealer's Points : YY");
            System.out.println("Player : ");
            System.out.println(playerHand);
            System.out.println("Player's Points : " + PlayerTotal() + "\n");
        } else {
            System.out.println("");
            System.out.println("Dealer : ");
            System.out.println("[" + DealerHand.get(1) + ", XX]");
            System.out.println("Dealer's Points : YY");
            System.out.println("Player : ");
            System.out.println(playerHand);
            System.out.println("Player's Points : " + PlayerTotal() + "\n");

        }
    }

    public static void DealerPlays() {
        int size = DealerHand.size();
        if (DealerTotal() < 18 && size < intNumber) {
            addCardComputer();
            DealerPlays();
        }
    }

    public static String comparePoints() {
        if (DealerTotal() > PlayerTotal() && DealerTotal() <= 21 || PlayerTotal() > 21) {
            String strWinner = ("Computer Won");
            System.out.println(DealerHand);
            System.out.println("Dealer's Points : " + DealerTotal());
            System.out.println(playerHand);
            System.out.println("Player's Points : " + PlayerTotal());
            return strWinner;
        }
        if (DealerTotal() == PlayerTotal()) {
            String strWinner = ("Tie. No one won.");
            System.out.println("Player's Points : " + PlayerTotal());
            System.out.println("Dealer's Points : " + DealerTotal());
            return strWinner;
        } else {
            String strWinner = ("Player Won");
            System.out.println(DealerHand);
            System.out.println("Dealer's Points : " + DealerTotal());
            System.out.println(playerHand);
            System.out.println("Player's Points : " + PlayerTotal());
            return strWinner;
        }
    }

    public static void HitStandQuit() {
        while (counter != 0) {
            Scanner input = new Scanner(System.in);
            System.out.print("Would you like to Hit, Stand, or Quit? : ");
            String strAnswer = input.next();
            switch (strAnswer.toLowerCase()) {
                case "hit":
                    addCardPlayer();
                    handDisplay();
                    if (PlayerTotal() > 21) {
                        System.out.println("");
                        counter--;
                        break;
                    }
                    if (playerHand.size() >= intNumber) {
                        counter--;
                        System.out.println("");
                    } else {
                        counter--;
                        HitStandQuit();
                    }
                    break;
                case "stand":
                    counter = counter - 1;

                    handDisplay();
                    HitStandQuit();

                    break;
                case "quit":

                    System.out.println("Thank you for playing.");
                    counter = 0;
                    break;
                default:
                    System.out.println("Please enter a valid Answer : ");
                    HitStandQuit();
                    break;
            }
            System.out.println(comparePoints());
        }
    }

    public static void BlackJackGame() {
        intNumber = playingNumber();
        counter = intNumber - 1;

        if (intNumber == 0) {
            System.out.println("Thank you for playing.");
        } else {
            addCardPlayer();
            addCardComputer();
            DealerPlays();
            handDisplay();
            if (playerHand.size() < intNumber) {
                HitStandQuit();
            } else {
                System.out.println(comparePoints());
            }

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BlackJackGame();
    }
}
