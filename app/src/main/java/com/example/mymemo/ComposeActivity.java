package com.example.mymemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class ComposeActivity extends AppCompatActivity {
    EditText title;
    EditText content;
    boolean isModifying;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        title = (EditText) findViewById(R.id.editTextTitle);
        content = (EditText) findViewById(R.id.editTextContent);

        Intent intent = getIntent();
        if (intent != null) {
            content.setText(intent.getStringExtra("content"));
            isModifying = true;
            position = intent.getIntExtra("position", -1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_compose, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("title", title.getText().toString());
            intent.putExtra("content", content.getText().toString());
            intent.putExtra("position", position);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            if (isModifying) {
                intent.putExtra("isModifying", true);
            } else {
                intent.putExtra("isModifying", false);
            }
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
