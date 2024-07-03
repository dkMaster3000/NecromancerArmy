package main.armyview;

import main.models.undead.Undead;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class ArmyView extends ArmyViewComponentPanel {

    private final List<Undead> undeads = new ArrayList<>();

    ArmyStats armyStats;
    ArmyUnits armyUnits;
    ArmyDMGs armyDMGs;
    ArmyCharacteristics armyCharacteristics;

    private HashMap<String, String> dmgs;
    private HashMap<String, String> characteristics;

    private HashMap<String, Pair<Undead, Integer>> undeadCount;

    private ArmyStatsRecord armyStatsRecord;

    private final List<ArmyViewUpdateableComponent> updateableComponents = new ArrayList<>();


    public ArmyView() {
        super();

        createArmyView();
    }

    private void createArmyView() {

        ArmyTracker armyTracker = new ArmyTracker();
        addBoxLayoutAlignment(armyTracker);
        add(armyTracker);

        armyStats = new ArmyStats(this::getArmyStatsRecord);
        updateableComponents.add(armyStats);

        armyUnits = new ArmyUnits(this::getUndeadCount, this::removeUndead);
        updateableComponents.add(armyUnits);

        armyDMGs = new ArmyDMGs(this::getDmgs);
        updateableComponents.add(armyDMGs);

        armyCharacteristics = new ArmyCharacteristics(this::getCharacteristics);
        updateableComponents.add(armyCharacteristics);

        for (ArmyViewUpdateableComponent updateableComponent : updateableComponents) {
            addEmptyLine();
            add(updateableComponent);
        }

        updateArmyView();
    }

    private void updateArmyView() {
        calculateUndeadData();

        for (ArmyViewUpdateableComponent updateableComponent : updateableComponents) {
            updateableComponent.updateComponent();
            addBoxLayoutAlignment(updateableComponent);
        }

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

    private ArmyStatsRecord getArmyStatsRecord() {
        return armyStatsRecord;
    }

    private HashMap<String, Pair<Undead, Integer>> getUndeadCount() {
        return undeadCount;
    }

    private HashMap<String, String> getDmgs() {
        return dmgs;
    }

    private HashMap<String, String> getCharacteristics() {
        return characteristics;
    }


    //calculate all data -> for performance
    private void calculateUndeadData() {
        int hp = 0;
        int armor = 0;
        int strength = 0;
        int dexterity = 0;
        int intelligence = 0;
        boolean hasLeader = false;

        dmgs = new HashMap<>();
        characteristics = new HashMap<>();

        undeadCount = new HashMap<>();

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

            //for undeadCount
            int count = undeadCount.containsKey(undeadName) ? undeadCount.get(undeadName).getValue1() + 1 : 1;

            Pair<Undead, Integer> undeadCountPair = new Pair<>(undead, count);

            undeadCount.put(undeadName, undeadCountPair);
        }

        armor += strength;

        armyStatsRecord = new ArmyStatsRecord(hp, armor, strength, dexterity, intelligence, hasLeader, undeads.size());
    }
}
