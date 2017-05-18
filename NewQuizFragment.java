package ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tylerbrady34gmail.leadprepper.R;

import model.Model;

/**
 * Created by tyler on 5/11/2017.
 */

public class NewQuizFragment extends DialogFragment {
    private EditText mQuizName;
    private EditText mDescriptor;
    private Button mSubmitButton;



    public NewQuizFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }


    public static NewQuizFragment newInstance() {
        NewQuizFragment frag = new NewQuizFragment();
        Bundle args = new Bundle();
        args.putString("title", "New Quiz");
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_quiz, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        mQuizName = (EditText) view.findViewById(R.id.enter_quiz_name_);
        mDescriptor = (EditText) view.findViewById(R.id.enter_quiz_descriptor);
        mSubmitButton = (Button) view.findViewById(R.id.submit_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if they have entered in something
                if(mQuizName.getText().length() > 0 && mDescriptor.getText().length() > 0) {
                    //if this quiz name has not already been used
                    if(!Model.getInstance().getOurQuizzes().containsKey(mQuizName.getText().toString())) {
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("keepgoing", true);
                        bundle.putString("name", mQuizName.getText().toString());
                        bundle.putString("descriptor", mDescriptor.getText().toString());
                        // Return input text back to activity through the implemented listener
                        EditDialogListener listener = (EditDialogListener) getActivity();
                        listener.onFinishedDialogListener(bundle);
                        // Close the dialog and return back to the parent activity
                        dismiss();
                    }
                    else{
                        Toast.makeText(getActivity(),"Quiz name already used, please change the name",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        // Show soft keyboard automatically and request focus to field
        mQuizName.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }


}
