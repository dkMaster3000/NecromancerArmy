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

        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        updateArmyStats();
    }

    protected void updateArmyStats() {
        updateValues();

        int SMALL_LINE_HEIGHT = 5;
        int BIG_LINE_HEIGHT = 15;

        addHeaderLabel("Armee Stats:");

        addEmptyLine(SMALL_LINE_HEIGHT);

        addLabel("Anführer: " + (hasLeader ? "Ja" : "Nein"));

        addLabel("Armee Größe: " + undeads.size());

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
            addLabel("<html><p>" + key + " : " + characteristics.get(key) + "</p></html>");
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

    private void addLabel(String text) {
        add(createLabel(text));
    }

    private void addHeaderLabel(String text) {
        JLabel newLabel = createLabel(text);
        newLabel.setFont(new Font("Default font", Font.BOLD, 14));
        add(newLabel);
    }

    private JLabel createLabel(String text) {
        JLabel newLabel = new JLabel(text);
        newLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        newLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, newLabel.getMinimumSize().height));

        return newLabel;
    }


    private void addEmptyLine(int height) {
        add(Box.createRigidArea(new Dimension(1, height)));
    }
}
