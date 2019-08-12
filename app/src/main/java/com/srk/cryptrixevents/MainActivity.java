package com.srk.cryptrixevents;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity  {
    public static TextView name1,phone1,name2,phone2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name1 = findViewById(R.id.name1);
        name2 = findViewById(R.id.name2);
        phone1 = findViewById(R.id.phone1);
        phone2 = findViewById(R.id.phone2);
    }
    public void addParticipant(View view){
        Intent intent = new Intent(this,scanner.class);
        startActivity(intent);
    }
}