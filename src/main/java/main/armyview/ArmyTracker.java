package main.armyview;

import javax.swing.*;
import java.awt.*;

public class ArmyTracker extends ArmyViewComponentPanel {

    public ArmyTracker() {
        super();

        addHeaderLabel("Tracker");

        addEmptyLine();

        addJPanelContainer("HP:");

        addEmptyLine();

        addJPanelContainer("Sonstiges:");

    }

    private void addJPanelContainer(String text) {

        int PANEL_WIDTH = 850;
        int PANEL_HEIGHT = 20;
        int LABEL_WIDTH = 100;

        JPanel newPanel = new JPanel();
        newPanel.setMinimumSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        newPanel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        newPanel.setLayout(null);

        JLabel newLabel = new JLabel(text);
        newLabel.setBounds(0, 0, LABEL_WIDTH, PANEL_HEIGHT);

        JTextField newTF = new JTextField();
        newTF.setBounds(LABEL_WIDTH + 1, 0, PANEL_WIDTH - LABEL_WIDTH, PANEL_HEIGHT);

        newPanel.add(newLabel);
        newPanel.add(newTF);

        addBoxLayoutAlignment(newPanel);

        add(newPanel);
    }
}
