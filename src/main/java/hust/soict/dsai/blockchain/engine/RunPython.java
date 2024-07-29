
package hust.soict.dsai.blockchain.engine;
import com.opencsv.exceptions.CsvException;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class RunPython {
    public static String pythonPath = getPythonPath();

    public static String getPythonPath() {
        String path = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/java/hust/soict/dsai/blockchain/engine/python_path.txt"));
            String firstLine = reader.readLine();
            path = firstLine;
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(path);
        return path;
    }

    public static void run(String pythonScript) {
        try {
            Process process = Runtime.getRuntime().exec(pythonPath + " " + pythonScript);
            // capture output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                
            }
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Completed.");
            } else {
                System.err.println("Error code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
    }

    public static void search(String key, boolean smartSearchMode, JTable SearchingResultsTable) throws IOException, FileNotFoundException, CsvException {
        String input = key;
        String fileName = FilePathsManager.getSearchHistoryFilePath();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            bufferedWriter.write(input + "\n");
            System.out.println("Input added to " + fileName);
        } catch (IOException e) {
            System.err.println("Error occurred: " + e.getMessage());
            return;
        }
        //run search file
        if(smartSearchMode){
            RunPython.run(FilePathsManager.getSearchFilePath());
        }
        else{
            RunPython.run(FilePathsManager.getExactSearchFilePath());
        }
        //fill table
        CSVHandler.fillTable(SearchingResultsTable, FilePathsManager.getTempFilePath());
    }
    public static void exportTrendingPNG(){
        RunPython.run("src\\main\\java\\hust\\soict\\dsai\\blockchain\\engine\\Search\\trending\\Trend_Detection.py");
    }

    public static  ArrayList<String[]> getTredingInformation() throws FileNotFoundException, IOException{
        ArrayList<String[]> trendingList = new ArrayList<String[]>();

        BufferedReader br;

        br = new BufferedReader(new FileReader(FilePathsManager.getTrendCSVFilePath()));
        String line = "";


        while ((line = br.readLine()) != null) {


            String[] values = line.split(",");

            String name = values[0];


            String count = values[1];

            String [] data = {name, count};
            trendingList.add(data);
        }
        return trendingList;
    }
}
