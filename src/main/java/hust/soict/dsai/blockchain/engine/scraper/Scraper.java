package hust.soict.dsai.blockchain.engine.scraper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class Scraper {
    private String[] urlPages;

    public Scraper() {
    }


    public String[] getUrlPages() {
        return urlPages;
    }

    public void setUrlPages(String[] urlPages) {
        this.urlPages = urlPages;
    }


    public static String formatDoubleQuotes(String text) {
        return text.replace("\"","\"\"");
    }

    public static String formatLink(String originalLink) {
        Pattern pattern = Pattern.compile("(%)(?!([0-9A-Fa-f]{2}))");
        Matcher matcher = pattern.matcher(originalLink);
        String formattedLink = matcher.replaceAll("%25");
        return formattedLink;
    }
    public abstract void scrape();
    public abstract String getTitle(Document newsdoc);
    public abstract String getSummary(Document newsdoc);
    public abstract String getContent(Document newsdoc);
    public abstract String getDate(Document newsdoc);
    public abstract String getAuthor(Document newsdoc);
    public abstract String getTags(Document newsdoc);
    public abstract String getCategories(Document newsdoc);
    public News getNews(String newsUrl, String websiteSource, ArrayList<News> newsList, ArrayList<String> droppedLink) {
        try {
            newsUrl = Scraper.formatLink(newsUrl);
            Document newsdoc = Jsoup.connect(newsUrl).timeout(10 * 100000).get();
            //Get relevant data
            String title = this.getTitle(newsdoc);
            String category = this.getCategories(newsdoc);
            String summary = this.getSummary(newsdoc);
            String content = this.getContent(newsdoc);
            String date = this.getDate(newsdoc);
            String author = this.getAuthor(newsdoc);
            String tags = this.getTags(newsdoc);
            News currentNews = new News(newsUrl, websiteSource, 2, summary, title, content, date, tags, author, category);
            return currentNews;
        } catch (IOException e) {
            droppedLink.add(newsUrl);
            System.out.println("Can't get link.");
            return new News("Empty link. Do not add.");
        }
    }
}