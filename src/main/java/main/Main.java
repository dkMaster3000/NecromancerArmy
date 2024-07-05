package main;

import com.formdev.flatlaf.FlatDarkLaf;
import main.themes.NecromancerColors;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        //Set FlatLaf look and feel
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            UIManager.put("Component.focusedBorderColor", NecromancerColors.DARK_PURPLE);
            UIManager.put("Component.focusColor", NecromancerColors.DARK_PURPLE);
            UIManager.put("Button.hoverBorderColor", NecromancerColors.DARK_PURPLE);
            UIManager.put("Button.focusedBorderColor", NecromancerColors.DARK_PURPLE);
            UIManager.put("ComboBox.buttonFocusedBackground", NecromancerColors.DARK_PURPLE);
            UIManager.put("ComboBox.selectionBackground", NecromancerColors.DARK_PURPLE);
            UIManager.put("ScrollBar.thumb", NecromancerColors.DARK_PURPLE);
            UIManager.put("ScrollBar.width", 12);
            UIManager.put("ScrollBar.maximumThumbSize", new Dimension(8, 600));
            UIManager.put("ScrollBar.thumbInsets", new Insets(0, 2, 0, 2));
            UIManager.put("ScrollBar.trackArc", 5);
            UIManager.put("ScrollBar.thumbArc", 10);
            UIManager.put("ScrollBar.track", NecromancerColors.LIGHT_PURPLE);

        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace(System.err);

        }

        new MainFrame();
    }
}