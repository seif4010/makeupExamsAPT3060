package com.example.makeupexams;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ViewActivity extends AppCompatActivity {

    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        DB = new DBHelper(this);
        Button detail = findViewById(R.id.details);

        //viewing the data
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
          public void onClick(View view) {

                Cursor res = DB.getData();
                if (res.getCount() == 0) {
                    Toast.makeText(ViewActivity.this, "No entry exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("First Name : " + res.getString(0) + "\n");
                    buffer.append("Last Name : " + res.getString(1) + "\n");
                    buffer.append("username : " + res.getString(2) + "\n");
                    buffer.append("password : " + res.getString(3) + "\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(ViewActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Details");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}