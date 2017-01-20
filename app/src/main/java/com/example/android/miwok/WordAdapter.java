package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by moritzmoldenhauer on 20/01/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {


    ArrayList<Word> words = new ArrayList<>();
    public WordAdapter(Context context, ArrayList<Word> list) {
        super(context, 0, list);
        words.addAll(list);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if(view == null) {
            view = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        TextView defaultText = (TextView) view.findViewById(R.id.default_text_View);
        defaultText.setText(words.get(position).getDefaultTranslation());

        TextView miwokText = (TextView) view.findViewById(R.id.miwok_text_view);
        miwokText.setText(words.get(position).getMiwokTranslation());

        return view;
    }
}
