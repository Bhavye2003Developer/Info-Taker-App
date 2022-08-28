package com.example.infotaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText name = findViewById(R.id.editTextTextPersonName);
        EditText email = findViewById(R.id.editTextTextEmailAddress);
        EditText number = findViewById(R.id.editTextNumber);
        Button submit = findViewById(R.id.button);
        Button reset = findViewById(R.id.button2);

        submit.setBackgroundColor(Color.parseColor("#C6951B"));
        reset.setBackgroundColor(Color.parseColor("#C6951B"));

        String[] email_arr = new String[]{email.getText().toString()};

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = CombineInfo(name.getText().toString(), email.getText().toString(), number.getText().toString());
                String[] email_arr = new String[]{email.getText().toString()};
                composeEmail(email_arr,"Your Personal Information",message);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText("");
                email.setText("");
                number.setText("");
                Toast.makeText(MainActivity.this, "Your Info has been reset", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String CombineInfo(String name, String email, String number){
        String message = "";
        message = message + "Your Name : " + name + "\n";
        message = message + "Your Email address : " + email + "\n";
        message = message + "Your Phone NUmber : " + number + "\n";
        message = message + "\n\n\nThank You For using my App...";

        return message;
    }

    public void composeEmail(String[] addresses, String subject, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT,message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}