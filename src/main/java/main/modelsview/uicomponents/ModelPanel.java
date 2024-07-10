package main.modelsview.uicomponents;

import main.models.undead.Undead;
import main.modelsview.ModelsView;
import main.themes.NecromancerColors;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.function.Consumer;

public class ModelPanel extends JPanel {

    Undead undead;

    private final int CONTENT_MAX_WIDTH = 370;

    public ModelPanel(Undead undead, Consumer<Undead> addFunction, Consumer<Undead> deleteFunction, Consumer<Undead> editFunction) {
        this.undead = undead;

        // Define border thickness
        int borderThickness = 2;

        // Set border
        setBorder(new CompoundBorder(
                new LineBorder(NecromancerColors.DARK_PURPLE, borderThickness, true),
                new EmptyBorder(5, 5, 5, 5))); // Add border and padding

        // Set layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Get panels
        JPanel headerPanel = getHeaderPanel(undead, deleteFunction);
        JPanel statsPanel = getStatsPanel(undead);
        JPanel dmgPanel = getDmgPanel(undead);
        JPanel charectaristicPanel = getCharacteristicPanel(undead);
        JPanel addBPanel = getAddBPanel(undead, addFunction, editFunction);

        // Add panels
        add(headerPanel);
        add(statsPanel);
        add(dmgPanel);
        add(charectaristicPanel);
        add(addBPanel);

        // Update preferred size of the panel
        updatePreferredSize(borderThickness);
    }

    private JPanel getHeaderPanel(Undead undead, Consumer<Undead> deleteFunction) {

        JPanel leftWrapper = new JPanel((new FlowLayout(FlowLayout.LEFT)));
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel nameLabel = new JLabel(undead.getName());
        nameLabel.setFont(new Font("Default font", Font.BOLD, 14));

        JButton deleteB = new JButton("Löschen");
        deleteB.setBackground(NecromancerColors.BLOOD_RED);
        deleteB.addActionListener(_ -> deleteFunction.accept(undead));

        headerPanel.add(nameLabel, BorderLayout.WEST);
        headerPanel.add(deleteB, BorderLayout.EAST);

        headerPanel.setPreferredSize(new Dimension(CONTENT_MAX_WIDTH, headerPanel.getPreferredSize().height));

        leftWrapper.add(headerPanel);

        return leftWrapper;
    }

    private JPanel getStatsPanel(Undead undead) {
        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel hpLabel = new JLabel("HP: " + undead.getMaxhp());
        JLabel rLabel = new JLabel("R: " + undead.getArmor());
        JLabel stLabel = new JLabel("ST: " + undead.getStrength());
        JLabel dexLabel = new JLabel("GE: " + undead.getDexterity());
        JLabel intLabel = new JLabel("INT: " + undead.getIntelligence());
        JLabel leadeLabel = new JLabel("Anführer: " + (undead.getIsLeader() ? "Ja" : "Nein"));

        statsPanel.add(hpLabel);
        statsPanel.add(rLabel);
        statsPanel.add(stLabel);
        statsPanel.add(dexLabel);
        statsPanel.add(intLabel);
        statsPanel.add(leadeLabel);

        return statsPanel;
    }

    private JPanel getDmgPanel(Undead undead) {
        return getTextAreaPanel("Angriff: " + undead.getDmg());
    }

    private JPanel getCharacteristicPanel(Undead undead) {
        return getTextAreaPanel("Eigenschaft: " + undead.getCharacteristics());
    }

    private JPanel getTextAreaPanel(String text) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextArea textArea = getJTextArea(text);
        panel.add(textArea);

        return panel;
    }

    private JTextArea getJTextArea(String x) {
        JTextArea newTextArea = new JTextArea();
        newTextArea.setMargin(new Insets(0, 0, 0, 0)); //set margins to zero
        newTextArea.setEditable(false);
        newTextArea.setLineWrap(true);
        newTextArea.setWrapStyleWord(true);
        newTextArea.setText(x);
        newTextArea.setMaximumSize(new Dimension(CONTENT_MAX_WIDTH, newTextArea.getPreferredSize().height)); //IMPORTANT for calculation

        // Get the FontMetrics of the JTextArea
        FontMetrics metrics = newTextArea.getFontMetrics(newTextArea.getFont());
        // Get the width of a single character (assuming monospaced font for simplicity)
        int charWidth = metrics.charWidth('M'); // 'M' is often used as a reference character
        // Calculate the number of columns
        int columns = CONTENT_MAX_WIDTH / charWidth;

        newTextArea.setColumns(columns);

        return newTextArea;
    }

    private JPanel getAddBPanel(Undead undead, Consumer<Undead> addFunction, Consumer<Undead> editFunction) {
        JPanel addBPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton addB = new JButton("Hinzufügen");
        addB.setBackground(NecromancerColors.POISON_GREEN);
        addB.addActionListener(_ -> addFunction.accept(undead));
        addBPanel.add(addB);

        JButton editB = new JButton("Bearbeiten");
        editB.setBackground(NecromancerColors.DARK_PURPLE);
        editB.addActionListener(_ -> new UndeadCreator(editFunction, undead));
        addBPanel.add(editB);

        return addBPanel;
    }

    private void updatePreferredSize(int borderThickness) {
        int height = getPreferredSize().height + borderThickness * 2;
        setMaximumSize(new Dimension(ModelsView.COMPONENT_MAX_WIDTH, height));
    }
}
