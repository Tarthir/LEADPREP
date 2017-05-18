package ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.nineoldandroids.animation.ObjectAnimator;

import com.tylerbrady34gmail.leadprepper.R;

import model.Model;

/**
 * Created by tyler on 5/9/2017.
 * A wrapper which holds our cards in aour page viewer
 */

public class CardContainerFragment extends Fragment implements QuizFragment.OnChildFragmentInteractionListener {
    /**Boolean, lets us know is the card is flipped or not*/
    private boolean cardFlipped = false;
    /**The position in the viewpager we are at*/
    private int cardPos;
    /**The current fragment*/
    private QuizFragment currFrag;

    @Override
    public void messageFromChildToParent() {
        Log.d("cardcontainer","Sending message from child to parent");
        flipCard();
    }


    public CardContainerFragment() {
    //empty
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_cardcontainer, container, false);
        currFrag = new QuizFragment();
        cardPos = getArguments().getInt("args");
        Bundle bundle = new Bundle();
        bundle.putBoolean("args",false);
        bundle.putInt("pos",cardPos);
        currFrag.setArguments(bundle);

        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.container, currFrag)
                .commit();
        return rootView;
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