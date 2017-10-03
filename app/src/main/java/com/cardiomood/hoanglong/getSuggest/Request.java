package com.cardiomood.hoanglong.getSuggest;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by apple on 9/19/17.
 */

public class Request {


    @SerializedName("writing_guide")
    @Expose
    private WritingGuide writingGuide;
    @SerializedName("ink")
    @Expose
    private List<List<List<Integer>>> ink = null;
    @SerializedName("language")
    @Expose
    private String language;

    public Request(WritingGuide writingGuide, List<List<List<Integer>>> ink, String language) {
        this.writingGuide = writingGuide;
        this.ink = ink;
        this.language = language;
    }

    public WritingGuide getWritingGuide() {
        return writingGuide;
    }

    public void setWritingGuide(WritingGuide writingGuide) {
        this.writingGuide = writingGuide;
    }

    public List<List<List<Integer>>> getInk() {
        return ink;
    }

    public void setInk(List<List<List<Integer>>> ink) {
        this.ink = ink;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "{" +
                "\"writing_guide\":\n" + writingGuide +
                ",\n \"ink\":" + ink +
                ",\n \"language\":\"" + language + '\"' +
                "}";
    }
}
