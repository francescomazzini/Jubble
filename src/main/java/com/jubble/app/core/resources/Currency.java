package com.jubble.app.core.resources;

/** Defines the shape of a currency of the game. */
public final class Currency {
  private final String name;
  private double owned;

  /**
   * This represent a general currency of the game.
   *
   * @param name of the currency.
   */
  public Currency(String name) {
    this.name = name;
    owned = 0;
  }

  /**
   * Set amount of currency owned by the player.
   *
   * @param owned number of currency to set.
   */
  public void setOwned(double owned) {
    this.owned = owned;
  }

  /**
   * Get amount of currency owned by the player.
   *
   * @return amount of currency owned.
   */
  public double getOwned() {
    return owned;
  }

  @Override
  public String toString() {
    return "Currency: " + name;
  }
}
