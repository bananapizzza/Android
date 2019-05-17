package com.example.mymemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int COMPOSEACTIVITY = 100;
    ListView listView;
    ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("All notes");
        toolbar.setNavigationIcon(R.drawable.round_menu_black_18);
        setSupportActionBar(toolbar);

        //Set add floating button and listener
        FloatingActionButton addFloatingButton = (FloatingActionButton) findViewById(R.id.addFloatingButton);
        addFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ComposeActivity.class);
                startActivityForResult(intent, COMPOSEACTIVITY);
            }
        });

        //Set listView and adapter
        listView = (ListView)findViewById(R.id.memoList);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ComposeActivity.class);
                String content = ((TextView)view).getText().toString();
                intent.putExtra("content", content);
                intent.putExtra("position", position);
                startActivityForResult(intent, COMPOSEACTIVITY);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_search) {
            Toast.makeText(this, "Search clicked", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == COMPOSEACTIVITY){
            if(resultCode == Activity.RESULT_OK) {
                String title = data.getStringExtra("title");
                String content = data.getStringExtra("content");
                int position = data.getIntExtra("position", -1);
                if(position == -1) {
                    //in case of new memo
                    if (title != null || content != null) {
                        if (title != null) {
                            content = title + "\n" + content;
                        }
                        listItems.add(content);
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    //in case of existing memo
                    listItems.set(position, content);
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }
}
