package com.srk.cryptrixevents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class userDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        Intent intent = getIntent();
        String uniqueId = intent.getStringExtra("barcodeValue");
        TextView tv= findViewById(R.id.display);
        tv.setText(uniqueId);
    }
}
