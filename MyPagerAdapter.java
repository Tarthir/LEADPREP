package ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import model.Model;

/**
 * Created by tyler on 5/6/2017.
 * THe adapter for our Page Viewer
 */

public class MyPagerAdapter extends FragmentPagerAdapter {
    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        CardContainerFragment frag = new CardContainerFragment();//makes the new fragment with the right text
        Bundle bundle = new Bundle();
        bundle.putInt("args",pos);
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public int getCount() {
        if(Model.getInstance().getCurrQuizObj() != null) {
            return Model.getInstance().getCurrQuizObj().getCurrQuiz().size();
        }
        return 0;
    }

}
