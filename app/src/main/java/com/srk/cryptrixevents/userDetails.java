package com.srk.cryptrixevents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class userDetails extends AppCompatActivity {

    DatabaseReference db;
    teamDetails teamDetailsObject;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        try{
            Intent intent = getIntent();
            final String teamName = intent.getStringExtra("TeamName");
            tv= findViewById(R.id.display);
            tv.setText(teamName);
            db = FirebaseDatabase.getInstance().getReference("Mysterio").child(teamName);
            teamDetailsObject = new teamDetails();
            db.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    try {
                        teamDetailsObject = dataSnapshot.getValue(teamDetails.class);
                        if(teamDetailsObject.r1 && teamDetailsObject.r2) {
                            Button bt = findViewById(R.id.button1);
                            bt.setVisibility(View.VISIBLE);
                            bt=findViewById(R.id.button2);
                            bt.setVisibility(View.VISIBLE);
                            tv = findViewById(R.id.participantOne);
                            tv.setText(teamDetailsObject.n1);
                            tv = findViewById(R.id.participantTwo);
                            tv.setText(teamDetailsObject.n2);
                            tv = findViewById(R.id.college);
                            tv.setText(teamDetailsObject.c1);
                            tv = findViewById(R.id.collegeTwo);
                            tv.setText(teamDetailsObject.collegeNameTwo);
                            tv = findViewById(R.id.phoneNumberOne);
                            tv.setText(teamDetailsObject.pn1);
                            tv = findViewById(R.id.phoneNumberTwo);
                            tv.setText(teamDetailsObject.pn2);
                        }
                        else
                        {
                            Button bt = findViewById(R.id.button1);
                            bt.setVisibility(View.VISIBLE);
                            tv = findViewById(R.id.participantOne);
                            tv.setText(teamDetailsObject.n1);
                            tv = findViewById(R.id.college);
                            tv.setText(teamDetailsObject.c1);
                            tv = findViewById(R.id.phoneNumberOne);
                            tv.setText(teamDetailsObject.pn1);
                        }

                    }
                    catch (Exception e){
                        Toast.makeText(getApplicationContext(), "PLEASE TRY AGAIN!", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        catch (Exception e){
            Log.e("HERE",e.getMessage());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void callParticipant(View view){
        switch (view.getId()){
            case R.id.button1 :
                try {
                    if (teamDetailsObject.r1) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL,Uri.fromParts("tel",teamDetailsObject.pn1,null));
                        startActivity(callIntent);
                    } else {
                        Toast.makeText(this, "SOME INTERNAL ERROR! SORRY!", Toast.LENGTH_LONG).show();
                    }
                    break;
                }
                catch(Exception e){
                    Log.e("HERE",e.getMessage());
                    Toast.makeText(this, "ERROR..TRY AGAIN", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            case R.id.button2 :
                try {
                    if (teamDetailsObject.r2) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL,Uri.fromParts("tel", teamDetailsObject.pn2, null));
                        startActivity(callIntent);
                    } else {
                        Toast.makeText(this, "SOME INTERNAL ERROR! SORRY!", Toast.LENGTH_LONG).show();
                    }
                    break;
                }
                catch(Exception e){
                    Log.e("HERE",e.getMessage());
                    Toast.makeText(this, "ERROR..TRY AGAIN", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            default: break;
        }
    }
}