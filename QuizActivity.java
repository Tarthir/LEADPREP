package ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.tylerbrady34gmail.leadprepper.R;

import model.Model;

/**
 * Created by tyler on 5/4/2017.
 * Our activity where the user chooses what quiz to do
 */

public class QuizActivity extends AppCompatActivity {
    private Fragment fragment;
    private ViewPager viewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        //THe adapter handles the movement of our quiz pages
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            float sumPostAndOffset;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position + positionOffset > sumPostAndOffset){
                    //scrolled right to left
                }
                else{
                    //scrolled left to right
                }
                sumPostAndOffset = position + positionOffset;
            }

            @Override
            public void onPageSelected(int position) {
                Model.getInstance().getCurrQuizObj().setCurrPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

}
