package com.srk.cryptrixevents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

import android.content.Intent;
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
    public String barcodeValue,userName,phoneNumber;;
    ZXingScannerView camScanner;
    public pojo_userDetails userDetails;
    public pojo_ObjectHolder objectHolder;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        camScanner = new ZXingScannerView(this);
        setContentView(camScanner);

    }
    @Override
    public void handleResult(Result rawResult) {
        barcodeValue=rawResult.getText();
        DatabaseReference dbObj = FirebaseDatabase.getInstance().getReference("RegisteredUsers");
        dbObj.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(barcodeValue).exists()) {
                    try {
                        pojo_ObjectHolder temp = new pojo_ObjectHolder();
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            temp = child.getValue(pojo_ObjectHolder.class);
                            if (temp.barCodeValue.equals(barcodeValue)) {
                                break;
                            }
                        }
                        if (temp.barCodeValue.equals(barcodeValue)) {
                            if(userDetails == null){
                                userDetails = pojo_userDetails.getInstanceOf();
                                userDetails.setParticipantOneUniqueId(temp.uniqueId);
                                userDetails.setParticipantOne(temp.userName);
                                userDetails.setPhoneNumberOne(temp.phoneNumber);
                                MainActivity.name1.setText(userDetails.getParticipantOne());
                                MainActivity.phone1.setText(userDetails.getPhoneNumberOne());
                            }
                            else{
                                userDetails.setParticipantTwoUniqueId(temp.uniqueId);
                                userDetails.setParticipantTwo(temp.userName);
                                userDetails.setPhoneNumberTwo(temp.phoneNumber);
                                MainActivity.name2.setText(userDetails.getParticipantTwo());
                                MainActivity.phone2.setText(userDetails.getPhoneNumberTwo());
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"USER NOT REGISTERED",Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        Log.e("HERE", e.getMessage());
                    }

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
