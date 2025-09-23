package com.example.myapplication999;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import android.view.MenuItem;

public class login extends AppCompatActivity {

    EditText emailEditText, passwordEditText;
    Button signInButton;
    BottomNavigationView bottomNav;

    SharedPreferences sharedPreferences;
    public static final String PREF_NAME = "UserPrefs";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signInButton = findViewById(R.id.signInButton);
        bottomNav = findViewById(R.id.bottomNavigationLogin);

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        signInButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String pass = passwordEditText.getText().toString().trim();

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(login.this, "Enter email and password", Toast.LENGTH_SHORT).show();
                return;
            }

            String savedEmail = sharedPreferences.getString(KEY_EMAIL, null);
            String savedPass = sharedPreferences.getString(KEY_PASSWORD, null);

            if (savedEmail == null || savedPass == null) {
                // First time sign-in, save credentials
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_EMAIL, email);
                editor.putString(KEY_PASSWORD, pass);
                editor.apply();

                Toast.makeText(login.this, "Signed In", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(login.this, DashboardActivity.class));
                finish();
            } else {
                // Credentials exist, verify
                if (email.equals(savedEmail) && pass.equals(savedPass)) {
                    Toast.makeText(login.this, "Signed In", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(login.this, DashboardActivity.class));
                    finish();
                } else {
                    Toast.makeText(login.this, "Incorrect email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.menu_home) {
                    startActivity(new Intent(login.this, MainActivity.class));
                    return true;
                } else if (id == R.id.menu_dashboard) {
                    startActivity(new Intent(login.this, DashboardActivity.class));
                    return true;
                } else if (id == R.id.menu_login) {
                    Toast.makeText(login.this, "Already on Login", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
    }
}
