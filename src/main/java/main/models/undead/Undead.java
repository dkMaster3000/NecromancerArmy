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

    public Undead(String name, int maxhp, int armor, String dmg,
                  int strength, int dexterity, int intelligence,
                  String characteristics, boolean isLeader) {
        this.name = name;
        this.maxhp = maxhp;
        this.hp = maxhp;
        this.armor = armor;
        this.dmg = dmg;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.characteristics = characteristics;
        this.isLeader = isLeader;
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

    public void setHp(int hp) {
        this.hp = hp;
    }
}
