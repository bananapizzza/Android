package com.example.mymemo;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity
public class Memo {
    @PrimaryKey
    @NonNull
    private long memoID;
    private String title;
    private String content;

    Memo(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @NonNull
    public long getMemoID() {
        return memoID;
    }

    public void setMemoID(@NonNull long memoID) {
        this.memoID = memoID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
