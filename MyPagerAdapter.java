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
        if(Model.getInstance().getCurrQuizObj() != null) {
            Model.getInstance().getCurrQuizObj().setCurrPosition(pos);//update the current position
        }
        return QuizFragment.newInstance();
    }

    @Override
    public int getCount() {
        if(Model.getInstance().getCurrQuizObj() != null) {
            return Model.getInstance().getCurrQuizObj().getCurrQuiz().length;
        }
        return 1;
    }
}
