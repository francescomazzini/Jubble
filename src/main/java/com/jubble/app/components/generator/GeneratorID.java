package com.jubble.app.components.generator;

public class GeneratorID {
  /** Represents the name of this generator. */
  private final String name;
  /** Represents the description of this generator. */
  private final String description;
  /** Represents a level (or progress) in the game. */
  private final int level;
  /**
   * Represent the unique name and description of a generator.
   *
   * @param name name of the generator.
   * @param description description of the generator.
   * @param level game level.
   */
  public GeneratorID(String name, String description, int level) {
    this.name = name;
    this.description = description;
    this.level = level;
  }

  public GeneratorID(GeneratorID id) {
    this.name = id.getName();
    this.description = id.getDescription();
    this.level = id.getLevel();
  }

  /**
   * Returns the name.
   *
   * @return name of this generator.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the description.
   *
   * @return description of this generator.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Returns the level.
   *
   * @return level represented by this generator.
   */
  public int getLevel() {
    return level;
  }

  @Override
  public String toString() {
    return "Generator: " + name + " : " + description;
  }
}
