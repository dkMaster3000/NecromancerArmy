package main.armyview;

import main.models.undead.Undead;
import org.javatuples.Pair;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class ArmyUnits extends JPanel {

    private final List<Undead> undeads;

    private final HashMap<String, Pair<Undead, Integer>> undeadCount;

    private final Consumer<Undead> removeUndead;

    public ArmyUnits(List<Undead> undeads, Consumer<Undead> removeUndead) {
        this.undeads = undeads;
        this.removeUndead = removeUndead;

        undeadCount = new HashMap<>();

        setBorder(new EmptyBorder(20, 20, 20, 20));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        updateArmyUnits();

    }

    private void updateArmyUnits() {
        updateUndeadCount();

        //create labels
        JLabel nameLabel = new JLabel("Armee Einheiten Übersicht:");
        add(nameLabel);

        for (String key : undeadCount.keySet()) {

            add(Box.createRigidArea(new Dimension(5, 5)));

            ArmyUnit newArmyUnit = new ArmyUnit(undeadCount.get(key), removeUndead);
            newArmyUnit.setAlignmentX(Component.LEFT_ALIGNMENT);
            add(newArmyUnit);
        }
    }

    private void updateUndeadCount() {
        for (Undead undead : undeads) {
            String undeadName = undead.getName();
            int count = undeadCount.containsKey(undeadName) ? undeadCount.get(undeadName).getValue1() + 1 : 1;

            Pair<Undead, Integer> undeadCountPair = new Pair<>(undead, count);

            undeadCount.put(undeadName, undeadCountPair);
        }
    }

}
