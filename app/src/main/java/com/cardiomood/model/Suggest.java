package com.cardiomood.model;

/**
 * Created by apple on 9/29/17.
 */

public class Suggest {
    String text;

    public Suggest(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
