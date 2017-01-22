package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by moritzmoldenhauer on 20/01/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {


    public WordAdapter(Context context, ArrayList<Word> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Word word = getItem(position);

        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        TextView defaultText = (TextView) view.findViewById(R.id.default_text_view);
        defaultText.setText(word.getDefaultTranslation());

        TextView miwokText = (TextView) view.findViewById(R.id.miwok_text_view);
        miwokText.setText(word.getMiwokTranslation());

        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        if(word.getImageResourceId() != 0) {
            imageView.setImageResource(word.getImageResourceId());
        } else {
            imageView.setVisibility(View.GONE);
        }

        return view;
    }
}
