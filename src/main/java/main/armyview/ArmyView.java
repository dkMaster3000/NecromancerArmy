package main.armyview;

import main.models.undead.Undead;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ArmyView extends JPanel {

    private final List<Undead> undeads = new ArrayList<>();

    public ArmyView() {
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        updateArmyView();
    }

    private void updateArmyView() {
        removeAll();

        ArmyStats armyStats = new ArmyStats(undeads);
        add(armyStats);

        ArmyUnits armyUnits = new ArmyUnits(undeads, this::removeUndead);
        add(armyUnits);

        revalidate();
        repaint();
    }

    private void addUndead(Undead undead) {
        Undead newUndead = new Undead(undead); //create copy
        undeads.add(newUndead);

        updateArmyView();
    }

    private void removeUndead(Undead undead) {
        undeads.remove(undead);

        updateArmyView();
    }

    public Consumer<Undead> getAddToArmyFunction() {
        return this::addUndead;
    }
}
