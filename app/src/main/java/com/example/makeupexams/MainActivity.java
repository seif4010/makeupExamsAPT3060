package com.example.makeupexams;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button login, register;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
         DB = new DBHelper(this);

        //button action listener login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user == "" || pass == "") {
                    Toast.makeText(MainActivity.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                } else {
                  Boolean exist =  DB.checkUserName(user);
                  if (exist == false) {
                      Boolean insert = DB.insertData(user, pass);
                      if (insert == true) {
                          Toast.makeText(MainActivity.this, "login success", Toast.LENGTH_SHORT).show();
                          Intent intent = new Intent(getApplicationContext(), ViewActivity.class);
                          startActivity(intent);
                      } else {
                          Toast.makeText(MainActivity.this, "Failed to login", Toast.LENGTH_SHORT).show();
                      }
                  }
                };
            }
        });

        //button action listener register
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user =  username.getText().toString();
                String pass = password.getText().toString();

                if (user == "" || pass == ""){
                    Toast.makeText(MainActivity.this, "Fill in all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkuser = DB.checkUserName(user);
                    if (checkuser == false) {
                        Boolean insert = DB.insertData(user, pass);
                        if (insert == true) {
                            Toast.makeText(MainActivity.this, "Redirecting you to a register form", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "User Already Exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}