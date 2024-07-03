package main.armyview;

import java.util.function.Supplier;

public class ArmyStats extends ArmyViewUpdateableComponent {

    Supplier<ArmyStatsRecord> getArmyStatsRecord;

    protected ArmyStats(Supplier<ArmyStatsRecord> getArmyStatsRecord) {
        super();

        this.getArmyStatsRecord = getArmyStatsRecord;
    }

    @Override
    protected void updateThisComponent() {
        ArmyStatsRecord armyStatsRecord = getArmyStatsRecord.get();

        int armySize = armyStatsRecord.size();

        addHeaderLabel("Armee Stats:");

        addEmptyLine();

        addLabel("Anführer: " + (armyStatsRecord.hasLeader() ? "Ja" : "Nein"));

        addLabel("Armee Größe: " + armySize);

        addLabel("HP: " + armyStatsRecord.hp());

        addLabel("Rüstung: " + armyStatsRecord.armor());

        addLabel("STK: " + armyStatsRecord.strength());

        addLabel("GES: " + armyStatsRecord.dexterity());

        addLabel("INT: " + armyStatsRecord.intelligence());
    }
}
