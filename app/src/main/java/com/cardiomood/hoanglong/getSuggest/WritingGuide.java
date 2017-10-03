package com.cardiomood.hoanglong.getSuggest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by apple on 9/19/17.
 */

public class WritingGuide {
    @SerializedName("writing_area_width")
    @Expose
    private int writingAreaWidth;
    @SerializedName("writing_area_height")
    @Expose
    private int writingAreaHeight;

    public WritingGuide(int writingAreaWidth, int writingAreaHeight) {
        this.writingAreaWidth = writingAreaWidth;
        this.writingAreaHeight = writingAreaHeight;
    }

    public int getWritingAreaWidth() {
        return writingAreaWidth;
    }

    public void setWritingAreaWidth(int writingAreaWidth) {
        this.writingAreaWidth = writingAreaWidth;
    }

    public int getWritingAreaHeight() {
        return writingAreaHeight;
    }

    public void setWritingAreaHeight(int writingAreaHeight) {
        this.writingAreaHeight = writingAreaHeight;
    }

    @Override
    public String toString() {
        return "{" +
                "\"writing_area_width\":" + writingAreaWidth +
                ",\n \"writing_area_height\":" + writingAreaHeight +
                '}';
    }
}
