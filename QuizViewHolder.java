package ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tylerbrady34gmail.leadprepper.R;

import model.Model;
import model.Quiz;

/**
 * Created by tyler on 5/8/2017.
 */

public class QuizViewHolder extends RecyclerView.ViewHolder{
    private TextView mQuizText;
    private Context mContext;

    QuizViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.quiz_view, parent, false));
        mQuizText = (TextView) itemView.findViewById(R.id.quiz_text);
        mContext = parent.getContext();
    }

    void bind(Quiz row) {
        mQuizText.setText(row.getName());
        mQuizText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quizID = (String)mQuizText.getText();
                Quiz quiz = Model.getInstance().getOurQuizzes().get(quizID);
                Model.getInstance().setCurrQuiz(quiz);
                mContext.startActivity(new Intent(mContext,QuizActivity.class));
            }
        });
        //TODO add on click listener
    }
}
