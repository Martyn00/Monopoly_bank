package com.example.nushfrate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int getMoney, loseMoney;
    int Money = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView theMoneyText = (TextView) findViewById(R.id.textView2);
        theMoneyText.setText(String.valueOf(Money));

        Button btnPierde = (Button) findViewById(R.id.Pierde);
        Button btnPrimeste = (Button) findViewById(R.id.Primeste);

        btnPierde.setOnClickListener(this);
        btnPrimeste.setOnClickListener(this);

        EditText getMoney = (EditText) findViewById(R.id.editTextgetMoney);
        EditText loseMoney = (EditText) findViewById(R.id.editTextloseMoney);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.Pierde:
                loseMoney = Integer.valueOf(loseMoney);
                Money = Money - loseMoney;
                Toast.makeText(this, "Ai pierdut o tona de bani!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.Primeste:
                getMoney = Integer.valueOf(getMoney);
                Money = Money + getMoney;
                Toast.makeText(this, "Ai primit o tona de bani!", Toast.LENGTH_SHORT).show();
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

}