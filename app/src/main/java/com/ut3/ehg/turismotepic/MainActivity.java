package com.ut3.ehg.turismotepic;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String fragmentTemp="com.ut3.ehg.turismotepic.HomeActivity";
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        FragmentTransaction tx = this.getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.lframe, Fragment.instantiate(this, fragmentTemp));
        tx.commit();
        drawer.closeDrawer(GravityCompat.START);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /*@Override
    public void onNavigationDrawerItemSelected(int posicion){

    }*/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /*if (id == R.id.nav_inicio) {
            // Handle the camera action
        } else */ //paso hacia abajo
        String fragmentTemp="";
        if (id == R.id.nav_realidad_aumentada) {
            Intent intent = new Intent(this, Augmentedreality.class);
            startActivity(intent);
        } else if (id == R.id.nav_ruta) {
            fragmentTemp="com.ut3.ehg.turismotepic.PoiMenu";
            changeFragment(fragmentTemp);
        } else if (id == R.id.nav_encuesta) {
            fragmentTemp="com.ut3.ehg.turismotepic.EncuestaActivity";
            changeFragment(fragmentTemp);
        } else if (id == R.id.nav_logout) {
            finish();
        }


        return true;
    }

    public void changeFragment(String fragmentTemp){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        FragmentTransaction tx = this.getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.lframe, Fragment.instantiate(this, fragmentTemp)).addToBackStack("tag");
        tx.commit();
        drawer.closeDrawer(GravityCompat.START);
    }

}
