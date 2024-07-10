package main.modelsview;

import main.modelsview.resources.DefaultUndeadCollection;
import main.modelsview.uicomponents.ModelPanel;
import main.modelsview.uicomponents.UndeadCreator;
import main.models.undead.Undead;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ModelsView extends JPanel {

    private final List<Undead> undeads = new ArrayList<>();

    Consumer<Undead> addToArmyFunction;

    public final static int COMPONENT_MAX_WIDTH = 390;

    private final int SPACING = 10;

    public ModelsView(Consumer<Undead> addToArmyFunction) {
        this.addToArmyFunction = addToArmyFunction;

        setBorder(new EmptyBorder(SPACING, SPACING, SPACING, SPACING));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //load default undeads
        undeads.addAll(DefaultUndeadCollection.getDefaultUndeads());

        updateModelsView();
    }

    private void updateModelsView() {
        removeAll();

        for (Undead undead : undeads) {
            ModelPanel newModelPanel = new ModelPanel(undead, addToArmyFunction, this::removeUndead);

            add(newModelPanel);

            add(Box.createVerticalStrut(SPACING));
        }

        JButton createButton = getCreateButton();
        add(createButton);

        add(Box.createVerticalStrut(SPACING));

        JButton downloadButton = getDownloadButton();
        add(downloadButton);

        revalidate();
        repaint();
    }

    private JButton getCreateButton() {
        JButton addButton = new JButton("+");
        addButton.setFont(new Font("Default font", Font.BOLD, 30));
        addButton.setPreferredSize(new Dimension(0, 100)); //for height
        addButton.setMaximumSize(new Dimension(COMPONENT_MAX_WIDTH, 400)); //for width
        addButton.addActionListener(_ -> new UndeadCreator(this::addNewUndead));
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        return addButton;
    }

    private JButton getDownloadButton() {
        JButton addButton = new JButton("Export");
        addButton.setFont(new Font("Default font", Font.BOLD, 30));
        addButton.setPreferredSize(new Dimension(0, 100)); //for height
        addButton.setMaximumSize(new Dimension(COMPONENT_MAX_WIDTH, 400)); //for width
        addButton.addActionListener(_ -> new ExportModels(undeads));
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        return addButton;
    }

    private void addNewUndead(Undead newUndead) {
        undeads.add(newUndead);

        updateModelsView();
    }

    private void removeUndead(Undead undead) {
        undeads.remove(undead);

        updateModelsView();
    }

    private void exportModels() {

    }

}
