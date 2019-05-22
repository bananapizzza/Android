package com.example.mymemo;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MemoDAO {
    @Query("SELECT * FROM Memo")
    List<Memo> getAllMemo();

    @Insert
    long insertMemo(Memo memo);

    @Update
    void updateMemo(Memo memo);

    @Delete
    void deleteMemo(Memo memo);
}
