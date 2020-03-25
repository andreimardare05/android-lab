package com.example.onlineshop;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    Coffee[] drinksArray = { new Coffee("Espresso", null, 7),new Coffee("Espresso Macchiatto", null, 10),
            new Coffee("Cappuccino", null, 10), new Coffee("Latte Machiatto", null, 15),
            new Coffee("Coconut White Hot Chocolate", null, 15)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            ArrayAdapter adapter = new ArrayAdapter<Coffee>(this,R.layout.activity_listview, drinksArray);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);

        if (savedInstanceState != null) {
            TextView description = (TextView) findViewById(R.id.description);
            String restoreText = savedInstanceState.getString("STATE_DESCRIPTION");
            description.setText(restoreText);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()  {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                long id) {
                    String item = ((TextView)view).getText().toString();

                    TextView description = (TextView) findViewById(R.id.description);

                    description.setText(drinksArray[position].selectionString());

                    saveTextToInternalStorage(drinksArray[position].selectionString());

                    readFromInternalStorage();

                }
            });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        TextView description = (TextView) findViewById(R.id.description);

        savedInstanceState.putString("STATE_DESCRIPTION", description.getText().toString());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_destination, menu);
        return true;
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        TextView description = (TextView) findViewById(R.id.description);

        description.setText(savedInstanceState.getString("STATE_DESCRIPTION"));
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
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
            newFragment.show(getSupportFragmentManager(), "subscribe");
        }
        else if(id == R.id.action_settings){
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveTextToInternalStorage(String text) {
        try {
            FileOutputStream fileOutputStream = openFileOutput("internal_storage.txt", Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);
            writer.append(text);
            writer.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFromInternalStorage() {
        try {
            FileInputStream fileInputStream = openFileInput("internal_storage.txt");
            InputStreamReader isr = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("On start lifecycle hook");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("On stop lifecycle hook");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("On resume lifecycle hook");
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("On pause lifecycle hook");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("On destroy lifecycle hook");
    }

}
