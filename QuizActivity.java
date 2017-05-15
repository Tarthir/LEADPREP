package ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

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
        //The adapter handles the movement of our quiz pages
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        //We will hold every card that is off screen.
        //TODO figure out how to get text not being overwritten and we wont have to do this
        viewPager.setOffscreenPageLimit(Model.getInstance().getCurrQuizObj().getCurrQuiz().size());

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
               Model.getInstance().setPos(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean bool = super.onCreateOptionsMenu(menu);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        getMenuInflater().inflate(R.menu.defaultmenu,menu);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        //IconDrawable draw = new IconDrawable(this, Iconify.IconValue.fa_angle_double_up).colorRes(R.color.white).sizeDp(40);
        //menu.getItem(0).setIcon(draw);//sets filter item to have the right icon
        return bool;
    }



}
