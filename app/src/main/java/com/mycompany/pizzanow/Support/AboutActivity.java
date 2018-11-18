package com.mycompany.pizzanow.Support;

import android.os.Bundle;

import com.mycompany.pizzanow.R;
import com.mycompany.pizzanow.ui.Toolbar.ToolbarActivity;

public class AboutActivity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}
