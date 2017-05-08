package ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.tylerbrady34gmail.leadprepper.R;

import model.Quiz;

/**
 * Created by tyler on 5/8/2017.
 */

public class QuizViewHolder extends RecyclerView.ViewHolder{
    private TextView mQuizText;

    QuizViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.quiz_view, parent, false));
        mQuizText = (TextView) itemView.findViewById(R.id.quiz_textview);
    }

    void bind(Quiz row) {
        mQuizText.setText(row.getName());
    }
}
