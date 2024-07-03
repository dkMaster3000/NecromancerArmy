package main.armyview;

import java.util.HashMap;
import java.util.function.Supplier;

public class ArmyDMGs extends ArmyViewUpdateableComponent {

    Supplier<HashMap<String, String>> getDMGs;

    protected ArmyDMGs(Supplier<HashMap<String, String>> getDMGs) {
        super();

        this.getDMGs = getDMGs;
    }

    @Override
    protected void updateThisComponent() {
        HashMap<String, String> dmgs = getDMGs.get();

        addHeaderLabel("Angriffsmöglichkeiten");

        for (String key : dmgs.keySet()) {
            addEmptyLine();
            addLabel(key + " : " + dmgs.get(key));
        }
    }
}
