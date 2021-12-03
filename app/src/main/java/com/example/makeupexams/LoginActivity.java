package com.example.makeupexams;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText fname;
    EditText lname;
    EditText username1;
    EditText password1;
    Button submit, details;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username1 = findViewById(R.id.username1);
        password1 = findViewById(R.id.password1);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        submit = findViewById(R.id.submit);
        details = findViewById(R.id.details);
        DB = new DBHelper(this);

        //login button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname = fname.getText().toString();
                String lastname = lname.getText().toString();
                String username = username1.getText().toString();
                String password = password1.getText().toString();

                Boolean submit = DB.submitData( firstname,  lastname,  username,  password);
                if (submit == true) {
                    Toast.makeText(LoginActivity.this, "New entry updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ViewActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(LoginActivity.this, "New was not entry updated", Toast.LENGTH_SHORT).show();
            }
        });

        //Veiw the details
//        details.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Cursor res = DB.getData();
//                if (res.getCount() == 0) {
//                    Toast.makeText(LoginActivity.this, "No entry exists", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                StringBuffer buffer = new StringBuffer();
//                while (res.moveToNext()) {
//                    buffer.append("First Name : " + res.getString(0) + "\n");
//                    buffer.append("Last Name : " + res.getString(1) + "\n");
//                    buffer.append("username : " + res.getString(2) + "\n");
//                    buffer.append("password : " + res.getString(3) + "\n");
//                }
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                builder.setCancelable(true);
//                builder.setTitle("User Details");
//                builder.setMessage(buffer.toString());
//                builder.show();
//            }
//        });
    }
}