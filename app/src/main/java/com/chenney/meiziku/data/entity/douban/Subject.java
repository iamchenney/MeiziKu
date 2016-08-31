package com.chenney.meiziku.data.entity.douban;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/8/30.
 */
public class Subject {
    private String id;
    private String alt;
    private String year;
    private String subtype;
    private String title;
    @SerializedName(value = "original_title")
    private String originalTitle;
    @SerializedName(value = "collect_count")
    private String collectCount;
    private String[] genres;
    private User[] casts;
    private User[] directors;
    private Image images;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(String collectCount) {
        this.collectCount = collectCount;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public User[] getCasts() {
        return casts;
    }

    public void setCasts(User[] casts) {
        this.casts = casts;
    }

    public User[] getDirectors() {
        return directors;
    }

    public void setDirectors(User[] directors) {
        this.directors = directors;
    }

    public Image getImages() {
        return images;
    }

    public void setImages(Image images) {
        this.images = images;
    }
}
