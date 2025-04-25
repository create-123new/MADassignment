package com.example.somya_sanjana;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

public class MainActivity extends Activity {

    // Labels and icons for each tile
    String[] labels = {"Call", "Camera", "Email", "Browser", "SMS", "Maps"};
    int[] icons = {
            android.R.drawable.ic_menu_call,
            android.R.drawable.ic_menu_camera,
            android.R.drawable.ic_dialog_email,
            android.R.drawable.ic_menu_compass,
            android.R.drawable.ic_dialog_dialer,
            android.R.drawable.ic_dialog_map
    };
    int[] colors = {
            Color.parseColor("#4CAF50"),  // Green for Call
            Color.parseColor("#2196F3"),  // Blue for Camera
            Color.parseColor("#F44336"),  // Red for Email
            Color.parseColor("#FF9800"),  // Orange for Browser
            Color.parseColor("#9C27B0"),  // Purple for SMS
            Color.parseColor("#009688")   // Teal for Maps
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(new GridAdapter());

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: // Call
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:1234567890"));
                        startActivity(callIntent);
                        break;

                    case 1: // Camera
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivity(cameraIntent);
                        break;

                    case 2: // Email
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                        emailIntent.setData(Uri.parse("mailto:someone@example.com"));
                        startActivity(emailIntent);
                        break;

                    case 3: // Browser
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
                        startActivity(browserIntent);
                        break;

                    case 4: // SMS
                        Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
                        smsIntent.setData(Uri.parse("smsto:1234567890"));
                        smsIntent.putExtra("sms_body", "Hello from Grid Launcher!");
                        startActivity(smsIntent);
                        break;

                    case 5: // Maps
                        Intent mapsIntent = new Intent(Intent.ACTION_VIEW);
                        mapsIntent.setData(Uri.parse("geo:0,0?q=Eiffel+Tower,Paris"));
                        startActivity(mapsIntent);
                        break;
                }
            }
        });
    }

    // Adapter for GridView
    class GridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return labels.length;
        }

        @Override
        public Object getItem(int position) {
            return labels[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View gridItem;

            if (convertView == null) {
                gridItem = LayoutInflater.from(getApplicationContext()).inflate(R.layout.grid_item, parent, false);
            } else {
                gridItem = convertView;
            }

            ImageView icon = gridItem.findViewById(R.id.icon);
            TextView label = gridItem.findViewById(R.id.label);

            Drawable iconDrawable = ContextCompat.getDrawable(MainActivity.this, icons[position]);
            if (iconDrawable != null) {
                iconDrawable.setTint(colors[position]);  // 💡 Use position to select correct color
                icon.setImageDrawable(iconDrawable);
            }

           // icon.setImageResource(icons[position]);
            label.setText(labels[position]);

            return gridItem;
        }
    }
}

