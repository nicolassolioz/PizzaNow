package com.mycompany.pizzanow.Toolbar;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mycompany.pizzanow.Helper.LanguageHelper;
import com.mycompany.pizzanow.Helper.LocaleHelper;
import com.mycompany.pizzanow.R;
import com.mycompany.pizzanow.Support.AboutActivity;

import java.util.Locale;

public class ToolbarActivity extends AppCompatActivity {

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.help:
                Toast.makeText(getApplicationContext(), "help", Toast.LENGTH_SHORT).show();
                break;

            case R.id.idAbout:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;

            case R.id.en:
                LocaleHelper.setLocale(this, "en");
                //It is required to recreate the activity to reflect the change in UI.
                // recreate();
                break;

            case R.id.fr:
                LocaleHelper.setLocale(this, "fr");

                //It is required to recreate the activity to reflect the change in UI.
                recreate();

                break;
                default:
        }

        return super.onOptionsItemSelected(item);

    }
}
