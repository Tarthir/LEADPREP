package model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
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
    /**Current new quiz name and description*/
    private Quiz newQuiz;
    /**Number of quizzes we have saved*/
    private static int numOfQuizzes = 0;

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
     * The quizzes are given the current number of quizzes as their file "number" which will help us save/load them as needed
     * @param newQuiz the quiz object
     * @return a boolean. If this quis was added and saved correctly*/
    public boolean addQuiz(Context context, Quiz newQuiz){
        newQuiz.setFileNum(getNumOfQuizzes());
        numOfQuizzes++;//increment
        //save the quiz
        try {
            Utils.saveQuiz(context,newQuiz);
            ourQuizzes.put(newQuiz.getName(),newQuiz);//add it
            return true;
        } catch (IOException e) {
            Log.e("Model:addQuiz method",e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void setNumOfQuizzes(int num){
        numOfQuizzes = num;
    }

    public void decrementNumOfQuizzes(){ numOfQuizzes--;}

    public int getNumOfQuizzes(){
        return numOfQuizzes;
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

    public Quiz getNewQuiz() {
        return newQuiz;
    }

    public void setNewQuiz(Quiz newQuiz) {
        this.newQuiz = newQuiz;
    }
}
