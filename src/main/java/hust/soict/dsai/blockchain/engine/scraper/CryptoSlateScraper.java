package hust.soict.dsai.blockchain.engine.scraper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class CryptoSlateScraper extends Scraper {
    private static ArrayList<News> newsList = new ArrayList<News>();
    private static ArrayList<String> droppedLinks = new ArrayList<String>();
    private static String websiteSource = "https://cryptoslate.com/";
    public CryptoSlateScraper() {};

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
        String title = newsdoc.select("h1.post-title").text();
        return Scraper.formatDoubleQuotes(title);
    }

    public String getCategories(Document newsdoc) {
        String category = "";
        String[] categories = newsdoc.select("span.big-cat").text().split(" ▸ ");
        category = String.join(", ",  categories);
        return category;
    }

    public String getSummary(Document newsdoc) {
        String summary = newsdoc.select("p.post-subheading").text();
        return Scraper.formatDoubleQuotes(summary);
    }

    public String getContent(Document newsdoc) {
        String content = newsdoc.select("article.full-article").text();
        if (content.equals("")) {
            content = "Pay to unlock.";
        }
        return Scraper.formatDoubleQuotes(content);
    }

    public String getDate(Document newsdoc) {
        String date;
        try {
            date = newsdoc.selectFirst("div.post-date").text();
        } catch (NullPointerException e) {
            date = "";
        }
        return date;
    }

    public String getAuthor(Document newsdoc) {
        String author;
        try {
            author= newsdoc.selectFirst("div.author-info").select("a").text();
        } catch (NullPointerException e) {
            author = "";
        }
        return author;
    }

    public String getTags(Document newsdoc) {
        Elements tagss = newsdoc.select("div.mentioned-items > [href] > .title");
        String tags = "";
        int index=0;
        for (Element t:tagss) {
            index += 1;
            tags += t.text();
            if (index < tagss.size()) {
                tags += " - ";
            }
        }
        return tags;
    }

    public void scrape() {
        int count = 0;
        try {
            for (String page : this.getUrlPages()) {
                String urlPage = "https://cryptoslate.com/news/page/" + page;
                Document doc = Jsoup.connect(urlPage).timeout(10 * 100000).get();
                String newsUrl;
                //get the relevant articles in page
                Element target = doc.selectFirst("div.list-feed.slate");
                Elements links = target.select("div.list-post");
                for (Element link : links) {
                    count += 1;
                    System.out.println("Article num: " + count);
                    //Get the hyperlink (article link)
                    newsUrl = link.select("a").attr("href");
                    News currentNews = getNews(newsUrl, websiteSource, newsList, droppedLinks);
                    if (!currentNews.getContent().equals("Empty link. Do not add.")) {
                        newsList.add(currentNews);
                        currentNews.displayNews();
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