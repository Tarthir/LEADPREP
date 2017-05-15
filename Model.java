package model;

import com.tylerbrady34gmail.leadprepper.R;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by tyler on 5/5/2017.
 * Our model class which holds the data for the quizzes
 */
public class Model {
    private static Model ourInstance;
    /**The name of the quiz is the key and the Quiz object is the value*/
    private Map<String,Quiz> ourQuizzes = new TreeMap<>();;
    /**The currently selected quiz*/
    private Quiz currQuiz;
    /**Position in deck of cards for the quiz*/
    private int currPos = 0;
    /**Current new quiz name and description*/
    private Quiz newQuiz;
    //TODO NEED TO SAVE QUIZZES

    private Model() {
    }

    public static Model getInstance() {

        if(ourInstance == null){
            ourInstance = new Model();
        }
        return ourInstance;
    }
    /**A function to add quizzes to the model class
     * @param newQuiz the quiz object*/
    public void addQuiz(Quiz newQuiz){
        ourQuizzes.put(newQuiz.getName(),newQuiz);
    }

    public Quiz getCurrQuizObj() {
        return currQuiz;
    }

    public void setCurrQuiz(Quiz currQuiz) {
        this.currQuiz = currQuiz;
    }

    public Map<String, Quiz> getOurQuizzes() {
        return ourQuizzes;
    }

    public int getCurrPos() {
        return currPos;
    }
    public void incrementPos(){
        ++currPos;
    }
    public  void decrementPos(){
        --currPos;
    }
    public void setPos(int pos){
        currPos = pos;
    }

    public Quiz getNewQuiz() {
        return newQuiz;
    }

    public void setNewQuiz(Quiz newQuiz) {
        this.newQuiz = newQuiz;
    }
}
