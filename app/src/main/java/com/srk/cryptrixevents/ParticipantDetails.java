package com.srk.cryptrixevents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ParticipantDetails extends AppCompatActivity {

    public DatabaseReference db;
    public String cryptrixId;
    public String barcodeValue;
    public String teamName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_details);
    }
    public void fetchUserDetails(View view){
        EditText et = findViewById(R.id.cryptrixId);
        cryptrixId = et.getText().toString();
        if(!cryptrixId.isEmpty()) {
            db = FirebaseDatabase.getInstance().getReference("BarcodeMap");
            db.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    try {
                        if (dataSnapshot.child(cryptrixId).exists()) {
                            for (DataSnapshot temp : dataSnapshot.getChildren()) {
                                if (temp.getKey().equals(cryptrixId)) {
                                    barcodeValue = temp.getValue().toString();
                                    Log.e("HERE", barcodeValue);
                                    db = FirebaseDatabase.getInstance().getReference("Mysterio").child("RegisteredParticipants");
                                    db.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            try {
                                                if (dataSnapshot.child(barcodeValue).exists()) {
                                                    for (DataSnapshot here : dataSnapshot.getChildren()) {
                                                        if (here.getKey().equals(barcodeValue)) {
                                                            teamName = here.getValue().toString();
                                                            Log.e("HERE", teamName);
                                                            Intent intent = new Intent(getApplicationContext(), userDetails.class);
                                                            intent.putExtra("TeamName", teamName);
                                                            startActivity(intent);
                                                            break;
                                                        }
                                                    }
                                                }
                                                else {
                                                    Toast.makeText(getApplicationContext(), "PARTICIPANT NOT REGISTERED FOR THIS EVENT!", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                            catch (Exception e){
                                                Toast.makeText(getApplicationContext(), "PARTICIPANT NOT REGISTERED FOR THIS EVENT!", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                    break;
                                }
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "ENTER A VALID CRYPTRIX ID", Toast.LENGTH_LONG).show();
                        }
                    }
                    catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "ENTER A VALID CRYPTRIX ID", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else{
            Toast.makeText(this, "ENTER CRYPTRIX ID", Toast.LENGTH_LONG).show();
        }

    }
}