package ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.tylerbrady34gmail.leadprepper.R;

/**
 * Created by tyler on 5/11/2017.
 */

public class NewSlideFragment extends DialogFragment{

    private EditText mQuestion;
    private EditText mAnswer;
    private Button mAddButton;
    private Button mSubmitQuizButton;

    public NewSlideFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }


    public static NewSlideFragment newInstance() {
        NewSlideFragment frag = new NewSlideFragment();
        Bundle args = new Bundle();
        args.putString("title", "New Quiz");
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_slide, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        mQuestion = (EditText) view.findViewById(R.id.enter_question_edit);
        mAnswer = (EditText) view.findViewById(R.id.enter_answer_edit);
        mAddButton = (Button) view.findViewById(R.id.add_button_slide);
        mSubmitQuizButton = (Button) view.findViewById(R.id.submit_quiz_button);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if they have entered in something
                if(mQuestion.getText().length() > 0 && mAnswer.getText().length() > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("keepgoing",true);
                    click(bundle);
                    // Close the dialog and return back to the parent activity
                    dismiss();
                }
            }
        });
        mSubmitQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("keepgoing",false);
                click(bundle);
                dismiss();
            }
        });

        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        // Show soft keyboard automatically and request focus to field
        mQuestion.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    void click(Bundle bundle){
        bundle.putString("question", mQuestion.getText().toString());
        bundle.putString("answer", mAnswer.getText().toString());
        // Return input text back to activity through the implemented listener
        EditDialogListener listener = (EditDialogListener) getActivity();
        listener.onFinishedDialogListener(bundle);
    }
}
