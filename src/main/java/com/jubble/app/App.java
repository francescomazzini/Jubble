package com.jubble.app;

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
    Settings.getGenerators().get(0).incrementNumberOwned();
    MainThread game = new MainThread();
    Thread t1 = new Thread(game);
    Timer timer = new Timer();
    t1.start();
    timer.schedule(new IncrementValues(), 0, 1000);
  }
}
