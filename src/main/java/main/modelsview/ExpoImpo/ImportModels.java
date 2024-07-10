package main.modelsview.ExpoImpo;

import main.models.undead.Undead;
import main.models.undead.UndeadParameters;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ImportModels {

    private ImportModels() {
        //prevent from being initialized
    }

    public static void importUndeads(Consumer<List<Undead>> receiveImportedUndeads) {
        importWorkbook();

        if (ExpoImpoValues.workbook.getSheetIndex(ExpoImpoValues.MODELS_SHEET_NAME) != -1) {
            List<Undead> newUndeads = loadUndeads(ExpoImpoValues.workbook.getSheet(ExpoImpoValues.MODELS_SHEET_NAME));
            receiveImportedUndeads.accept(newUndeads);
        }
    }

    //opens filechooser and load the chosen workbook in ExpoImpoKeys
    public static void importWorkbook() {
        JFileChooser fileChooser = new JFileChooser(ExpoImpoValues.FSV.getHomeDirectory());
        fileChooser.setPreferredSize(ExpoImpoValues.DIMENSION);
        fileChooser.setFileFilter(ExpoImpoValues.NAME_FILTER);

        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();

                FileInputStream fileInputStream = new FileInputStream(file);
                ExpoImpoValues.workbook = new XSSFWorkbook(fileInputStream);

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static List<Undead> loadUndeads(Sheet sheet) {
        Map<Integer, List<String>> dataMap = LoaderUtils.getMap(sheet);

        List<Undead> undeads = new ArrayList<>();

        // helper class
        class UndeadBuilder {
            String name;
            int maxhp;
            int armor;
            String dmg;
            int strength;
            int dexterity;
            int intelligence;
            String characteristics;
            boolean isLeader;

            UndeadBuilder() {
                reset();
            }

            void reset() {
                name = "";
                maxhp = 0;
                armor = 0;
                dmg = "";
                strength = 0;
                dexterity = 0;
                intelligence = 0;
                characteristics = "";
                isLeader = false;
            }

            Undead build() {
                return new Undead(new UndeadParameters(name, maxhp, armor, dmg, strength, dexterity, intelligence, characteristics, isLeader));
            }
        }

        UndeadBuilder builder = new UndeadBuilder();

        for (List<String> data : dataMap.values()) {
            String identifier = data.get(0);
            String value = data.get(1);
            switch (identifier) {
                case ExpoImpoValues.UNDEAD_MODEL_NAME -> builder.name = value;
                case ExpoImpoValues.UNDEAD_MODEL_MAXHP -> builder.maxhp = Integer.parseInt(value);
                case ExpoImpoValues.UNDEAD_MODEL_ARMOR -> builder.armor = Integer.parseInt(value);
                case ExpoImpoValues.UNDEAD_MODEL_DMG -> builder.dmg = value;
                case ExpoImpoValues.UNDEAD_MODEL_STRENGTH -> builder.strength = Integer.parseInt(value);
                case ExpoImpoValues.UNDEAD_MODEL_DEX -> builder.dexterity = Integer.parseInt(value);
                case ExpoImpoValues.UNDEAD_MODEL_INT -> builder.intelligence = Integer.parseInt(value);
                case ExpoImpoValues.UNDEAD_MODEL_CHARACTERISTICS -> builder.characteristics = value;
                case ExpoImpoValues.UNDEAD_MODEL_LEADER -> builder.isLeader = ExpoImpoValues.getIsLeaderByText(value);
                case ExpoImpoValues.UNDEAD_MODEL_MARKER -> {
                    // it is the end of an undead
                    undeads.add(builder.build());
                    builder.reset();
                }
            }
        }

        return undeads;
    }
}
