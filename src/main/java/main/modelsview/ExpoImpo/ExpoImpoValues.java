package main.modelsview.ExpoImpo;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;

public class ExpoImpoValues {

    public static XSSFWorkbook workbook = null;
    public static final FileSystemView FSV = FileSystemView.getFileSystemView();
    public static final Dimension DIMENSION = new Dimension(1000, 600);
    public static final String EXCEL_EXTINCTION = "xlsx";
    public static final FileNameExtensionFilter NAME_FILTER = new FileNameExtensionFilter(
            "Excel file", EXCEL_EXTINCTION);

    public static final String MODELS_SHEET_NAME = "Models";

    public static final String UNDEAD_MODEL_MARKER = "Undead-Model";
    public static final String UNDEAD_MODEL_NAME = "Name";
    public static final String UNDEAD_MODEL_MAXHP = "MaxHP";
    public static final String UNDEAD_MODEL_ARMOR = "Armor";
    public static final String UNDEAD_MODEL_DMG = "DMG";
    public static final String UNDEAD_MODEL_STRENGTH = "Strength";
    public static final String UNDEAD_MODEL_DEX = "Dexterity";
    public static final String UNDEAD_MODEL_INT = "Intelligence";
    public static final String UNDEAD_MODEL_CHARACTERISTICS = "Characteristics";
    public static final String UNDEAD_MODEL_LEADER = "Leader";
    public static final String UNDEAD_MODEL_YES = "Ja";
    public static final String UNDEAD_MODEL_NO = "Nein";


    public static String getTextByIsLeader(boolean isLeader) {
        return isLeader ? ExpoImpoValues.UNDEAD_MODEL_YES : ExpoImpoValues.UNDEAD_MODEL_NO;
    }

    public static boolean getIsLeaderByText(String text) {
        return text.equals(ExpoImpoValues.UNDEAD_MODEL_YES);
    }
}
