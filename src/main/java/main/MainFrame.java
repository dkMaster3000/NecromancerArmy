package main;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    final int VERTICAL_PADDING = 10;
    final int OUTER_PADDING = 10;
    final int INNER_PADDING = OUTER_PADDING / 2;
    final int INNER_PADDING_ZERO = 0;
    final int FRAME_WIDTH = 1400;
    final int FRAME_HEIGHT = 800;


    MainFrame() {
        setTitle("Necromancer Army");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel container = new JPanel();
        container.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        container.setBackground(Color.blue);
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = FRAME_HEIGHT - VERTICAL_PADDING * 2; //height
        gbc.weighty = 1;
        gbl.setConstraints(container, gbc);
        container.setLayout(gbl);


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.66;
        gbc.insets = new Insets(VERTICAL_PADDING, OUTER_PADDING, VERTICAL_PADDING, INNER_PADDING_ZERO);
        JPanel armyView = new JPanel();
        armyView.setBackground(Color.red);
        container.add(armyView, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.001;
        gbc.insets = new Insets(VERTICAL_PADDING, INNER_PADDING, VERTICAL_PADDING, INNER_PADDING);
        JSeparator newSeparator = new JSeparator();
        newSeparator.setOrientation(SwingConstants.VERTICAL);
        container.add(newSeparator, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0.33;
        gbc.insets = new Insets(VERTICAL_PADDING, INNER_PADDING_ZERO, VERTICAL_PADDING, OUTER_PADDING);
        ModelsView modelsView = new ModelsView();
        modelsView.setBackground(Color.GREEN);
        container.add(modelsView, gbc);

        add(container);

        revalidate();
        repaint();
    }


}
