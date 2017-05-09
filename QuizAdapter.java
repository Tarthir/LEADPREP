package ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import model.Quiz;

/**
 * Created by tyler on 5/8/2017.
 */

public class QuizAdapter extends RecyclerView.Adapter<QuizViewHolder>{
    private ArrayList<Quiz> mRows;
    public QuizAdapter(Context context, ArrayList<Quiz> qRows) {
        super();
        mRows = qRows;
    }

    @Override
    public QuizViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new QuizViewHolder(layoutInflater, parent);
    }

    @Override
    public void onBindViewHolder(QuizViewHolder holder, int position) {
        holder.bind(mRows.get(position));
    }

    @Override
    public int getItemCount() {
        return mRows.size();
    }
}
