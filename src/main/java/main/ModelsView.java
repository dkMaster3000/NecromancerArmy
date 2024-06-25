package main;

import main.models.uicomponents.ModelPanel;
import main.models.uicomponents.UndeadCreator;
import main.models.undead.Undead;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ModelsView extends JPanel implements ActionListener {

    private final List<Undead> undeads = new ArrayList<>();

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

    public ModelsView() {
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setLayout(new GridLayout(0, 2, 20, 20));

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
            ModelPanel newModelPanel = new ModelPanel(undead);

            add(newModelPanel);
        }

        JButton addButton = new JButton("+");
        addButton.addActionListener(this);
        add(addButton);

        revalidate();
        repaint();
    }

    private void addNewUndead(Undead newUndead) {
        undeads.add(newUndead);

        updateModelsView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new UndeadCreator(this::addNewUndead);
    }
}
