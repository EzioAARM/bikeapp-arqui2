package com.arqui.baikaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    public static Double Circunferencia = 1.25; // Circunferencia de una rueda de bicicleta en metros

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar_navigation_principal_activity);
        toolbar.setTitle("Baikapp");
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.main_toolbar);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = new PrincipalActivity();
        transaction.replace(R.id.contenedor_principal_principal_activity, fragment);
        toolbar.setTitle("Estadisticas");
        transaction.addToBackStack(null).commit();

    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.settings:
                // show settings activity
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment fragment = new SettingsFragment();
                transaction.replace(R.id.contenedor_principal_principal_activity, fragment);
                transaction.addToBackStack(null).commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed()
    {
        if(getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        }
        else
            super.onBackPressed();
    }
}
