package com.example.VoiceTrainTT;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public class TrainsStatus extends AppCompatActivity {

    private TextView txvResult,txtdis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trains_status);
        txvResult = (TextView) findViewById(R.id.txvResult);
        txtdis = (TextView) findViewById(R.id.txtdis);


        new CountDownTimer(5000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                Intent intent=getIntent();
                String message=intent.getStringExtra(HomeActivity.msg);
                txvResult.setText(message);
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());


                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, 10);
                }
                else
                    {
                    Toast.makeText(getApplicationContext(), "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
                }
            }
        }.start();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtdis.setText(result.get(0));

                }
                break;

        }
        if(txtdis.getText().toString()=="One"){

        }
    }
}
