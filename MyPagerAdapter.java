package ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import model.Model;

/**
 * Created by tyler on 5/6/2017.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {
    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        Model.getInstance().getCurrQuizObj().setCurrPosition(pos);//update the current position
        QuizFragment frag = QuizFragment.newInstance();//makes the new fragment with the right text
        //since this method is called twice for the current fragment and the one to the right, we want to reset to pos - 1
        if(pos > 0) {
            Model.getInstance().getCurrQuizObj().setCurrPosition(pos - 1);
        }
        return frag;
    }

    @Override
    public int getCount() {
        if(Model.getInstance().getCurrQuizObj() != null) {
            return Model.getInstance().getCurrQuizObj().getCurrQuiz().length;
        }
        return 0;
    }

}
