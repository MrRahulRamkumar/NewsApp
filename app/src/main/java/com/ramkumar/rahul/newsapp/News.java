package com.ramkumar.rahul.newsapp;

public class News {
    private String image;
    private String headline;
    private String description;
    private String articleUrl;
    private String source;
    private String date;

    public News(String date,String source,String image, String articleUrl, String headline, String description) {
        this.image = image;
        this.headline = headline;
        this.description = description;
        this.articleUrl = articleUrl;
        this.source = source;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
