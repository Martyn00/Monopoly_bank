package com.example.nushfrate;

import android.content.SharedPreferences;
import android.media.MediaSession2Service;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements Login.LoginListener, HomeActivity.HomeActivityListener {

    public final Money buget = new Money(1500);;
    private HomeActivity homeActivity;
    private History history;
    private Pay pay;
    private Scan scan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeActivity = new HomeActivity();
        history = new History();
        pay = new Pay();
        scan = new Scan();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firsStart = prefs.getBoolean("firsStart", true);

        if (firsStart) {
            showLoginDialog();
            buget.setSum(1500);
        }

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navi);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeActivity()).commit();
    }
    private void showLoginDialog() {
        Login login = new Login();
        login.show(getSupportFragmentManager(), "login");
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    @Override
    public void applyText(String username) {
        Toast.makeText(this, "Salut, " + username, Toast.LENGTH_SHORT).show();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
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
                            selectedFragment = pay;
                            break;
                        case R.id.nav_scan:
                            selectedFragment = scan;
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();


                    return true;
                }
            };
    public void onInputHomeSent(Money input){
        buget.setSum(input.getSum());
    }
}


