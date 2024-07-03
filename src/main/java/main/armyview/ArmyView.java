package main.armyview;

import main.models.undead.Undead;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ArmyView extends ArmyViewComponentPanel {

    private final List<Undead> undeads = new ArrayList<>();

    public ArmyView() {
        super();

        updateArmyView();
    }

    private void updateArmyView() {
        removeAll();

        ArmyStats armyStats = new ArmyStats(undeads);
        armyStats.setAlignmentX(Component.LEFT_ALIGNMENT);
        armyStats.setMaximumSize(new Dimension(Integer.MAX_VALUE, armyStats.getMinimumSize().height));
        add(armyStats);

        addEmptyLine(5);

        ArmyUnits armyUnits = new ArmyUnits(undeads, this::removeUndead);
        armyUnits.setAlignmentX(Component.LEFT_ALIGNMENT);
        armyUnits.setMaximumSize(new Dimension(Integer.MAX_VALUE, armyUnits.getMinimumSize().height));

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
