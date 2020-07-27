package com.example.nushfrate;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;

import com.journeyapps.barcodescanner.CaptureActivity;

public class CaptureActivityPortrait extends CaptureActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        
    }
    Intent intent = getIntent();

}
