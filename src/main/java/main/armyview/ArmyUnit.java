package main.armyview;

import main.models.undead.Undead;
import org.javatuples.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class ArmyUnit extends JPanel {

    public ArmyUnit(Pair<Undead, Integer> undeadCountPair, Consumer<Undead> removeUndead) {
        setMaximumSize(new Dimension(600, 50));
        setLayout(new FlowLayout(FlowLayout.LEFT));

        Undead undead = undeadCountPair.getValue0();
        int undeadCount = undeadCountPair.getValue1();

        JButton removeButton = new JButton("-1 Einheit");
        removeButton.addActionListener(_ -> removeUndead.accept(undead));
        add(removeButton);

        JLabel nameCountJLabel = new JLabel(undeadCount + "x" + undead.getName());
        add(nameCountJLabel);

        JLabel seperatorJLabel = new JLabel(" || ");
        add(seperatorJLabel);

        JLabel hpJLabel = new JLabel("MaxHP pro Einheit: " + undead.getMaxhp());
        add(hpJLabel);

    }
}
