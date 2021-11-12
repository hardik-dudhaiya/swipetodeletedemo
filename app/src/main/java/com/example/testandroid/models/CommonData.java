package com.example.testandroid.models;

import java.io.Serializable;
import java.util.List;

public class CommonData implements Serializable {

    private boolean status;
    private String lang;
    private List<ContentDetails> content;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public List<ContentDetails> getContent() {
        return content;
    }

    public void setContent(List<ContentDetails> content) {
        this.content = content;
    }
}
