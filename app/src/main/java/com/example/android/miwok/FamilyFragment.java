package com.example.android.miwok;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyFragment extends Fragment {


    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        // Create a list of words
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("father", "әpә", R.drawable.family_father, R.raw.family_father));
        words.add(new Word("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word("son", "angsi", R.drawable.family_son, R.raw.family_son));
        words.add(new Word("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word("older brother", "taachi", R.drawable.family_older_brother,
                R.raw.family_older_brother));
        words.add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother,
                R.raw.family_younger_brother));
        words.add(new Word("older sister", "teṭe", R.drawable.family_older_sister,
                R.raw.family_older_sister));
        words.add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister,
                R.raw.family_younger_sister));
        words.add(new Word("grandmother ", "ama", R.drawable.family_grandmother,
                R.raw.family_grandmother));
        words.add(new Word("grandfather", "paapa", R.drawable.family_grandfather,
                R.raw.family_grandfather));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_family);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);

        // Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Get the {@link Word} object at the given position the user clicked on
                Word word = words.get(position);

                Helper.playMedia(getContext(), word.getAudioResourceId());
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        Helper.releaseMediaPlayer();
    }
}
