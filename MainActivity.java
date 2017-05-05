package ui;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.tylerbrady34gmail.leadprepper.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by tyler on 5/4/2017.
 * Our main activity which is our title screen
 */

public class MainActivity extends AppCompatActivity {
    final String TAG = "MainActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button leadButton = (Button) findViewById(R.id.lead_button);
        Button quizButton = (Button) findViewById(R.id.quiz_button);

        leadButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Lead Manual button clicked");
                startActivity(new Intent(MainActivity.this,MyPdfViewActivity.class));
            }
        });

        quizButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Quiz button clicked");
                Intent intent = new Intent(MainActivity.this,QuizChooserActivity.class);
                startActivity(new Intent(intent));
            }
        });
    }

}
