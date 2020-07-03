package com.example.nushfrate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int Money = 1500;
        TextView theMoneyText = (TextView) findViewById(R.id.textView2);
        theMoneyText.setText(String.valueOf(Money));

        Button btnPierde = (Button) findViewById(R.id.Pierde);
        Button btnPrimeste = (Button) findViewById(R.id.Primeste);

        btnPierde.setOnClickListener(this);
        btnPrimeste.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.Pierde:
                Toast.makeText(this, "Ai pierdut o tona de bani!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.Primeste:
                Toast.makeText(this, "Ai primit o tona de bani!", Toast.LENGTH_SHORT).show();
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

}