package com.example.Monopoly_bank;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RandomPlayers extends AppCompatActivity implements View.OnClickListener {
    Button Back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.randomplayers);
        Back = findViewById(R.id.Back);
        Back.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Back:
                finish();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

}
