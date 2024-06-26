package main.models.uicomponents;

import main.models.undead.Undead;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class ModelPanel extends JPanel {

    Undead undead;

    public ModelPanel(Undead undead, Consumer<Undead> deleteFunction) {
        this.undead = undead;

        setBackground(Color.ORANGE);

        JButton addB = new JButton("+");
        add(addB);

        JLabel name = new JLabel(undead.getName());
        add(name);

        JButton deleteB = new JButton("Delete");
        deleteB.addActionListener(_ -> deleteFunction.accept(undead));
        add(deleteB);
    }
}
