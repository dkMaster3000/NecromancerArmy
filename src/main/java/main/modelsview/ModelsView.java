package main.modelsview;

import main.modelsview.ExpoImpo.ExportModels;
import main.modelsview.ExpoImpo.ImportModels;
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

    private List<Undead> undeads = new ArrayList<>();

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
            ModelPanel newModelPanel = new ModelPanel(undead, addToArmyFunction, this::removeUndead, this::addNewUndead);

            add(newModelPanel);

            add(Box.createVerticalStrut(SPACING));
        }

        JButton createButton = createModelsViewButton("+");
        createButton.addActionListener(_ -> new UndeadCreator(this::addNewUndead));
        add(createButton);

        add(Box.createVerticalStrut(SPACING));

        JButton importButton = createModelsViewButton("Import");
        importButton.addActionListener(_ -> ImportModels.importUndeads(this::receiveImportedUndeads));
        add(importButton);

        add(Box.createVerticalStrut(SPACING));

        JButton exportButton = createModelsViewButton("Export");
        exportButton.addActionListener(_ -> ExportModels.getInstance().exportUndeads(undeads));
        add(exportButton);

        revalidate();
        repaint();
    }

    private JButton createModelsViewButton(String text) {
        JButton addButton = new JButton(text);
        addButton.setFont(new Font("Default font", Font.BOLD, 30));
        addButton.setPreferredSize(new Dimension(0, 100)); //for height
        addButton.setMaximumSize(new Dimension(COMPONENT_MAX_WIDTH, 400)); //for width
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

    private void receiveImportedUndeads(List<Undead> undeads) {
        this.undeads = undeads;

        updateModelsView();
    }

}
