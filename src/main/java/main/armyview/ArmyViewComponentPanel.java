package main.armyview;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class ArmyViewComponentPanel extends JPanel {

    public ArmyViewComponentPanel() {
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    protected void addLabel(String text) {
        add(createLabel(text));
    }

    protected void addHeaderLabel(String text) {
        JLabel newLabel = createLabel(text);
        newLabel.setFont(new Font("Default font", Font.BOLD, 14));
        add(newLabel);
    }

    protected JLabel createLabel(String text) {
        JLabel newLabel = new JLabel(text);
        newLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        newLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, newLabel.getMinimumSize().height));

        return newLabel;
    }

    protected void addEmptyLine(int height) {
        add(Box.createVerticalStrut(height));
    }
}
