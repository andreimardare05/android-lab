package com.example.onlineshop;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName() + "_preferences" , Context.MODE_PRIVATE);

        boolean notifications = sharedPreferences.getBoolean("notifications",false);
        String feedback = sharedPreferences.getString("feedback","");
        String first_name = sharedPreferences.getString("first_name","");
        String last_name = sharedPreferences.getString("last_name","");

        // In order to test if the preferences were saved I used toast to display the new values on the lifecycle onCreate of settings activity

        System.out.println("Your last settings are: \n" +
                (notifications ? "Notifications are enabled" : "Notifications are disabled") + "\n"
                + feedback + "\n" + first_name + "\n" + last_name);

//        Toast.makeText(this, notifications ? "Notifications are enabled" : "Notifications are disabled", Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, first_name + "", Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, last_name + "", Toast.LENGTH_SHORT).show();

        getSupportFragmentManager().beginTransaction().replace(R.id.settings_frame, new SettingsFragment()).commit();
    }
}