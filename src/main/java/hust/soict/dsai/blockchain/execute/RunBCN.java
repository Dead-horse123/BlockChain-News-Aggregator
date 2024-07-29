package hust.soict.dsai.blockchain.execute;
import hust.soict.dsai.blockchain.engine.CSVHandler;
import hust.soict.dsai.blockchain.engine.scraper.BlockChainNewsScraper;

import java.util.ArrayList;
import java.util.Scanner;

public class RunBCN {
    public static void main(String[] args) {
        String[] allCategories = {"Bitcoin", "Ethereum", "Cardano", "Ripple", "SHIB", "Stablecoin", "CBDC", "DeFi", "NFT", "Web3", "Metaverse", "Exchange", "AI", "ChatGPT"};
        ArrayList<String> category = new ArrayList<String>();
        BlockChainNewsScraper bcn = new BlockChainNewsScraper();
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose category:");
        for (String cat: allCategories) {
            System.out.println("Add " + cat + "?:");
            System.out.println("0. No");
            System.out.println("1. Yes");
            int choice = sc.nextInt();
            switch (choice) {
                case 1: category.add(cat);
                    break;
                case 0: break;
            }
        }
        System.out.println("Enter start page:");
        int startPage = sc.nextInt();
        System.out.println("Enter end page:");
        int endPage = sc.nextInt();
        sc.close();
        String[] pages = new String[(endPage - startPage + 1) * category.size()];
        for (int i = 0; i < category.size(); i++) {
            for (int j = startPage; j <= endPage; j++) {
                pages[i * (endPage - startPage + 1) + (j - startPage)] = category.get(i) + "/" + j;
            }
        }
        bcn.setUrlPages(pages);
        bcn.scrape();
        CSVHandler.writeScrapedData("src\\main\\java\\hust\\soict\\dsai\\blockchain\\engine\\Data\\BCN\\BCN_raw", bcn.getNewsList(), true);
        System.out.println("Dropped links: ");
        for (String link : bcn.getDroppedLinks()) {
            System.out.println(link);
        }
    }
}
