package com.example.mymemo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

// TODO: test with ArrayAdapter<Memo>
public class MyListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] title;
    private final String[] content;

    public MyListAdapter(Activity context, String[] title, String[] content){
        super(context, R.layout.list_item, title);
        this.context = context;
        this.title = title;
        this.content = content;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_item, null, true);
        TextView titleText = rowView.findViewById(R.id.title);
        TextView contentText = rowView.findViewById(R.id.content);
        titleText.setText(title[position]);
        contentText.setText(content[position]);
        return rowView;
    }
}
