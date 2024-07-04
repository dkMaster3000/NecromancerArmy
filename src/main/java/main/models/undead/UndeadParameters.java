package main.models.undead;

public record UndeadParameters(String name,
                               int maxhp,
                               int armor,
                               String dmg,
                               int strength,
                               int dexterity,
                               int intelligence,
                               String characteristics,
                               boolean isLeader) {
}
