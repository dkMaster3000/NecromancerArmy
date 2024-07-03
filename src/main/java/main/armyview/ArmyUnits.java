package main.armyview;

import main.models.undead.Undead;
import org.javatuples.Pair;

import java.awt.*;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ArmyUnits extends ArmyViewUpdateableComponent {

    private final Consumer<Undead> removeUndead;

    Supplier<HashMap<String, Pair<Undead, Integer>>> getUndeadCount;

    public ArmyUnits(Supplier<HashMap<String, Pair<Undead, Integer>>> getUndeadCount, Consumer<Undead> removeUndead) {
        super();

        this.getUndeadCount = getUndeadCount;
        this.removeUndead = removeUndead;
    }

    @Override
    protected void updateThisComponent() {
        HashMap<String, Pair<Undead, Integer>> undeadCount = getUndeadCount.get();

        addHeaderLabel("Armee Einheiten Übersicht:");

        for (String key : undeadCount.keySet()) {

            addEmptyLine();

            ArmyUnit newArmyUnit = new ArmyUnit(undeadCount.get(key), removeUndead);
            newArmyUnit.setAlignmentX(Component.LEFT_ALIGNMENT);
            newArmyUnit.setMaximumSize(new Dimension(Integer.MAX_VALUE, newArmyUnit.getMinimumSize().height));
            add(newArmyUnit);
        }
    }
}
