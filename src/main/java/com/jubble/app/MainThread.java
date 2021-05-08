package com.jubble.app;

import java.util.Scanner;

public class MainThread implements Runnable{

    @Override
    public void run() {
        String choice;
        char c;
        boolean repeat = true;
        boolean badInput = false;
        Scanner scan = new Scanner(System.in);

        while(repeat) {
            badInput = false;
            System.out.println("Your balance is ");
            System.out.println("What would you like to do?\n" +
                    "- 's' to check the shop\n" +
                    "- 'e' to check the exchange\n" +
                    "- 'p' to check what properties you own at the moment\n" +
                    "- 'q' to quit the game\n" );

            choice = scan.next();
            if(choice.length() > 1) {
                badInput = true;
            } else {
                c = choice.charAt(0);

                switch (c) {
                    case 's':
                        break;

                    case 'e':
                        break;

                    case 'p':
                        break;

                    case 'q':
                        repeat = false;
                        break;

                    default:
                        badInput = true;
                        break;
                }
            }

            if(badInput) {
                System.out.println("\' "+choice+" \' is not a command. Please try Again\n\n");
            }
        }

        System.out.println("Game Finished!");
    }

    private void runShop() {
        System.out.println("");
    }
}
