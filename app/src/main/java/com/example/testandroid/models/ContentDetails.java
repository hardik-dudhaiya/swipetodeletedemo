package com.example.testandroid.models;

import java.io.Serializable;

public class ContentDetails implements Serializable {

    private int mediaId;
    private String mediaUrl;
    private String mediaUrlBig;
    private String mediaType;
    private MediaDate mediaDate;
    private String mediaTitleCustom;

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getMediaUrlBig() {
        return mediaUrlBig;
    }

    public void setMediaUrlBig(String mediaUrlBig) {
        this.mediaUrlBig = mediaUrlBig;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public MediaDate getMediaDate() {
        return mediaDate;
    }

    public void setMediaDate(MediaDate mediaDate) {
        this.mediaDate = mediaDate;
    }

    public String getMediaTitleCustom() {
        return mediaTitleCustom;
    }

    public void setMediaTitleCustom(String mediaTitleCustom) {
        this.mediaTitleCustom = mediaTitleCustom;
    }
}
