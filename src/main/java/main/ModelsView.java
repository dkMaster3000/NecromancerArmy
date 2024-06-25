package main;

import main.models.uicomponents.ModelPanel;
import main.models.undead.Undead;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ModelsView extends JPanel {

    List<Undead> undeads = new ArrayList<>();
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

    public ModelsView() {
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setLayout(new GridLayout(0, 2, 20, 20));

        undeads.add(zombie);
        undeads.add(skelettalWarrior);
        undeads.add(skelettalArcher);
        undeads.add(banshee);

        for (Undead undead : undeads) {
            ModelPanel newModelPanel = new ModelPanel(undead);

            add(newModelPanel);
        }

        JButton addButton = new JButton("+");
        add(addButton);

    }
}
