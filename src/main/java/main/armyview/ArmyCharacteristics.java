package main.armyview;

import java.util.HashMap;
import java.util.function.Supplier;

public class ArmyCharacteristics extends ArmyViewUpdateableComponent {

    Supplier<HashMap<String, String>> getCharacteristics;

    public ArmyCharacteristics(Supplier<HashMap<String, String>> getCharacteristics) {
        super();

        this.getCharacteristics = getCharacteristics;
    }

    //triggered through ArmyView
    @Override
    protected void updateThisComponent() {
        HashMap<String, String> characteristics = getCharacteristics.get();

        int LABEL_WIDTH = 650;
        addHeaderLabel("Eigenschaften");

        for (String key : characteristics.keySet()) {
            addEmptyLine();

            addLabel("<html><body style='width: " + LABEL_WIDTH + "px;'>" + key + " : " + characteristics.get(key) + "</body></html>");
        }


        addEmptyLine();

        addLabel("<html><body style='width: " + LABEL_WIDTH + "px;'> Umzingeln: Kann ab 6 Einheiten jemanden umzingeln, bekommt Vorteil beim Angriff.</body></html>");

        addEmptyLine();

        addLabel("<html><body style='width: " + LABEL_WIDTH + "px;'> Beschützen: Kann ab 6 Einheiten ein Ziel beschützen und fängt Schaden ab.</body></html>");
    }
}
