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
    public String barcodeValue;
    ZXingScannerView camScanner;
    public pojo_userDetails userDetails;
    static int count = 0;

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
        Log.e("HERE",barcodeValue);
        DatabaseReference dbObj = FirebaseDatabase.getInstance().getReference("RegisteredUsers");
        dbObj.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(barcodeValue).exists()) {
                    try {
                        Log.e("HERE","VANTENDA VENNA");
                        pojo_ObjectHolder temp = new pojo_ObjectHolder();
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            temp = child.getValue(pojo_ObjectHolder.class);
                            if (temp.barCodeValue.equals(barcodeValue)) {
                                break;
                            }
                        }
                        if (temp.barCodeValue.equals(barcodeValue)) {
                            Log.e("HERE","KEDACHUDUTHU");
                            if(count == 0){
                                Log.e("HERE",temp.phoneNumber);
                                Log.e("HERE",temp.uniqueId);
                                Log.e("HERE",temp.userName);
                                userDetails = pojo_userDetails.getInstanceOf();
                                userDetails.setParticipantOneUniqueId(temp.uniqueId);
                                userDetails.setParticipantOne(temp.userName);
                                userDetails.setPhoneNumberOne(temp.phoneNumber);
                                MainActivity.name1.setText(userDetails.getParticipantOne());
                                MainActivity.phone1.setText(userDetails.getPhoneNumberOne());
                                count=1;
                            }
                            else{
                                userDetails = pojo_userDetails.getInstanceOf();
                                Log.e("HERE1",temp.phoneNumber);
                                Log.e("HERE1",temp.uniqueId);
                                Log.e("HERE1",temp.userName);
                                userDetails.setParticipantTwoUniqueId(temp.uniqueId);
                                userDetails.setParticipantTwo(temp.userName);
                                userDetails.setPhoneNumberTwo(temp.phoneNumber);
                                MainActivity.name2.setText(userDetails.getParticipantTwo());
                                MainActivity.phone2.setText(userDetails.getPhoneNumberTwo());
                            }
                        }
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
        finish();
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
