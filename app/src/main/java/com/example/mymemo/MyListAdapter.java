package com.example.mymemo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

// TODO: test with ArrayAdapter<Memo>
public class MyListAdapter extends ArrayAdapter<Memo> {
    private final Activity context;
    //private final String[] title;
    //private final String[] content;
    private List<String> title;
    private List<String> content;
    private List<Memo> memoList;

//    public MyListAdapter(Activity context, String[] title, String[] content){
//        super(context, R.layout.list_item, title);
//        this.context = context;
//        this.title = title;
//        this.content = content;
//    }

    public MyListAdapter(Activity context, List<Memo> memos){
        super(context, R.layout.list_item, memos);
        this.context = context;
        memoList = memos;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listItem = view;
        if(listItem == null) {
            listItem = inflater.inflate(R.layout.list_item, parent, false);
        }
        Memo currentMemo = memoList.get(position);

        TextView titleText = listItem.findViewById(R.id.title);
        TextView contentText = listItem.findViewById(R.id.content);
        titleText.setText(currentMemo.getTitle());
        contentText.setText(currentMemo.getContent());
        return listItem;
    }
}
