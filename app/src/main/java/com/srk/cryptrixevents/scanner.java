package com.srk.cryptrixevents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;

public class scanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    public String barcodeValue;
    ZXingScannerView camScanner;
    public pojo_userDetails userDetails;
    public pojo_ObjectHolder temp;
    public DatabaseReference obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        camScanner = new ZXingScannerView(this);
        setContentView(camScanner);
    }

    @Override
    public void handleResult(Result rawResult) {
        barcodeValue = rawResult.getText();
        Log.e("HERE",barcodeValue);
        DatabaseReference dbObj = FirebaseDatabase.getInstance().getReference("RegisteredUsers");
        dbObj.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(barcodeValue).exists()) {
                    try {
                        temp = new pojo_ObjectHolder();
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            temp = child.getValue(pojo_ObjectHolder.class);
                            if (temp.b.equals(barcodeValue)) {
                                break;
                            }
                        }
                        Log.e("HERE",barcodeValue);
                        obj = FirebaseDatabase.getInstance().getReference("Mysterio").child("RegisteredParticipants");
                        obj.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.child(barcodeValue).exists()){
                                    try {
                                        Toast.makeText(getApplicationContext(),"PARTICIPANT ALREADY REGISTERED",Toast.LENGTH_LONG).show();
                                        onBackPressed();
                                    }
                                    catch (Exception e) {
                                        Log.e("HERE", e.getMessage());
                                    }
                                }
                                else{
                                    userDetails = pojo_userDetails.getInstanceOf();
                                    //userDetails.clearDataHolder();
                                    if (!userDetails.isr1()) {
                                        Log.e("HERE", temp.u);
                                        userDetails.setp1(temp.id);
                                        userDetails.setn1(temp.u);
                                        userDetails.setpn1(temp.p);
                                        userDetails.setc1(temp.e);
                                        userDetails.setr1(true);
                                        userDetails.setb1(temp.b);
                                        MainActivity.name1.setText(userDetails.getn1());
                                        MainActivity.phone1.setText(userDetails.getpn1());
                                        MainActivity.clg1.setText(userDetails.getc1());
                                    }
                                    else{
                                        Log.e("HERE1", temp.u);
                                        userDetails.setr2(true);
                                        userDetails.setp2(temp.id);
                                        userDetails.setn2(temp.u);
                                        userDetails.setpn2(temp.p);
                                        userDetails.setc2(temp.e);
                                        userDetails.setb2(temp.b);
                                        MainActivity.name2.setText(userDetails.getn2());
                                        MainActivity.phone2.setText(userDetails.getpn2());
                                        MainActivity.clg2.setText(userDetails.getc2());
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    } catch (Exception e) {
                        Log.e("HEREZOO", e.getMessage());
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"USER NOT REGISTERED",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        camScanner.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        camScanner.setResultHandler(this);
        camScanner.startCamera();
    }
}