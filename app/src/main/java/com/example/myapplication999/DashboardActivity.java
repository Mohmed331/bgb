package com.example.myapplication999;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import android.view.MenuItem;

public class DashboardActivity extends AppCompatActivity {

    Button btnExit;
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        btnExit = findViewById(R.id.btnExit);
        bottomNav = findViewById(R.id.bottomNavigationDashboard);

        btnExit.setOnClickListener(v -> {
            finishAffinity();
            System.exit(0);
        });

        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.menu_home) {
                    Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                    return true;
                } else if (id == R.id.menu_dashboard) {
                    Toast.makeText(DashboardActivity.this, "Already on Dashboard", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id == R.id.menu_exit) {
                    finishAffinity();
                    System.exit(0);
                    return true;
                }
                return false;
            }
        });
    }
}
