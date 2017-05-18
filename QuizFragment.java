package ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tylerbrady34gmail.leadprepper.R;

import model.Model;

/**
 * Created by tyler on 5/4/2017.
 * Our quiz fragment
 */

public class QuizFragment extends Fragment {
    /**Our textview for the answer/question*/
    private TextView mQuizText;
    /**Tag for our logger*/
    private final String TAG = "QuizFrag";
    /**If we are showing the question or answer*/
    private boolean isFlipped = true;
    /**Listens for message to send to parent activity*/
    private OnChildFragmentInteractionListener mParentListener;
    /**Goes to parent and calls flipcard() method*/
    public interface OnChildFragmentInteractionListener {
        void messageFromChildToParent();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"OncreateView Entered");
        //get whether the card is flipped or not
        isFlipped = (boolean)getArguments().get("args");
        int pos = (int)getArguments().get("pos");//where we are in the cards
        View v = inflater.inflate(R.layout.fragment_quizview, container, false);
        mQuizText = (TextView) v.findViewById(R.id.quiz_textview);
        //setup listener to parent
        mParentListener = (OnChildFragmentInteractionListener) getParentFragment();
        //setup on click to flip card
        mQuizText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mParentListener.messageFromChildToParent();
            }
        });

        if(!isFlipped){
            if(mQuizText.getText().equals("")) {
                mQuizText.setText(Model.getInstance().getCurrQuizObj().getCurrQuestionText(pos));//set quiz text
            }
        }
        else{
            if(mQuizText.getText().equals("")) {
                mQuizText.setText(Model.getInstance().getCurrQuizObj().getCurrAnswerText(pos));//set answer text
            }
        }
        return  v;
    }


}
