package hust.soict.dsai.blockchain.engine.scraper;
public class News {
    private String url;
    private String websiteSource;
    private String summary;
    private String title;
    private String content;
    private String creationDate;
    private String tags;
    private String authorName;
    private String category;
    private int type;
    public News(String content) {
        this.content = content;
    };
    public News(String url,String websiteSource,int type ,String summary, String title,String content, String date, String tags, String author, String category) {
        this.url = url;
        this.websiteSource = websiteSource;
        this.type=type;
        this.summary = summary;
        this.title = title;
        this.content = content;
        this.creationDate = date;
        this.tags = tags;
        this.authorName = author;
        this.category = category;
    }
    public void displayNews() {
        System.out.println("Link: " + this.url);
        System.out.println("Category: " + this.category);
        System.out.println("Title: " + this.title);
        System.out.println("Summary: " + this.summary);
        System.out.println("Content: " + this.content);
        System.out.println("Date: " + this.creationDate);
        System.out.println("Author: " + this.authorName);
        System.out.println("Tags: " + this.tags);
        System.out.println("---------------------------------------------------------");
    }
    public String toString() {
        return "\"" + url + "\"" + ", " + "\"" +  websiteSource +"\"" + ", " +"\"" + String.valueOf(type) +"\"" +
                ", " +"\"" + summary +"\"" + ", " +"\"" + title +"\"" + ", " +"\"" + content +"\"" +
                ", " +"\"" + creationDate +"\"" + ", " +"\"" + tags +"\"" + ", "
                +"\"" + authorName +"\"" + ", " +"\"" + category +"\"" + "\n";
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWebsiteSource() {
        return websiteSource;
    }

    public void setWebsiteSource(String websiteSource) {
        this.websiteSource = websiteSource;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
