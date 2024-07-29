/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hust.soict.dsai.blockchain.engine.scraper;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BlockChainNewsScraper extends Scraper {
    private static ArrayList<News> newsList = new ArrayList<News>();
    private static ArrayList<String> droppedLinks = new ArrayList<String>();
    private static String websiteSource = "https://blockchain.news/";
    public BlockChainNewsScraper() {};

    public static ArrayList<News> getNewsList() {
        return newsList;
    }

    public static ArrayList<String> getDroppedLinks() {
        return droppedLinks;
    }

    public static String getWebsiteSource() {
        return websiteSource;
    }
    public String getTitle(Document newsdoc) {
        Elements title = newsdoc.select("title");
        String heading = "";
        for (Element t : title) {
            String text = t.text();
            heading += text;
        }
        return Scraper.formatDoubleQuotes(heading);
    }

    public String getCategories(Document newsdoc) {
        return "";
    }

    public String getSummary(Document newsdoc) {
        Elements sum = newsdoc.select(".text-size-big");
        String summary = "";
        for (Element t : sum) {
            String text = t.text();
            if (text.equals("by")) {
                continue;
            }
            summary += text;
        }
        return Scraper.formatDoubleQuotes(summary);
    }

    public String getContent(Document newsdoc) {
        Elements paragraphs = newsdoc.select("p > span");
        String content = "";
        for (Element paragraph : paragraphs) {
            String text = paragraph.text();
            content += text;
        }
        return Scraper.formatDoubleQuotes(content);
    }

    public String getDate(Document newsdoc) {
        Elements time = newsdoc.select("time.entry-date");
        String date = "";
        for (Element t : time) {
            String text = t.text();
            date += text;
            break;
        }
        return date;
    }

    public String getAuthor(Document newsdoc) {
        Elements au = newsdoc.select("a.entry-cat");
        String author = "";
        for (Element a : au) {
            String text = a.text();
            author += text;
        }
        return author;
    }

    public String getTags(Document newsdoc) {
        Elements tag = newsdoc.select("div.tagcloud > [href]");
        String tags = "";
        int index=0;
        for (Element t:tag) {
            index += 1;
            tags += t.text();
            if (index < tag.size()) {
                tags += " - ";
            }
        }
        return tags;
    }

    public void scrape() {
        int count = 0;
        try {
            for (String page : this.getUrlPages()) {
                String urlPage = "https://blockchain.news/tag/" + page;
                System.out.println(urlPage);
                Document doc = Jsoup.connect(urlPage).timeout(10 * 100000).get();
                String newsUrl;
                Elements links = doc.select("div.thumbnail-attachment > a[href]");
                for (Element link : links) {
                    count += 1;
                    System.out.println("Article num: " + count);
                    //Get the hyperlink (article link)
                    String l = link.attr("href");
                    newsUrl = websiteSource + l;
                    if (newsUrl.contains("https://blockchain.news//news/")) {
                        News currentNews = getNews(newsUrl, websiteSource, newsList, droppedLinks);
                        if (!currentNews.getContent().equals("Empty link. Do not add.")) {
                            currentNews.setCategory(page.split("/")[0]);
                            newsList.add(currentNews);
                            currentNews.displayNews();
                        }
                    }
                    else {
                        count -= 1;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}