package com.example.somya_sanjana;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnOpenWebsite, btnSendEmail, btnCall, btnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpenWebsite = findViewById(R.id.btnOpenWebsite);
        btnSendEmail = findViewById(R.id.btnSendEmail);
        btnCall = findViewById(R.id.btnCall);
        btnMap = findViewById(R.id.btnMap);

        // Open Website
        btnOpenWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage = Uri.parse("https://www.who.int");
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });

        // Send Email
        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:example@email.com"));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Health Inquiry");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello Doctor, I have a question...");
                startActivity(emailIntent);
            }
        });

        // Make Call
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:1234567890"));
                startActivity(callIntent);
            }
        });

        // Open Map
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=hospital");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }
}
