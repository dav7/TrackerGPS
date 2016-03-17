package com.example.david.trackergps;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editTextPhone;
    EditText editTextPass;
    String pass=null;
    Boolean modeInit=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context myContext = this.getApplicationContext();

        //Numero Téléphone
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextPass = (EditText) findViewById(R.id.editTextPass);


        //Bouton de déblocage

        Button ButtonUnlock = (Button) findViewById(R.id.buttonUnlock);
        ButtonUnlock.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }

        });

        //Démarrage

        Button buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //Stop

        Button buttonStop = (Button) findViewById(R.id.buttonStop);
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
            public void onPause() {
        super.onPause();

    }
}