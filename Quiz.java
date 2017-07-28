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
    /**The current quiz questions*/
    private List<String> currQuiz;
    /**The curr quiz answers*/
    private List<String> currQuizAnswers;
    /**The name of the quiz*/
    private String name;
    /**The description of the quiz*/
    private String description;
    /**The file number of this quiz*/
    private int fileNum;

    public Quiz(String name, String[] newQuiz,String[] newQAnswers) {
        this.name = name;
        this.currQuiz = new ArrayList<>(Arrays.asList(newQuiz));
        this.currQuizAnswers = new ArrayList<>(Arrays.asList(newQAnswers));
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

    public void setFileNum(int fileNum) {
        this.fileNum = fileNum;
    }

    public int getFileNum(){
        return this.fileNum;
    }

}

