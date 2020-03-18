package com.example.onlineshop;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView textView = findViewById(R.id.info);
        textView.setText(R.string.activity_about_text);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_destination, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_about){
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.action_share){
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Download CafeShop from the Google Store. Enjoy!");
            sendIntent.setType("text/plain");

            if (sendIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(sendIntent);
            }
        }
        else if(id == R.id.action_subscribe){
            DialogFragment newFragment = new SubscribeDialogFragment();
            newFragment.show(getSupportFragmentManager(), "SUBSCRIBE");
        }
        return super.onOptionsItemSelected(item);
    }

}
