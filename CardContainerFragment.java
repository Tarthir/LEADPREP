package ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.tylerbrady34gmail.leadprepper.R;

import model.Model;

/**
 * Created by tyler on 5/9/2017.
 * A wrapper which holds our cards in aour page viewer
 */

public class CardContainerFragment extends Fragment {
    /**Boolean, lets us know is the card is flipped or not*/
    private boolean cardFlipped = false;
    private int cardPos;
    public CardContainerFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_cardcontainer, container, false);
        QuizFragment frag = new QuizFragment();
        cardPos = getArguments().getInt("args");
        Bundle bundle = new Bundle();
        bundle.putBoolean("args",false);
        bundle.putInt("pos",cardPos);
        frag.setArguments(bundle);

        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.container, frag)
                .commit();
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.card, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_flip:
                flipCard();
                return true;
        }

        return false;
    }

    public void flipCard() {
        Fragment newFragment;
        if (cardFlipped) {
            newFragment = new QuizFragment();
        } else {
            newFragment = new QuizFragment();
        }
        Bundle bundle = new Bundle();
        bundle.putInt("pos",cardPos);
        bundle.putBoolean("args",!cardFlipped);
        newFragment.setArguments(bundle);//pass along if the card is flipped or not

        getChildFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.card_flip_right_in,
                        R.anim.card_flip_right_out,
                        R.anim.card_flip_left_in,
                        R.anim.card_flip_left_out)
                .replace(R.id.container, newFragment)
                .commit();

        cardFlipped = !cardFlipped;
    }

}