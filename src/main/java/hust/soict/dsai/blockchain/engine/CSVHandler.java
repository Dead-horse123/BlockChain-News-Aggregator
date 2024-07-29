package hust.soict.dsai.blockchain.engine;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import hust.soict.dsai.blockchain.engine.scraper.News;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVHandler {
    public static void writeScrapedData(String name, ArrayList<News> n, boolean append) {
        String filePath = name + ".csv";
        boolean fileExist = Files.exists(Paths.get(filePath));
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, append));
            if (!fileExist) {
                writer.write("Link, WebsiteSource, Type, Summary, Title, Content, Date, Tags, Author, Category\n");
            }
            for (News a: n) {
                writer.write(a.toString());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Write to CSV successfully");
    }
    public static void exportCSV(javax.swing.JTable Table, String path) throws IOException, CsvValidationException{
        TableModel model = Table.getModel();
        CSVReader reader = new CSVReader(new FileReader(new File(path)));
        String[] fisrtRow = reader.readNext();
        CSVWriter csv = new CSVWriter(new FileWriter(new File(path)));
        try{
            csv.writeNext(fisrtRow, false);
            for (int i = 0; i < model.getRowCount(); i++) {
                ArrayList<String> row = new ArrayList<String>();
                for (int j = 0; j < model.getColumnCount(); j++) {
                    if (model.getValueAt(i, j) != null) {
                        row.add(model.getValueAt(i, j).toString());
                    } else {
                        row.add("");
                    }
                }
                String[] arr = {row.get(0), row.get(1), row.get(2)};
               
                csv.writeNext(arr,false);
            }
            csv.close();
        }
        catch(IOException ex){
            System.out.println("Error");
        }
    }
    public static void removeCSV(String path){
        File myFile = new File(path);
        myFile.delete();
    }
    public static void fillTable(JTable table, String filePath) throws FileNotFoundException, IOException, CsvException{
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        CSVReader saveFile = new CSVReader(new FileReader(filePath));
        List<String[]> saveList = saveFile.readAll();
        for(String[] row : saveList){
            model.addRow(row);
        }
        model.removeRow(0);
    }
}
