package com.example.newsapp.modal;


/*
"name": "New York Times"
"author": "Andrew Das",
"title": "USWNT and US Soccer Settle Equal Pay Lawsuit - The New York Times",
"url": "https://www.nytimes.com/2022/02/22/sports/soccer/uswnt-equal-pay-lawsuit.html",
"urlToImage": "https://static01.nyt.com/images/2022/02/22/sports/22soccer-equalpay-01/22soccer-equalpay-01-facebookJumbo.jpg",
* */
public class News {
    private String name;
    private String author;
    private String title;
    private String url;
    private String urlToImage;

    public News() {
    }

    public News(String name, String author, String title, String url, String urlToImage) {
        this.name = name;
        this.author = author;
        this.title = title;
        this.url = url;
        this.urlToImage = urlToImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }
}
