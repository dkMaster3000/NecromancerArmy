package main.modelsview;

import main.modelsview.uicomponents.ModelPanel;
import main.modelsview.uicomponents.UndeadCreator;
import main.models.undead.Undead;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ModelsView extends JPanel {

    private final List<Undead> undeads = new ArrayList<>();

    Consumer<Undead> addToArmyFunction;

    private final int SPACING = 10;

    final Undead zombie = new Undead("Zombie",
            10, 0, "1d4 Pro Einheit + STK des Stacks",
            1, 0, 0,
            " infizieren Ziele, bei Tod eines infizierten Ziels wird die Leiche dem Stack hinzugefügt", false);
    final Undead skelettalWarrior = new Undead("Skelettkrieger",
            5, 0, "1d6 Pro Einheit + STK des Stacks",
            1, 0, 0,
            "10% DR", false);
    final Undead skelettalArcher = new Undead("Skelettschütze",
            5, 0, "1d8 Pro Einheit + GES des Stacks, Fernangriff (7 Range)",
            0, 1, 0,
            "20% Dodge", false);
    final Undead banshee = new Undead("Banhee",
            10, 0, "Seelenentzug: Fügt dem Ziel 1d20+Wissen des Stacks Schaden zu, 7 Range",
            0, 0, 2,
            "Das Ziel ist markiert und die Seele des Ziels wird als Banshee dem Stack nach dem Tod hinzugefügt", false);
    final Undead reaper = new Undead("Sensenmann",
            80, 15, "1d12 + GES des Stacks",
            0, 4, 0,
            "Ermöglicht fliegen, Angriffe fügen 15% des gewürfelten Schadens als zusätzlichen True Damage Schaden zu, Getötete Ziele werden zu Banshees",
            true);
    final Undead lich = new Undead("Lich",
            80, 15, "Fernangriff: Feuert einen nekrotischen Strahl welcher 1d20+Wissen des Stacks Schaden verursacht und die Rüstung des Ziel um 1d20 senkt",
            0, 0, 4,
            "Kann Zombies oder Skelette dem Stack hinzufügen",
            true);
    final Undead monstro = new Undead("Monstrosität",
            120, 20, "1d20+Stärke des Stacks",
            6, 0, 0,
            "Feinde in der Nähe der Monstrosität werden infiziert, infizierte Feinde erhalten zu Beginn jeder Runde 5% MAX HP Schaden, der Stack erhält DR und macht % mehr Schaden in Höhe der Gesamteinheiten im Stack(Max 30%)",
            true);
    final Undead boneDragon = new Undead("Knochendrache",
            120, 20, "1d20+STK+INT+Ges des Stacks",
            2, 2, 2,
            "Ermöglicht fliegen, Angriffe verlangsamen Gegner um 1, Getötete Feinde werden als Skelettkrieger oder Skelettschütze dem Stack hinzugefügt, Stackeigenschaften von Skeletten werde verdoppelt, Schaden wird mit jeder Einheit im Stack um 2% erhöht (Max 50)",
            true);

    public ModelsView(Consumer<Undead> addToArmyFunction) {
        this.addToArmyFunction = addToArmyFunction;

        setBorder(new EmptyBorder(SPACING, SPACING, SPACING, SPACING));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        undeads.add(zombie);
        undeads.add(skelettalWarrior);
        undeads.add(skelettalArcher);
        undeads.add(banshee);
        undeads.add(reaper);
        undeads.add(lich);
        undeads.add(monstro);
        undeads.add(boneDragon);

        updateModelsView();
    }

    private void updateModelsView() {
        removeAll();

        for (Undead undead : undeads) {
            ModelPanel newModelPanel = new ModelPanel(undead, addToArmyFunction, this::removeUndead);

            add(newModelPanel);

            add(Box.createVerticalStrut(SPACING));
        }

        JButton createButton = getCreateButton();
        add(createButton);

        revalidate();
        repaint();
    }

    private JButton getCreateButton() {
        JButton addButton = new JButton("+");
        addButton.setFont(new Font("Default font", Font.BOLD, 30));
        addButton.setPreferredSize(new Dimension(0, 100)); //for height
        addButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 400)); //for width
        addButton.addActionListener(_ -> new UndeadCreator(this::addNewUndead));
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        return addButton;
    }

    private void addNewUndead(Undead newUndead) {
        undeads.add(newUndead);

        updateModelsView();
    }

    private void removeUndead(Undead undead) {
        undeads.remove(undead);

        updateModelsView();
    }

}
