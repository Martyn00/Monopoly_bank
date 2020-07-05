package com.example.nushfrate;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Money bani = new Money(1500);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView theMoneyText = (TextView) findViewById(R.id.textView2);
        theMoneyText.setText(String.valueOf(bani.getSum()));

        Button btnPierde = (Button) findViewById(R.id.Pierde);
        Button btnPrimeste = (Button) findViewById(R.id.Primeste);
        Button btnQuickAdd = (Button) findViewById(R.id.Quickadd);

        btnPierde.setOnClickListener(this);
        btnPrimeste.setOnClickListener(this);
        btnQuickAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        TextView theMoneyText = (TextView) findViewById(R.id.textView2);
        String number, prop;
        switch (view.getId()){
            case R.id.Pierde:

                EditText loseMoney = (EditText) findViewById(R.id.editTextloseMoney);
                number = loseMoney.getText().toString();
                if(!number.isEmpty()) {
                    bani.scadeBani(Long.parseLong(number));
                    theMoneyText.setText(String.valueOf(bani.getSum()));
                    prop = "Ai pierdut " + number + " bani";
                    Toast.makeText(this, prop, Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.Primeste:
                EditText getMoney = (EditText) findViewById(R.id.editTextgetMoney);
                number = getMoney.getText().toString();
                if(!number.isEmpty()) {
                    bani.cresteBani(Long.parseLong(number));
                    theMoneyText.setText(String.valueOf(bani.getSum()));
                    prop = "Ai castigat " + number + " bani";
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.cha_ching);
                    mediaPlayer.start();
                    Toast.makeText(this, prop, Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.Quickadd:
                bani.cresteBani(200);
                theMoneyText.setText(String.valueOf(bani.getSum()));
                prop = "Saracule, ai trecut de start, poftim 200";
                MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.cha_ching);
                mediaPlayer.start();
                Toast.makeText(this, prop, Toast.LENGTH_SHORT).show();

             break;


            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

}
