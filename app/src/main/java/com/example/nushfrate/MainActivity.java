package com.example.nushfrate;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.nio.charset.MalformedInputException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Money bani = new Money(1500);
    int code = 0;
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
                    Intent intent = new Intent(this, Pop.class);
                    startActivityForResult(intent, code);
                break;

            case R.id.Primeste:
                EditText getMoney = (EditText) findViewById(R.id.editTextvalueMoney);
                number = getMoney.getText().toString();
                if(!number.isEmpty()) {
                    bani.cresteBani(Long.parseLong(number));
                    prop = "Ai castigat " + number + " bani";
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.cha_ching);
                    mediaPlayer.start();
                    startActivity(new Intent(MainActivity.this,Pop.class));
                    Toast.makeText(this, prop, Toast.LENGTH_SHORT).show();
                    theMoneyText.setText(String.valueOf(bani.getSum()));
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==  code){
            if(resultCode == RESULT_OK){
                String return_text = data.getStringExtra(Intent.EXTRA_TEXT);
                if(!return_text.isEmpty()){
                    bani.scadeBani(Long.parseLong(return_text));
                    TextView theMoneyText = (TextView) findViewById(R.id.textView2);
                    theMoneyText.setText(String.valueOf(bani.getSum()));
                }
            }
        }
    }
}
