package com.jubble.app;

import com.jubble.app.classes.Balance;
import com.jubble.app.utils.Settings;
import java.util.Scanner;

public class MainThread implements Runnable {

  private Scanner scan = new Scanner(System.in);
  private Balance balance;

  /** Every time the class is instantiated a new player thread is created. */
  public MainThread(Balance balance) {
    this.balance = balance;
  }

  @Override
  public void run() {
    String choice;
    char c;
    boolean repeat = true;
    boolean badInput = false;

    while (repeat) {
      badInput = false;
      System.out.println("Your balance is " + String.format("%,.2f", balance.getPrimary()) + " $");
      System.out.println(
          "What would you like to do?\n"
              + "- 's' to check the shop\n"
              + "- 'e' to check the exchange\n"
              + "- 'p' to check what properties you own at the moment\n"
              + "- 'q' to quit the game\n");

      choice = scan.next();
      if (choice.length() > 1) {
        badInput = true;
      } else {
        c = choice.charAt(0);

        switch (c) {
          case 's':
            runShop();
            break;

          case 'e':
            System.out.println("This feature is not implemented yet");
            break;

          case 'p':
            System.out.println(Settings.getGenerators());
            break;

          case 'q':
            repeat = false;
            break;

          default:
            badInput = true;
            break;
        }
      }

      if (badInput) {
        System.out.println("\' " + choice + " \' is not a command. Please try Again\n\n");
      }
    }

    System.out.println("Game Finished!");
    App.stop();
  }

  private void runShop() {

    String choice;
    char c;
    boolean repeat = true;
    boolean badInput = false;

    while (repeat) {
      badInput = false;
      System.out.println(
          "Welcome to the shop, your balance is "
              + String.format("%,.2f", balance.getPrimary())
              + "$");
      System.out.print("What would you like to do?\n");
      for (int i = 0; i < Settings.getGenerators().size(); i++)
        System.out.println(
            "- '"
                + i
                + "' to buy '"
                + Settings.getGenerators().get(i).getName()
                + "' which costs "
                + String.format("%,.2f", Settings.getGenerators().get(i).getNextCost())
                + " $");
      System.out.println("- 'q' to exit from the shop");

      choice = scan.next();

      if (choice.length() > 1) {
        badInput = true;
      } else {
        c = choice.charAt(0);

        switch (c) {
          case '0':
          case '1':
          case '2':
          case '3':
          case '4':
          case '5':
            int nr = Integer.parseInt(String.valueOf(c));
            if (balance.getPrimary() > Settings.getGenerators().get(nr).getNextCost()) {
              balance.setPrimary(
                  balance.getPrimary() - Settings.getGenerators().get(nr).getNextCost());
              Settings.getGenerators().get(nr).incrementNumberOwned();

              System.out.println(
                  "You have successfully bought "
                      + Settings.getGenerators().get(nr).getName()
                      + "\n");
            } else {
              System.out.println("You don't have enough money");
            }
          case 'q':
            repeat = false;
            break;

          default:
            badInput = true;
            break;
        }
      }

      if (badInput) {
        System.out.println("\' " + choice + " \' is not a command. Please try Again\n\n");
      }
    }
  }
}
