package ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tylerbrady34gmail.leadprepper.R;

/**
 * Created by tyler on 5/15/2017.
 */

public class CheckSubmitFrag extends DialogFragment {
    private Button mYesButton;
    private Button mNoButton;

    public CheckSubmitFrag() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_checksubmit, container);
    }

    public static CheckSubmitFrag newInstance() {
        return new CheckSubmitFrag();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mYesButton = (Button) view.findViewById(R.id.button_yes);
        mNoButton = (Button) view.findViewById(R.id.button_no);

        mYesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putBoolean("isYes",true);
                // Return input text back to activity through the implemented listener
                EditDialogListener listener = (EditDialogListener) getActivity();
                listener.onFinishedDialogListener(b);
                // Close the dialog and return back to the parent activity
                dismiss();
            }
        });
        mNoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putBoolean("isYes",false);
                // Return input text back to activity through the implemented listener
                EditDialogListener listener = (EditDialogListener) getActivity();
                listener.onFinishedDialogListener(b);
                // Close the dialog and return back to the parent activity
                dismiss();
            }
        });
    }

}
