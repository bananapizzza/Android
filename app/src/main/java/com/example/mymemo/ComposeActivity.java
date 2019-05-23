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
    int position;           //save the memo's position. if it's a new memo, position is -1
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        //Set views
        setToolbar();
        setComposeScreen();
        setText();
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
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setToolbar(){
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    private void setComposeScreen(){
        title = findViewById(R.id.editTextTitle);
        content = findViewById(R.id.editTextContent);
    }

    private void setText(){
        Intent intent = getIntent();
        if (intent != null) {
            if(!isNewMemo(intent)) {
                //in case of existing memo
                title.setText(intent.getStringExtra("title"));
                content.setText(intent.getStringExtra("content"));
            }
        }
    }

    private boolean isNewMemo(Intent data){
        position = data.getIntExtra("position", -1);
        return position == -1? true : false;
    }
}
