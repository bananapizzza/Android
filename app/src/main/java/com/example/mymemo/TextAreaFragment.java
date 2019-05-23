package com.example.mymemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class TextAreaFragment extends Fragment {
    private final String TAG = this.getClass().getName();
    ComposeActivity activity;
    EditText title;
    EditText content;
    int position;           //save the memo's position. if it's a new memo, position is -1


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (ComposeActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_text_area, container, false);
        title = rootView.findViewById(R.id.editTextTitle);
        content = rootView.findViewById(R.id.editTextContent);
        setText();
        return rootView;
    }

    private void setText(){
        Log.d(TAG, "setText");
        Bundle args = getArguments();
        if(args != null){
            Log.d(TAG, "setText: argument is not null");
            String titleString = args.getString("title");
            String contentString = args.getString("content");
            title.setText(titleString);
            content.setText(contentString);
        }
    }
}
