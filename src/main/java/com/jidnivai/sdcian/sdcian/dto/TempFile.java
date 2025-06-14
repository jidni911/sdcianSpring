package com.jidnivai.sdcian.sdcian.dto;


import java.time.LocalDateTime;

public class TempFile {
    private String name;
    private long size;
    private String type;
    private boolean important;
    private LocalDateTime createdAt;

    public TempFile(String name, long size, String type, boolean important, LocalDateTime createdAt) {
        this.name = name;
        this.size = size;
        this.type = type;
        this.important = important;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public String getName() { return name; }
    public long getSize() { return size; }
    public String getType() { return type; }
    public boolean isImportant() { return important; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setImportant(boolean important) {
        this.important = important;
    }
}
