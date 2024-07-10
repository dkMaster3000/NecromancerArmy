package main.modelsview.ExpoImpo;

import main.models.undead.Undead;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ExportModels {

    private static ExportModels exportModels = null;

    private int rowCount = 1;


    private static XSSFSheet sheet;

    private ExportModels() {
    }

    public static ExportModels getInstance() {
        if (exportModels == null) {
            exportModels = new ExportModels();
        }

        return exportModels;
    }


    public void exportUndeads(List<Undead> undeads) {

        XSSFWorkbook workbook = Objects.requireNonNullElseGet(ExpoImpoValues.workbook, () -> getNewWorkbook().orElseThrow());

        sheet = workbook.getSheetIndex(ExpoImpoValues.MODELS_SHEET_NAME) != -1 ?
                workbook.getSheet(ExpoImpoValues.MODELS_SHEET_NAME) :
                workbook.createSheet(ExpoImpoValues.MODELS_SHEET_NAME);

        fillSheet(undeads);
        saveWorkbook(workbook);

    }


    public void fillSheet(List<Undead> undeads) {

        for (Undead undead : undeads) {

            printValuePair(ExpoImpoValues.UNDEAD_MODEL_NAME, undead.getName());
            printValuePair(ExpoImpoValues.UNDEAD_MODEL_MAXHP, undead.getMaxhp());
            printValuePair(ExpoImpoValues.UNDEAD_MODEL_ARMOR, undead.getArmor());
            printValuePair(ExpoImpoValues.UNDEAD_MODEL_DMG, undead.getDmg());
            printValuePair(ExpoImpoValues.UNDEAD_MODEL_STRENGTH, undead.getStrength());
            printValuePair(ExpoImpoValues.UNDEAD_MODEL_DEX, undead.getDexterity());
            printValuePair(ExpoImpoValues.UNDEAD_MODEL_INT, undead.getIntelligence());
            printValuePair(ExpoImpoValues.UNDEAD_MODEL_CHARACTERISTICS, undead.getCharacteristics());
            printValuePair(ExpoImpoValues.UNDEAD_MODEL_LEADER, ExpoImpoValues.getTextByIsLeader(undead.getIsLeader()));

            printValuePair(ExpoImpoValues.UNDEAD_MODEL_MARKER, " "); //signal the end of an undead
        }
    }

    private Row createRow() {
        //create row
        Row newRow = sheet.createRow(rowCount);

        //shift the pointer to next position
        rowCount++;

        //return row
        return newRow;
    }

    private void printValuePair(String cellName, String cellValue) {
        //start point to write
        int startColumn = 1;

        //create row
        Row nameRow = createRow();
        //create cell for name
        Cell nameCell = nameRow.createCell(startColumn);
        nameCell.setCellValue(cellName);
        //create cell for value
        Cell nameCellValue = nameRow.createCell(startColumn + 1);
        nameCellValue.setCellValue(cellValue);
    }

    private void printValuePair(String cellName, int cellValue) {
        printValuePair(cellName, String.valueOf(cellValue));
    }

    private static Optional<XSSFWorkbook> getNewWorkbook() {
        return Optional.of(new XSSFWorkbook());
    }

    private static void saveWorkbook(XSSFWorkbook workbook) {
        JFileChooser fileChooser = new JFileChooser(ExpoImpoValues.FSV.getHomeDirectory());
        fileChooser.setPreferredSize(ExpoImpoValues.DIMENSION);

        fileChooser.setDialogTitle("Save As");
        fileChooser.setFileFilter(ExpoImpoValues.NAME_FILTER);
        fileChooser.setAcceptAllFileFilterUsed(false);

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();

            if (!filePath.endsWith("." + ExpoImpoValues.EXCEL_EXTINCTION)) {
                filePath += "." + ExpoImpoValues.EXCEL_EXTINCTION;
                fileToSave = new File(filePath);
            }

            try (FileOutputStream fileOut = new FileOutputStream(fileToSave)) {
                workbook.write(fileOut);
                JOptionPane.showMessageDialog(null, "Excel file saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException ex) {
                ex.printStackTrace(System.err);
                JOptionPane.showMessageDialog(null, "Error saving Excel file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
