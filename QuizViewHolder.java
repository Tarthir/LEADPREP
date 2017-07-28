package ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tylerbrady34gmail.leadprepper.R;

import java.io.IOException;

import model.Model;
import model.Quiz;
import model.Utils;

/**
 * Created by tyler on 5/8/2017.
 * The implementation of our views for our recycler view
 */

public class QuizViewHolder extends RecyclerView.ViewHolder{
    /**The quiz's name*/
    private TextView mQuizText;
    /**The description of the quiz*/
    private TextView mDescripText;
    /**The Edit image*/
    private ImageView mEditImage;
    /**The delete Image*/
    private ImageView mDeleteImage;
    /**The context passed into this class*/
    private Context mContext;

    //TODO need to not be able to delete default quizzes

    QuizViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.quiz_view, parent, false));
        mQuizText = (TextView) itemView.findViewById(R.id.quiz_text);
        mDescripText = (TextView) itemView.findViewById(R.id.descrip_text);
        mEditImage = (ImageView) itemView.findViewById(R.id.edit_image);
        mDeleteImage = (ImageView) itemView.findViewById(R.id.delete_image);
        mContext = parent.getContext();
    }

    void bind(Quiz row) {
        mQuizText.setText(row.getName());
        mQuizText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click();
            }
        });
        mDescripText.setText(row.getDescription());
        mDescripText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click();
            }
        });
        mEditImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
        mDeleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Utils.deleteFile(mContext, Model.getInstance().getOurQuizzes().get(mQuizText).getFileNum());
                } catch (IOException e) {
                    Log.e("Deleting a quiz",e.getMessage());
                    Toast.makeText(mContext,"Error deleting Quiz, please try again",Toast.LENGTH_LONG);
                    e.printStackTrace();
                }
                //TODO make this work, ALSO have Dialog box come up to make sure they want to delete!
            }
        });
    }
    /**Called when the textviews are clicked. It starts our next activity*/
    private void click(){
        String quizID = (String)mQuizText.getText();
        Quiz quiz = Model.getInstance().getOurQuizzes().get(quizID);
        Model.getInstance().setCurrQuiz(quiz);
        mContext.startActivity(new Intent(mContext,QuizActivity.class));
    }
}
