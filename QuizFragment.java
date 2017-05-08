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
    /**Shows answer/question*/
    private Button mToggleTextButton;
    /**Tag for our logger*/
    private final String TAG = "QuizFrag";
    /**If we are showing the question or answer*/
    private boolean showingQuestion = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"OncreateView Entered");
        View v = inflater.inflate(R.layout.fragment_quizview, container, false);
        mToggleTextButton = (Button) v.findViewById(R.id.toggle_button);
        mToggleTextButton.setText(R.string.button_showA);
        mQuizText = (TextView) v.findViewById(R.id.quiz_textview);
        mQuizText.setText(getArguments().getString("msg"));//set quiz text
        setupClickers();
        return  v;
    }
    /**Sets up clickers for our member variables*/
    private void setupClickers() {
        mToggleTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showingQuestion) {//if we are currently showing the answer
                    Log.d(TAG,"Showing answer");
                    mToggleTextButton.setText(R.string.button_showQ);
                    //show the answer
                    mQuizText.setText(Model.getInstance().getCurrQuizObj().getCurrAnswerText());
                }
                else {
                    Log.d(TAG,"Showing question");
                    mToggleTextButton.setText(R.string.button_showA);
                    //show the question
                    mQuizText.setText(Model.getInstance().getCurrQuizObj().getCurrQuestionText());
                }
                //toggle boolean
                showingQuestion = !showingQuestion;
            }
        });
    }

    public static QuizFragment newInstance() {
        QuizFragment f = new QuizFragment();
        Bundle b = new Bundle();
        b.putString("msg",Model.getInstance().getCurrQuizObj().getCurrQuestionText());
        f.setArguments(b);

        return f;
    }
}
