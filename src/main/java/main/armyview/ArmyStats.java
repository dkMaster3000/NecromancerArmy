package main.armyview;

import main.models.undead.Undead;

import java.util.HashMap;
import java.util.List;

public class ArmyStats extends ArmyViewComponentPanel {

    private final List<Undead> undeads;

    private int hp;
    private int armor;
    private int strength;
    private int dexterity;
    private int intelligence;
    private HashMap<String, String> dmgs;
    private HashMap<String, String> characteristics;
    private boolean hasLeader;

    public ArmyStats(List<Undead> undeads) {
        super();
        this.undeads = undeads;

        updateArmyStats();
    }

    protected void updateArmyStats() {
        removeAll();

        updateValues();

        int armySize = undeads.size();

        addHeaderLabel("Armee Stats:");

        addEmptyLine(SMALL_LINE_HEIGHT);

        addLabel("Anführer: " + (hasLeader ? "Ja" : "Nein"));

        addLabel("Armee Größe: " + armySize);

        addLabel("HP: " + hp);

        addLabel("Rüstung: " + armor);

        addLabel("STK: " + strength);

        addLabel("GES: " + dexterity);

        addLabel("INT: " + intelligence);

        addEmptyLine(BIG_LINE_HEIGHT);

        addHeaderLabel("Angriffsmöglichkeiten");

        for (String key : dmgs.keySet()) {
            addEmptyLine(SMALL_LINE_HEIGHT);
            addLabel(key + " : " + dmgs.get(key));
        }

        addEmptyLine(BIG_LINE_HEIGHT);

        addHeaderLabel("Eigenschaften");

        for (String key : characteristics.keySet()) {
            addEmptyLine(SMALL_LINE_HEIGHT);

            addLabel("<html><body style='width: " + 650 + "px;'>" + key + " : " + characteristics.get(key) + "</body></html>");
        }

        if (armySize >= 6) {
            addEmptyLine(SMALL_LINE_HEIGHT);

            addLabel("<html><body style='width: " + 650 + "px;'> Umzingeln: Kann ab 6 Einheiten jemanden umzingeln, bekommt Vorteil beim Angriff.</body></html>");

            addEmptyLine(SMALL_LINE_HEIGHT);

            addLabel("<html><body style='width: " + 650 + "px;'> Beschützen: Kann ab 6 Einheiten ein Ziel beschützen und fängt Schaden ab.</body></html>");
        }

        revalidate();
        repaint();
    }

    private void updateValues() {
        hp = 0;
        armor = 0;
        strength = 0;
        dexterity = 0;
        intelligence = 0;

        dmgs = new HashMap<>();
        characteristics = new HashMap<>();
        hasLeader = false;

        for (Undead undead : undeads) {
            hp += undead.getHp();
            armor += undead.getArmor();
            strength += undead.getStrength();
            dexterity += undead.getDexterity();
            intelligence += undead.getIntelligence();

            String undeadName = undead.getName();
            if (!dmgs.containsKey(undeadName)) {
                dmgs.put(undeadName, undead.getDmg());
            }

            if (!characteristics.containsKey(undeadName)) {
                characteristics.put(undeadName, undead.getCharacteristics());
            }

            if (undead.getIsLeader()) {
                hasLeader = true;
            }
        }

        armor += strength;
    }
}
