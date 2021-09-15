package com.yhhl.yhyq.event;

public class SearchEvent {
    private String text;

    public SearchEvent(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
