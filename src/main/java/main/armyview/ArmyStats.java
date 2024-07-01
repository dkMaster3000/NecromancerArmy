package main.armyview;

import main.models.undead.Undead;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import java.util.HashMap;
import java.util.List;

public class ArmyStats extends JPanel {

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
        this.undeads = undeads;

        setBorder(new EmptyBorder(20, 20, 20, 20));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        updateArmyStats();
    }

    protected void updateArmyStats() {
        updateValues();

        JLabel nameLabel = new JLabel("Armee Stats:");
        add(nameLabel);

        JLabel leaderLabel = new JLabel("Anführer: " + (hasLeader ? "Ja" : "Nein"));
        add(leaderLabel);

        JLabel armySize = new JLabel("Armee Größe: " + undeads.size());
        add(armySize);

        JLabel hpLabel = new JLabel("HP: " + hp);
        add(hpLabel);

        JLabel armorLabel = new JLabel("Rüstung: " + armor);
        add(armorLabel);

        JLabel strengthLabel = new JLabel("STK: " + strength);
        add(strengthLabel);

        JLabel dexterityLabel = new JLabel("GES: " + dexterity);
        add(dexterityLabel);

        JLabel intelligenceLabel = new JLabel("INT: " + intelligence);
        add(intelligenceLabel);

        addSpace();

        JLabel dmgsHeaderLabel = new JLabel("Angriffsmöglichkeiten");
        add(dmgsHeaderLabel);
        for (String key : dmgs.keySet()) {
            JLabel newDmgLabel = new JLabel(key + " | " + dmgs.get(key));
            add(newDmgLabel);
        }

        addSpace();

        JLabel characteristicsHeaderLabel = new JLabel("Eigenschaften");
        add(characteristicsHeaderLabel);
        for (String key : characteristics.keySet()) {
            JLabel newCharacteristicsLabel = new JLabel("<html><p style=\"width:600px\">" + key + " | " + characteristics.get(key) + "</p></html>");
            add(newCharacteristicsLabel);
        }

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

    public void addSpace() {
        add(Box.createRigidArea(new Dimension(5, 10)));
    }
}
