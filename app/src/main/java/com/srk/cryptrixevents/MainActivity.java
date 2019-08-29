package com.srk.cryptrixevents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity  {
    DatabaseReference db;
    public static TextView name1,phone1,name2,phone2,clg1,clg2;
    pojo_userDetails userDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name1 = findViewById(R.id.name1);
        name2 = findViewById(R.id.name2);
        phone1 = findViewById(R.id.phone1);
        phone2 = findViewById(R.id.phone2);
        clg1 = findViewById(R.id.clg1);
        clg2 = findViewById(R.id.clg2);
    }
    public void addParticipant(View view){
        Intent intent = new Intent(this,scanner.class);
        startActivity(intent);
    }
    public void registerParticipants(View view){
        try {
            userDetails = pojo_userDetails.getInstanceOf();
            EditText et = findViewById(R.id.teamId);
            if (!et.getText().toString().isEmpty()) {
                final String teamName = "mysterio" + et.getText().toString();
                if (userDetails.isr1() && userDetails.isr2()) {
                    db = FirebaseDatabase.getInstance().getReference("Mysterio");
                    db.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (!dataSnapshot.child(teamName).exists()) {
                                db.child(teamName).setValue(userDetails);
                                db.child("RegisteredParticipants").child(userDetails.getb1()).setValue(teamName);
                                db.child("RegisteredParticipants").child(userDetails.getb2()).setValue(teamName);
                            } else {
                                Toast.makeText(getApplicationContext(), "TEAM ID EXISTS", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    userDetails.clearDataHolder();
                    startActivity(new Intent(this,MainActivity.class));
                    finish();
                }
                else if (userDetails.isr1()) {
                    db = FirebaseDatabase.getInstance().getReference("Mysterio");
                    db.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (!dataSnapshot.child(teamName).exists()) {
                                db.child(teamName).setValue(userDetails);
                                db.child("RegisteredParticipants").child(userDetails.getb1()).setValue(teamName);
                            } else {
                                Toast.makeText(getApplicationContext(), "TEAM ID EXISTS", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    userDetails.clearDataHolder();
                    startActivity(new Intent(this,MainActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(this, "PLEASE ADD PARTICIPANTS", Toast.LENGTH_LONG).show();
                }
            }
            else{
                Toast.makeText(this,"PLEASE ENTER THE TEAM NUMBER",Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e){
            Toast.makeText(this,"ENTER THE TEAM NUMBER!",Toast.LENGTH_LONG).show();
            Log.e("HERE","HOLY SHIT");
        }


    }
    public void fetchUserDetails(View view){
        Intent intent = new Intent(this,ParticipantDetails.class);
        startActivity(intent);
    }
}