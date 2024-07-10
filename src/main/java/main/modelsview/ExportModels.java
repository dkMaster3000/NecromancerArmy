package main.modelsview;

import main.models.undead.Undead;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ExportModels {

    private int rowCount = 1;
    private final int startColumn = 1;

    private final String UNDEAD_MODEL_MARKER = "Undead-Model";

    private final XSSFWorkbook workbook;
    private final XSSFSheet sheet;

    List<Undead> undeads;

    public ExportModels(List<Undead> undeads) {
        workbook = getNewWorkbook().orElseThrow();
        sheet = workbook.createSheet("Models");

        this.undeads = undeads;

        fillSheet();
        saveWorkbook(workbook);
    }


    public void fillSheet() {

        for (Undead undead : undeads) {
            printValuePair(UNDEAD_MODEL_MARKER, " ");

            printValuePair("Name", undead.getName());
            printValuePair("MaxHP", undead.getMaxhp());
            printValuePair("Armor", undead.getArmor());
            printValuePair("DMG", undead.getDmg());
            printValuePair("Strength", undead.getStrength());
            printValuePair("Dexterity", undead.getDexterity());
            printValuePair("Intelligence", undead.getIntelligence());
            printValuePair("Characteristics", undead.getCharacteristics());
            printValuePair("Leader", undead.getIsLeader() ? "Ja" : "Nein");
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
        FileSystemView fsv = FileSystemView.getFileSystemView();
        JFileChooser fileChooser = new JFileChooser(fsv.getHomeDirectory());
        fileChooser.setPreferredSize(new Dimension(1000, 600));

        fileChooser.setDialogTitle("Save As");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xlsx");
        fileChooser.setFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();

            if (!filePath.endsWith(".xlsx")) {
                filePath += ".xlsx";
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
