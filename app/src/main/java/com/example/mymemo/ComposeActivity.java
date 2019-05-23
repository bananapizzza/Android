package com.example.mymemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class ComposeActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getName();
    int position;           //save the memo's position. if it's a new memo, position is -1
    FragmentManager fragmentManager;
    ToolbarFragment toolbarFragment;
    TextAreaFragment textAreaFragment;
    private static final int TOOLBARFRAGMENT = 100;
    private static final int TEXTAREAFRAGMENT = 101;

    //TODO: Change to not show hint text when there is existing text
    //TODO: Show save button in the Toolbar fragment
    //TODO: After tapping SAVE button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        //Set fragments
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        toolbarFragment = new ToolbarFragment();
        textAreaFragment = new TextAreaFragment();
        sendTextToFragment();
        transaction.add(R.id.toolbarFragment, toolbarFragment);
        transaction.add(R.id.textAreaFragment, textAreaFragment);
        transaction.commit();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_compose, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_save) {
//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            intent.putExtra("title", title.getText().toString());
//            intent.putExtra("content", content.getText().toString());
//            intent.putExtra("position", position);
//            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            setResult(Activity.RESULT_OK, intent);
//            finish();
//        }
//        return super.onOptionsItemSelected(item);
//    }


    private void sendTextToFragment(){
        Log.d(TAG, "sendTextToFragment");
        Intent intent = getIntent();
        if (intent != null) {
            Log.d(TAG, "sendTextToFragment: Intent is not null");
            if(!isNewMemo(intent)) {
                Log.d(TAG, "sendTextToFragment: in case of existing memo");
                //in case of existing memo
                String title = intent.getStringExtra("title");
                String content = intent.getStringExtra("content");
                Bundle bundle = new Bundle();
                bundle.putString("title", title);
                bundle.putString("content", content);
                textAreaFragment.setArguments(bundle);
            }
        }
    }

    private boolean isNewMemo(Intent data){
        position = data.getIntExtra("position", -1);
        return position == -1? true : false;
    }
}
