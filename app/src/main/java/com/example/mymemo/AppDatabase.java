package com.example.mymemo;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Memo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MemoDAO memoDAO();
}
