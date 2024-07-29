package hust.soict.dsai.blockchain.execute;
import hust.soict.dsai.blockchain.engine.CSVHandler;
import hust.soict.dsai.blockchain.engine.scraper.CryptoSlateScraper;

import java.util.Scanner;

public class RunCSL {
    public static void main(String[] args) {
        CryptoSlateScraper csl = new CryptoSlateScraper();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter start page:");
        int startPage = sc.nextInt();
        System.out.println("Enter end page:");
        int endPage = sc.nextInt();
        sc.close();
        String[] pages = new String[endPage + 1];
        for (int i = startPage ; i <= endPage ; i ++) {
            pages[i] = Integer.toString(i);
        }
        csl.setUrlPages(pages);
        csl.scrape();
        CSVHandler.writeScrapedData("src\\main\\java\\hust\\soict\\dsai\\blockchain\\engine\\Data\\CSL\\CSL_raw", csl.getNewsList(), true);
        System.out.println("Dropped links: ");
        for (String link : csl.getDroppedLinks()) {
            System.out.println(link);
        }
    }
}
