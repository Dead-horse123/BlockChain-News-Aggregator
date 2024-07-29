package hust.soict.dsai.blockchain.execute;
import hust.soict.dsai.blockchain.engine.RunPython;

import java.util.Scanner;

public class ProcessData extends RunPython {
    public static void main(String[] args) {
        //input
        Scanner sc = new Scanner(System.in);
        String pythonScript = "";
        System.out.println("Format Type:");
        System.out.println("0. CryptoSlate.");
        System.out.println("1. BlockChain News.");
        System.out.println("2. Merge all data files.");
        System.out.println("3. Update tags and categories.");
        System.out.println("4. Named entity recognition.");
        System.out.println("5. Update inverted indexes");
        int mode = sc.nextByte();
        switch (mode) {
            case 0: pythonScript = "FormatCSL.py";
                break;
            case 1: pythonScript = "FormatBCN.py";
                break;
            case 2: pythonScript = "MergeData.py";
                break;
            case 3: pythonScript = "Update_Tags_And_Categories.py";
                break;
            case 4: pythonScript = "Entity_Recognition.py";
                break;
            case 5: pythonScript = "Create_Inverted_Index.py";
                break;
        }
        pythonScript = "src\\main\\java\\hust\\soict\\dsai\\blockchain\\engine\\scraper\\" + pythonScript;
        sc.close();

        // run python file
        RunPython.run(pythonScript);
    }
}
