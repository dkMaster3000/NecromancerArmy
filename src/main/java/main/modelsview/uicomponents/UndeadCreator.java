package main.modelsview.uicomponents;

import main.models.undead.Undead;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.function.Consumer;

public class UndeadCreator extends JFrame implements ActionListener {

    Consumer<Undead> addToModelsView;
    JTextField nameTF, hpTF, armorTF, dmgTF, strengthTF, dexterityTF, intelligenceTF, characteristicsTF;
    JComboBox<String> leaderCB;
    JLabel notificationLabel;

    private final String YES_CHOICE = "Ja";

    public UndeadCreator(Consumer<Undead> addToModelsView) {
        this.addToModelsView = addToModelsView;

        setTitle("Undead Creator");
        setSize(500, 800);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel container = new JPanel();
        container.setBackground(Color.cyan);
        container.setBorder(new EmptyBorder(20, 20, 20, 20));
        container.setLayout(new GridLayout(0, 2, 20, 20));

        JLabel nameLabel = new JLabel("Name(Text):");
        container.add(nameLabel);

        String DEFAULT_NUMBER_VALUE = "0";

        nameTF = new JTextField();
        container.add(nameTF);

        JLabel hpLabel = new JLabel("HP(Zahl):");
        container.add(hpLabel);

        hpTF = new JTextField("10");
        container.add(hpTF);

        JLabel armorLabel = new JLabel("Rüstung(Zahl):");
        container.add(armorLabel);


        armorTF = new JTextField(DEFAULT_NUMBER_VALUE);
        container.add(armorTF);

        JLabel dmgLabel = new JLabel("DMG(Text):");
        container.add(dmgLabel);

        dmgTF = new JTextField();
        container.add(dmgTF);

        JLabel strengthLabel = new JLabel("STK(Zahl):");
        container.add(strengthLabel);

        strengthTF = new JTextField(DEFAULT_NUMBER_VALUE);
        container.add(strengthTF);

        JLabel dexterityLabel = new JLabel("GES(Zahl):");
        container.add(dexterityLabel);

        dexterityTF = new JTextField(DEFAULT_NUMBER_VALUE);
        container.add(dexterityTF);

        JLabel intelligenceLabel = new JLabel("INT(Zahl):");
        container.add(intelligenceLabel);

        intelligenceTF = new JTextField(DEFAULT_NUMBER_VALUE);
        container.add(intelligenceTF);

        JLabel characteristcLabel = new JLabel("Eigenschaft(Text):");
        container.add(characteristcLabel);

        characteristicsTF = new JTextField();
        container.add(characteristicsTF);

        JLabel leaderLabel = new JLabel("Ist Anführer?:");
        container.add(leaderLabel);

        leaderCB = new JComboBox<>(new String[]{YES_CHOICE, "Nein"});
        container.add(leaderCB);


        JButton saveButton = new JButton("Speichern");
        saveButton.addActionListener(this);
        container.add(saveButton);

        notificationLabel = new JLabel("Werte sind Falsch!");
        notificationLabel.setForeground(Color.RED);
        notificationLabel.setVisible(false);
        container.add(notificationLabel);

        add(container);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (valuesAreUseable()) {
            addToModelsView.accept(
                    new Undead(nameTF.getText(),
                            Integer.parseInt(hpTF.getText()),
                            Integer.parseInt(armorTF.getText()),
                            dmgTF.getText(),
                            Integer.parseInt(strengthTF.getText()),
                            Integer.parseInt(dexterityTF.getText()),
                            Integer.parseInt(intelligenceTF.getText()),
                            characteristicsTF.getText(),
                            isLeader()));

            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        } else {
            notificationLabel.setVisible(true);
        }

    }

    private boolean valuesAreUseable() {
        if (nameTF.getText().isEmpty()) {
            return false;
        }

        try {
            Integer.parseInt(hpTF.getText());
            Integer.parseInt(armorTF.getText());
            Integer.parseInt(strengthTF.getText());
            Integer.parseInt(dexterityTF.getText());
            Integer.parseInt(intelligenceTF.getText());
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    private boolean isLeader() {
        return leaderCB.getSelectedItem() == YES_CHOICE;
    }
}
