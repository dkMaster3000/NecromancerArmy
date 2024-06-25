package main.models.uicomponents;

import main.models.undead.Undead;

import javax.swing.*;
import java.awt.*;

public class ModelPanel extends JPanel {

    Undead undead;

    public ModelPanel(Undead undead) {
        this.undead = undead;
        
        setBackground(Color.ORANGE);

        JButton addB = new JButton("+");
        add(addB);

        JLabel name = new JLabel(undead.getName());
        add(name);

        JButton deleteB = new JButton("Delete");
        add(deleteB);
    }
}
