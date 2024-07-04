package main.models.undead;

public class Undead {
    String name;
    int maxhp;
    int hp;
    int armor;
    String dmg;
    int strength;
    int dexterity;
    int intelligence;
    String characteristics;
    boolean isLeader;

    public Undead(UndeadParameters undeadParameters) {
        this.name = undeadParameters.name();
        this.maxhp = undeadParameters.maxhp();
        this.hp = undeadParameters.maxhp();  // Set hp to maxhp initially
        this.armor = undeadParameters.armor();
        this.dmg = undeadParameters.dmg();
        this.strength = undeadParameters.strength();
        this.dexterity = undeadParameters.dexterity();
        this.intelligence = undeadParameters.intelligence();
        this.characteristics = undeadParameters.characteristics();
        this.isLeader = undeadParameters.isLeader();
    }

    public Undead(Undead undead) {
        this.name = undead.getName();
        this.maxhp = undead.getMaxhp();
        this.hp = undead.getHp();
        this.armor = undead.getArmor();
        this.dmg = undead.getDmg();
        this.strength = undead.getStrength();
        this.dexterity = undead.getDexterity();
        this.intelligence = undead.getIntelligence();
        this.characteristics = undead.getCharacteristics();
        this.isLeader = undead.getIsLeader();
    }

    public String getName() {
        return name;
    }

    public int getMaxhp() {
        return maxhp;
    }

    public int getArmor() {
        return armor;
    }

    public String getDmg() {
        return dmg;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public boolean getIsLeader() {
        return isLeader;
    }

    public int getHp() {
        return hp;
    }
}
