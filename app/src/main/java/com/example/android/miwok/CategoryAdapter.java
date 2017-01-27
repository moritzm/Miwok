package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by moritzmoldenhauer on 27/01/2017.
 */

public class CategoryAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String [] {"Numbers", "Family", "Colors", "Phrases"};

    public CategoryAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            return new NumbersFragment();
        }

        if(position == 1) {
            return new FamilyFragment();
        }

        if(position == 2) {
            return new ColorsFragment();
        }

        if(position == 3) {
            return new PhrasesFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
