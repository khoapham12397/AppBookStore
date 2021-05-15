package com.example.reviewreadurl;

public class News {
    private String title;
    private String link;
    private String pathImg;
    private String intro;

    public String getTitle() {
        return title;
    }

    public News(String title, String link, String pathImg) {
        this.title = title;
        this.link = link;
        this.pathImg = pathImg;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPathImg() {
        return pathImg;
    }

    public void setPathImg(String pathImg) {
        this.pathImg = pathImg;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
