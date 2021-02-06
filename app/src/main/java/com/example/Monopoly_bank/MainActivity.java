package com.example.Monopoly_bank;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements
        Login.LoginListener, HomeActivity.HomeActivityListener, Pay.Paylistener, Scan.Scanlistener,
        Connectivity.ConnectivityListener {

    UserViewModel uvm;
    User Mainuser;

    public final Money buget = new Money(1500);
    private HomeActivity homeActivity;
    private History history;
    private Pay pay;
    private Scan scan;
    private Connectivity connectivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uvm = new UserViewModel(getApplication());
        uvm.getmUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                Mainuser = user;
            }
        });
        homeActivity = new HomeActivity();
        history = new History();
        pay = new Pay(getApplication());
        scan = new Scan();
        connectivity = new Connectivity(getApplication());

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);
        if (firstStart) {
            showLoginDialog(1);
            buget.setSum(1500);
        }

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navi);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,  homeActivity).commit();
    }
    public void showLoginDialog(int x) {
        Login login = new Login(x);
        login.setCancelable(false);
        login.show(getSupportFragmentManager(), "login");
        SharedPreferences prefs = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    @Override
    public void applyText(String username) {
        if (username.equals("")){
            Toast.makeText(this, "One character minimum", Toast.LENGTH_SHORT).show();
        }
        else {
            buget.setUser(username);
            User usr = new User(username, buget.getSum());
            uvm.insert(usr);
            Toast.makeText(this, "Salut, " + username, Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void applyText1(String username) {
        if (username.equals("")){
            Toast.makeText(this, "One character minimum", Toast.LENGTH_SHORT).show();
        }
        else
        {
            buget.setUser(username);
            User usr = new User(username, buget.getSum());
            // BA FACE YAAA
            uvm.insert(usr);
            Toast.makeText(this, "Salut, " + username, Toast.LENGTH_SHORT).show();
            connectivity.userText.setText(username);
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @SuppressLint("NonConstantResourceId")
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.nav_home:
                            homeActivity.updateBani(buget);
                            selectedFragment = homeActivity;
                            break;
                        case R.id.nav_history:
                            selectedFragment = history;
                            break;
                        case R.id.nav_pay:
                            pay.updateBani(buget);
                            selectedFragment = pay;
                            break;
                        case R.id.nav_scan:
                            scan.updateBani(buget);
                            selectedFragment = scan;
                            break;
                        case R.id.nav_connectivity:
                            connectivity.updateBani(buget);
                            selectedFragment = connectivity;
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };
    @Override
    public void onInputHomeSent(Money input){
        buget.setSum(input.getSum());
    }

    @Override
    public void onInputScanSent(Money input) {
        buget.setSum(input.getSum());
    }

    @Override
    public void onInputPaySent(Money input) {
        buget.setSum(input.getSum());
    }

    @Override
    public void onInputConnectivitySent(Money input) {
        buget.setUser(input.getUser());
        buget.setSum(input.getSum());
    }

    private void savePreferences() {
        SharedPreferences settings = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        String UnameValue = buget.getUser();
        editor.putString("user", UnameValue);
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    private void loadPreferences() {
        SharedPreferences settings = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        buget.setUser(settings.getString("user", "Unknown"));
    }


    @Override
    public void onPause() {
        super.onPause();
        savePreferences();

    }
    @Override
    public void onResume() {
        super.onResume();
        loadPreferences();
    }

}