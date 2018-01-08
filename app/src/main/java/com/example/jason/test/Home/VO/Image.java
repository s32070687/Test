package com.example.jason.test.Home.VO;

/**
 * Created by Jason on 2018/1/8.
 */

public class Image {

    private String thumbnail;
    private String path;

    public Image(String thumbnail, String path) {
        super();
        this.thumbnail = thumbnail;
        this.path = path;

    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
