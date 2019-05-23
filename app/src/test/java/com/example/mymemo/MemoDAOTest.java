package com.example.mymemo;

import android.arch.persistence.room.Room;
import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class MemoDAOTest {
    private MemoDAO memoDAO;
    private AppDatabase db;
    private Context context;

    @Before
    public void createDB(){
        db = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class).build();
        memoDAO = db.memoDAO();
    }

    @After
    public void closeDB() throws IOException {
        db.close();
    }

    @Test
    public void writeMemoAndReadInList() throws Exception {
        Memo memo = new Memo("Hello", "This is memo test");
        memoDAO.insertMemo(memo);
        List<Memo> memos = memoDAO.getAllMemo();

        assertThat(memos.get(0), equalTo(memo));
    }
}