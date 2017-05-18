package model;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import ui.NewQuizFragment;

/**
 * Created by tyler on 5/11/2017.
 */

public class Utils {
    static final String PREFS_NAME = "MyPrefsFile";
    static final int defaultNumOfQuzzes = 7;

    /**Deletes a file from the list of quizzes
     * @param context where this was called from
     * @param position the position of this quiz
     * */
    public static void deleteFile(Context context, int position) throws IOException {
        String fileName = "my_data" + position;
        ArrayList<Quiz> quizzes = new ArrayList<>();
        quizzes.addAll(Model.getInstance().getOurQuizzes().values());
        //TODO  add int delete and edit buttons to viewholder

        context.deleteFile(fileName);
        for(int i = position; i < Quiz.getCount(); i++){
            if(i+1 >= quizzes.size()){
                break;
            }
            Quiz thisQuiz = quizzes.get(i+1);//quizz at this position
            saveQuiz(context,i,thisQuiz);//save at the new position
        }
        Quiz.setCount(Quiz.getCount()-1);
        Model.getInstance().getOurQuizzes().remove(fileName);
    }
    /**Loads a file at a given index*/
    public static void loadFile(Context context,int index) throws IOException, ClassNotFoundException {
        //dont need to load the defaults because they are loaded up in the mainactivity
        String fileName = "my_data" + index;//grab all the files containing objects that have been saved
        FileInputStream fis = context.openFileInput(fileName);
        ObjectInputStream is = new ObjectInputStream(fis);
        Quiz quiz = (Quiz) is.readObject();
        is.close();
        fis.close();
        if(!Model.getInstance().getOurQuizzes().containsKey(quiz.getName())) {
            Model.getInstance().getOurQuizzes().put(quiz.getName(), quiz);
        }

    }

    public static void saveQuiz(Context context,int count,Quiz quiz) throws IOException{
        String fileName = "my_data" + count;
        FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(quiz);
        os.close();
        fos.close();
        count++;//more files to go through
        //save the number of quizzes created
        SharedPreferences settings = context.getSharedPreferences(Utils.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("numOfQuizzes",count);
        editor.apply();

    }

    public static int getNumOfSavedQuizzes(Context context){
        SharedPreferences settings = context.getSharedPreferences(Utils.PREFS_NAME, 0);
        return settings.getInt("numOfQuizzes",0);
    }
}
