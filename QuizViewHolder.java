package ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tylerbrady34gmail.leadprepper.R;

import model.Model;
import model.Quiz;
import model.Utils;

/**
 * Created by tyler on 5/8/2017.
 */

public class QuizViewHolder extends RecyclerView.ViewHolder{
    private TextView mQuizText;
    private TextView mDescripText;
    private ImageView mEditImage;
    private ImageView mDeleteImage;
    private Context mContext;

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
                //TODO
            }
        });
    }

    void click(){
        String quizID = (String)mQuizText.getText();
        Quiz quiz = Model.getInstance().getOurQuizzes().get(quizID);
        Model.getInstance().setCurrQuiz(quiz);
        mContext.startActivity(new Intent(mContext,QuizActivity.class));
    }
}
