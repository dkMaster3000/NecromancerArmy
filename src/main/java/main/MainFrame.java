package main;

import main.armyview.ArmyView;
import main.modelsview.ModelsView;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    final int FRAME_WIDTH = 1400;
    final int FRAME_HEIGHT = 800;


    MainFrame() {
        setTitle("Necromancer Army");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        ArmyView armyView = new ArmyView();
        JScrollPane armyViewScrollPane = getJScrollPane(armyView);

        ModelsView modelsView = new ModelsView(armyView.getAddToArmyFunction());
        JScrollPane modelsViewScrollPane = getJScrollPane(modelsView);

        // Create a JSplitPane and add the panels to it
        JSplitPane splitPane = getjSplitPane(armyViewScrollPane, modelsViewScrollPane);
        add(splitPane);

        revalidate();
        repaint();
    }

    private JSplitPane getjSplitPane(JComponent armyView, JComponent modelsView) {
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, armyView, modelsView);

        // Set the initial divider location to 2/3 of the frame width
        int dividerLocation = (FRAME_WIDTH * 2 / 3);
        splitPane.setDividerLocation(dividerLocation);

        splitPane.setBackground(Color.black);

        // Disable the dragging of the divider
        splitPane.setEnabled(false);

        splitPane.setDividerSize(5); // Optional: Set the thickness of the divider
        return splitPane;
    }

    private JScrollPane getJScrollPane(JComponent jcomponent) {
        // Create a JScrollPane and add jcomponent to it
        JScrollPane scrollPane = new JScrollPane(jcomponent);
        // Adjust the scroll speed
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(20);

        return scrollPane;
    }
}
