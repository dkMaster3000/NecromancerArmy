package main.armyview;

import main.models.undead.Undead;
import org.javatuples.Pair;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class ArmyUnits extends ArmyViewComponentPanel {

    private final List<Undead> undeads;

    private HashMap<String, Pair<Undead, Integer>> undeadCount;

    private final Consumer<Undead> removeUndead;

    public ArmyUnits(List<Undead> undeads, Consumer<Undead> removeUndead) {
        super();
        this.undeads = undeads;
        this.removeUndead = removeUndead;

        undeadCount = new HashMap<>();

        updateArmyUnits();
    }

    public void updateArmyUnits() {
        removeAll();

        updateUndeadCount();

        addHeaderLabel("Armee Einheiten Übersicht:");

        for (String key : undeadCount.keySet()) {

            addEmptyLine(SMALL_LINE_HEIGHT);

            ArmyUnit newArmyUnit = new ArmyUnit(undeadCount.get(key), removeUndead);
            newArmyUnit.setAlignmentX(Component.LEFT_ALIGNMENT);
            newArmyUnit.setMaximumSize(new Dimension(Integer.MAX_VALUE, newArmyUnit.getMinimumSize().height));
            add(newArmyUnit);
        }

        revalidate();
        repaint();
    }

    private void updateUndeadCount() {
        undeadCount = new HashMap<>();

        for (Undead undead : undeads) {
            String undeadName = undead.getName();
            int count = undeadCount.containsKey(undeadName) ? undeadCount.get(undeadName).getValue1() + 1 : 1;

            Pair<Undead, Integer> undeadCountPair = new Pair<>(undead, count);

            undeadCount.put(undeadName, undeadCountPair);
        }
    }
}
