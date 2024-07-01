package main.armyview;

import main.models.undead.Undead;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class ArmyUnits extends JPanel {

    private final List<Undead> undeads;

    private final HashMap<String, Integer> undeadCount;

    public ArmyUnits(List<Undead> undeads) {
        this.undeads = undeads;

        undeadCount = new HashMap<>();

        setBorder(new EmptyBorder(20, 20, 20, 20));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        updateArmyServants();
    }

    private void updateArmyServants() {

        //fill hashmap
        for (Undead undead : undeads) {
            String undeadName = undead.getName();
            undeadCount.merge(undeadName, 1, Integer::sum);
        }

        //create labels
        JLabel nameLabel = new JLabel("Armee Einheiten Übersicht:");
        add(nameLabel);

        for (String key : undeadCount.keySet()) {

            add(Box.createRigidArea(new Dimension(5, 5)));

            JLabel newJLabel = new JLabel(undeadCount.get(key) + "x" + key);
            add(newJLabel);
        }
    }
}
