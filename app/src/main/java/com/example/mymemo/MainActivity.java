package com.example.mymemo;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int COMPOSEACTIVITY = 100;
    ListView listView;
    MyListAdapter adapter;
    AppDatabase database;
    MemoDAO memoDAO;
    List<Memo> memoList;
    Toolbar toolbar;
    FloatingActionButton addFloatingButton;
    private final String TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set views and database
        setToolbar();
        setFloatingButton();
        setDatabase();
        setListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            Toast.makeText(this, "Search clicked", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, String.format("onActivityResult, requestCode %d", requestCode));
        switch (requestCode) {
            case COMPOSEACTIVITY:
                Log.d(TAG, "case COMPOSEACTIVITY");
                if (resultCode == Activity.RESULT_OK) {
                    if (isNewMemo(data)) {
                        addNewMemo(data);
                    } else {
                        changeExistingMemo(data);
                    }
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        // save to database
        super.onDestroy();
    }

    private void setToolbar() {
        Log.d(TAG, "setToolbar");
        //Set toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("All notes");
        toolbar.setNavigationIcon(R.drawable.round_menu_black_18);
        setSupportActionBar(toolbar);
    }

    private void setFloatingButton() {
        Log.d(TAG, "setFloatingButton");
        //Set add floating button and listener
        addFloatingButton = findViewById(R.id.addFloatingButton);
        addFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ComposeActivity.class);
                startActivityForResult(intent, COMPOSEACTIVITY);
            }
        });
    }

    private void setDatabase() {
        Log.d(TAG, "setDatabase");
        //Set database
        database = Room.databaseBuilder(this, AppDatabase.class, "db-memo").allowMainThreadQueries().build();
        memoDAO = database.memoDAO();

        //Fetching all memos
        memoList = memoDAO.getAllMemo();
    }

    private void setListView() {
        Log.d(TAG, "setListView");
        listView = findViewById(R.id.memoList);

        adapter = new MyListAdapter(
                this,
                memoList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ComposeActivity.class);
                View titleView = view.findViewById(R.id.title);
                View contentView = view.findViewById(R.id.content);
                String title = ((TextView) titleView).getText().toString();
                String content = ((TextView) contentView).getText().toString();
                intent.putExtra("title", title);
                intent.putExtra("content", content);
                intent.putExtra("position", position);
                startActivityForResult(intent, COMPOSEACTIVITY);
            }
        });
    }

    private boolean isNewMemo(Intent data) {
        int position = data.getIntExtra("position", -1);
        return position == -1 ? true : false;
    }

    private void addNewMemo(Intent data) {
        Log.d(TAG, "addNewMemo");
        String title = data.getStringExtra("title");
        String content = data.getStringExtra("content");
        Memo memo = new Memo(title, content);
        memoList.add(memo);
        adapter.notifyDataSetChanged();
    }

    private void changeExistingMemo(Intent data) {
        Log.d(TAG, "changeExistingMemo");
        String title = data.getStringExtra("title");
        String content = data.getStringExtra("content");
        int position = data.getIntExtra("position", -1);
        Memo memo = new Memo(title, content);
        memoList.set(position, memo);
        adapter.notifyDataSetChanged();
    }
}
