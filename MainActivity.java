package ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

import com.tylerbrady34gmail.leadprepper.R;

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
                Intent intent = new Intent(MainActivity.this, QuizChooserActivity.class);
                startActivity(new Intent(intent));
            }
        });
    }

}
