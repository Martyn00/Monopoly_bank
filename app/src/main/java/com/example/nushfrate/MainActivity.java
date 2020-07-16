package com.example.nushfrate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.nio.charset.MalformedInputException;

import static java.lang.Long.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Login.LoginListener {

    Money bani = new Money(1500);
    private int code = 0;
    private int code1 = 1;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE );
        boolean firsStart = prefs.getBoolean("firsStart", true);

        if (firsStart) {
          showLoginDialog();
        }

        TextView theMoneyText = (TextView) findViewById(R.id.textView2);
        theMoneyText.setText(String.valueOf(bani.getSum()));


        ImageButton btnPierde = (ImageButton) findViewById(R.id.Pierde);
        ImageButton btnPrimeste = (ImageButton) findViewById(R.id.Primeste);
        ImageButton btnQuickAdd = (ImageButton) findViewById(R.id.Quickadd);

        btnPierde.setOnClickListener(this);
        btnPrimeste.setOnClickListener(this);
        btnQuickAdd.setOnClickListener(this);


    }

    private void showLoginDialog(){
       Login login = new Login();
       login.show(getSupportFragmentManager(), "login");

       SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
       SharedPreferences.Editor editor = prefs.edit();
       editor.putBoolean("firstStart", false);
       editor.apply();
    }

    @Override
    public void onClick(View view) {
        TextView theMoneyText = (TextView) findViewById(R.id.textView2);
        String number, prop;
        switch (view.getId()){
            case R.id.Pierde:
                MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.crowd_boo);
                mediaPlayer.start();
                Intent intent = new Intent(this, Pop.class);
                startActivityForResult(intent, code);
                break;

            case R.id.Primeste:
                    MediaPlayer mediaPlayerL = MediaPlayer.create(this, R.raw.cha_ching);
                    mediaPlayerL.start();
                    Intent intentW = new Intent(this, Pop2.class);
                    startActivityForResult(intentW, code1);


                break;

            case R.id.Quickadd:
                bani.cresteBani(200);
                theMoneyText.setText(String.valueOf(bani.getSum()));
                prop = "Saracule, ai trecut de start, poftim 200";
                MediaPlayer mediaPlayer200 = MediaPlayer.create(this, R.raw.cha_ching);
                mediaPlayer200.start();
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
                    bani.scadeBani(parseLong(return_text));
                    TextView theMoneyText = (TextView) findViewById(R.id.textView2);
                    theMoneyText.setText(String.valueOf(bani.getSum()));
                    if(bani.getSum() == 0){
                        String prop = "Ai pierdut BOSS=(((";
                        Toast.makeText(this, prop, Toast.LENGTH_SHORT).show();}


                    }
                }
            }

        if(requestCode == code1){
            if(resultCode == RESULT_OK){
                String return_text = data.getStringExtra(Intent.EXTRA_TEXT);
                if(!return_text.isEmpty()){
                    bani.cresteBani(Long.parseLong(return_text));
                    TextView theMoneyText = (TextView) findViewById(R.id.textView2);
                    theMoneyText.setText(String.valueOf(bani.getSum()));
                }
            }
        }
    }

    @Override
    public void applyText(String username) {
        Toast.makeText(this,"Salut, " +  username, Toast.LENGTH_SHORT).show();
    }
}

