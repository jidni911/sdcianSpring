package com.jidnivai.sdcian.sdcian.dto;


import java.util.Date;

public class TempFileMetadata {
    public String name;
    public long size;
    public String type;
    public String url;
    public boolean important;
    public Date createdAt;

    public TempFileMetadata(String name, long size, String type, String url, boolean important, Date createdAt) {
        this.name = name;
        this.size = size;
        this.type = type;
        this.url = url;
        this.important = important;
        this.createdAt = createdAt;
    }
}
