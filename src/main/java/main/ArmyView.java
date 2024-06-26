package main;

import main.models.uicomponents.ModelPanel;
import main.models.uicomponents.UndeadCreator;
import main.models.undead.Undead;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ArmyView extends JPanel {

    private List<Undead> undeads = new ArrayList<>();

    public ArmyView() {
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setLayout(new GridLayout(0, 2, 20, 20));

        updateArmyView();
    }

    private void updateArmyView() {
        removeAll();

        JLabel baum = new JLabel("Baum");
        add(baum);

        JLabel count = new JLabel("" + undeads.size());
        add(count);

        revalidate();
        repaint();
    }

    private void addUndead(Undead undead) {
        Undead newUndead = new Undead(undead); //create copy
        undeads.add(newUndead);

        updateArmyView();
    }

    public Consumer<Undead> getAddToArmyFunction() {
        return this::addUndead;
    }
}
