package main.armyview;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class ArmyViewComponentPanel extends JPanel {

    protected final int SMALL_LINE_HEIGHT = 5;
    protected final int BIG_LINE_HEIGHT = 15;

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
        addBoxLayoutAlignment(newLabel);

        return newLabel;
    }

    protected void addEmptyLine(int height) {
        add(Box.createVerticalStrut(height));
    }

    protected void addBoxLayoutAlignment(JComponent jComponent) {
        jComponent.setAlignmentX(Component.LEFT_ALIGNMENT);
        jComponent.setMaximumSize(new Dimension(Integer.MAX_VALUE, jComponent.getMinimumSize().height));
    }
}
