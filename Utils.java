package model;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

import ui.NewQuizFragment;

/**
 * Created by tyler on 5/11/2017.
 */

public class Utils {
    public static final String PREFS_NAME = "MyPrefsFile";

    public static void deleteFile(Context context, String fileName){
        context.deleteFile(fileName);
        Model.getInstance().getOurQuizzes().remove(fileName);
    }
}
