package com.example.ehi.calculatorsarefun.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ehi.calculatorsarefun.R;
import com.example.ehi.calculatorsarefun.fragments.DisplayFragment;


public class CalculatorActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
    }

    public void onKeySelected (String key) {
        DisplayFragment displayFragment =  (DisplayFragment) getFragmentManager().findFragmentById(R.id.fragment_display);
        if (displayFragment != null) {
            displayFragment.updateDisplay(key);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
