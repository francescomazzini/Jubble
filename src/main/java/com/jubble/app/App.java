package com.jubble.app;

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
    MainThread game = new MainThread();
    game.run();
  }
}
