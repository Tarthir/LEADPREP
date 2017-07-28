package model;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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
 * Holds methods which have utility but are needed across the program
 */

public class Utils {
    static final String PREFS_NAME = "MyPrefsFile";

    /**Deletes a file from the list of quizzes
     * @param context where this was called from
     * @param fileNum the position of this quiz
     * */
    public static void deleteFile(Context context, int fileNum) throws IOException {
        String fileName = "my_data" + fileNum;
        //lets save the quizzes into their new correct positions
        try {
            ArrayList<Quiz> quizzes = new ArrayList<>();
            quizzes.addAll(Model.getInstance().getOurQuizzes().values());
            for (int i = fileNum; i < Model.getInstance().getNumOfQuizzes(); i++) {
                if (i + 1 >= quizzes.size()) {
                    break;
                }
                Quiz thisQuiz = quizzes.get(i + 1);//quizz at this position
                saveQuiz(context,thisQuiz);//save at the new position
            }

        }catch(Exception e){
            Log.d("Utils:deleteFile",e.getMessage());
        }
        finally {
            context.deleteFile(fileName);
            Log.d("Utils:deleteFile", "File deleted");
            Model.getInstance().decrementNumOfQuizzes();
            Model.getInstance().getOurQuizzes().remove(fileName);
        }

    }
    /**Loads a file at a given index
     * @param context the context this was called from
     * @param index the index,or fileNum*/
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
    /**Saves our quizzes to internal storage
     * @param context  the context we were called from
     * @param quiz the quiz object we are saving*/
    public static void saveQuiz(Context context,Quiz quiz) throws IOException{
        String fileName = "my_data" + quiz.getFileNum();
        FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(quiz);
        os.close();
        fos.close();
        //save the number of quizzes created
        SharedPreferences settings = context.getSharedPreferences(Utils.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("numOfQuizzes",quiz.getFileNum() + 1);//making it a 1 based index instead of zero based
        editor.apply();

    }
    /**Gets the number of saved quizzes*/
    public static int getNumOfSavedQuizzes(Context context){
        SharedPreferences settings = context.getSharedPreferences(Utils.PREFS_NAME, 0);
        return settings.getInt("numOfQuizzes",0);
    }
}
