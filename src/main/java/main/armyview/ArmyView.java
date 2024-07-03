package main.armyview;

import main.models.undead.Undead;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ArmyView extends ArmyViewComponentPanel {

    private final List<Undead> undeads = new ArrayList<>();

    ArmyStats armyStats;
    ArmyUnits armyUnits;


    public ArmyView() {
        super();

        createArmyView();
    }

    private void createArmyView() {


        armyStats = new ArmyStats(undeads);
        addBoxLayoutAlignment(armyStats);
        add(armyStats);

        addEmptyLine(SMALL_LINE_HEIGHT);

        armyUnits = new ArmyUnits(undeads, this::removeUndead);
        addBoxLayoutAlignment(armyUnits);
        add(armyUnits);

        addEmptyLine(SMALL_LINE_HEIGHT);

        ArmyTracker armyTracker = new ArmyTracker();
        addBoxLayoutAlignment(armyTracker);
        add(armyTracker);
    }

    private void updateArmyView() {
        armyStats.updateArmyStats();
        addBoxLayoutAlignment(armyStats);
        armyUnits.updateArmyUnits();
        addBoxLayoutAlignment(armyUnits);

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
