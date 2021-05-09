package com.jubble.app;

import com.jubble.app.classes.Balance;
import com.jubble.app.classes.Settings;

import java.util.Scanner;
import java.util.Timer;

/**
 * Main app class
 * */
public class App {
  /**
   * Start game main loop
   * */
  public static void main(String[] args) {
    Balance gameBalance = new Balance();
    Settings.getGenerators().get(0).incrementNumberOwned();
    MainThread game = new MainThread(gameBalance);
    Thread t1 = new Thread(game);
    Timer timer = new Timer();
    t1.start();
    timer.schedule(new IncrementValues(gameBalance), 0, 1000);
  }
}
