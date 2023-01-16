package com.example.gifsearchapp;

import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;

public class GiphyResultModel {

    private String height;
    private String width;
    private String url;

    public GiphyResultModel() {
    }

    public GiphyResultModel(String height, String width, String url) {
        this.height = height;
        this.width = width;
        this.url = url;
    }

    @Override
    public String toString() {
        return url;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
