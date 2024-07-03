package main.armyview;

public record ArmyStatsRecord(int hp,
                              int armor,
                              int strength,
                              int dexterity,
                              int intelligence,
                              boolean hasLeader,
                              int size) {
}
