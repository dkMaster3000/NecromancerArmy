package main;

import main.armyview.ArmyView;

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
        armyView.setBackground(Color.red);

        ModelsView modelsView = new ModelsView(armyView.getAddToArmyFunction());
        modelsView.setBackground(Color.GREEN);

        // Create a JSplitPane and add the panels to it
        JSplitPane splitPane = getjSplitPane(armyView, modelsView);

        JPanel splitPaneWithBorder = getComponentWithBorder(splitPane);

        add(splitPaneWithBorder);

        revalidate();
        repaint();
    }

    private JSplitPane getjSplitPane(ArmyView armyView, ModelsView modelsView) {
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, armyView, modelsView);

        // Set the initial divider location to 2/3 of the frame width
        int dividerLocation = (FRAME_WIDTH * 2 / 3);
        splitPane.setDividerLocation(dividerLocation);

        // Disable the dragging of the divider
        splitPane.setEnabled(false);

        splitPane.setDividerSize(5); // Optional: Set the thickness of the divider
        return splitPane;
    }


    private JPanel getComponentWithBorder(JComponent jcomponent) {
        // Create an outer panel with BorderLayout
        JPanel outerPanel = new JPanel(new BorderLayout());
        // Add a margin to the outer panel
        outerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add the split pane to the center of the outer panel
        outerPanel.add(jcomponent, BorderLayout.CENTER);

        return outerPanel;
    }

}
