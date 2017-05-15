package model;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tyler on 5/5/2017.
 * A singleton class for quizzes, since we only want one quiz at a time
 */
public class Quiz implements Serializable {
    private List<String> currQuiz;
    private List<String> currQuizAnswers;
    private String name;
    private String description;
    //How many Quizzes we have saved
    private static int count = 0;

    public Quiz(String name, String[] newQuiz,String[] newQAnswers) {
        this.name = name;
        this.currQuiz = new ArrayList<>(Arrays.asList(newQuiz));
        this.currQuizAnswers = new ArrayList<>(Arrays.asList(newQAnswers));
    }
    public Quiz(String name,String description, String[] newQuiz,String[] newQAnswers) {
        this.name = name;
        this.currQuiz = new ArrayList<>(Arrays.asList(newQuiz));
        this.currQuizAnswers = new ArrayList<>(Arrays.asList(newQAnswers));
        this.description = description;
    }
    public Quiz(String name,String description) {
        this.name = name;
        this.description = description;
    }

    public Quiz(String name,String description,ArrayList<String> newQ, ArrayList<String> newQAnswers) {
        this.name = name;
        this.description = description;
        this.currQuiz = newQ;
        this.currQuizAnswers = newQAnswers;
    }

    public static int getCount() {
        return count;
    }

    /**
     * Gets the current text at the given position and then increments the position counter
     * @param currPosition , the current position of the cards
     * @return String, The text for the current spot
     */
    public String getCurrQuestionText(int currPosition) {
        return currQuiz.get(currPosition);
    }

    /**Gets text for the answer to the current question
     * @param currPosition , the current position of the cards
     * @return String, the text for the answer*/
    public String getCurrAnswerText(int currPosition){
        return currQuizAnswers.get(currPosition);
    }

    public String getName() {
        return name;
    }

    public List<String> getCurrQuiz() {
        return currQuiz;
    }

    public String getDescription() {
        return description;
    }
    /**Set the count static variable*/
    public static void setCount(int counter) {
        count = counter;
    }

    public void saveQuiz(Context context) throws IOException{
        String fileName = "my_data" + count;
        FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(this);
        os.close();
        fos.close();
        count++;//more files to go through
        //save the number of quizzes created
        SharedPreferences settings = context.getSharedPreferences(Utils.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("numOfQuizzes",count);
        editor.apply();

    }


}

