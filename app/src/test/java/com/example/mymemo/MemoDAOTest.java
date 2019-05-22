package com.example.mymemo;

public class MemoDAOTest {
    private MemoDAO memoDAO;
    private AppDatabase db;
//
//    @Before
//    public void createDB(){
//        Context context = ApplicationProvider.getApplicationContext();
//        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
//        memoDAO = db.memoDAO();
//    }
//
//    @After
//    public void closeDB() throws IOException {
//        db.close();
//    }
//
//    @Test
//    public void writeMemoAndReadInList() throws Exception {
//        Memo memo = new Memo();
//        memo.setTitle("Hello");
//        memo.setContent("This is memo test");
//        memoDAO.insertMemo(memo);
//        List<Memo> memos = memoDAO.getAllMemo();
//        assertThat(memos.get(0), equalTo(memo));
//    }
}