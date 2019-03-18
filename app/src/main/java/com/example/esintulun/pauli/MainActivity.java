package com.example.esintulun.pauli;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.design.widget.NavigationView;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.hamburger);
        setContentView(R.layout.activity_main);


        goDrawer();
    }

    private void goDrawer() {

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {

                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );

        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        switch (menuItem.getItemId()) {
                            case android.R.id.home:
                                mDrawerLayout.openDrawer(GravityCompat.START);
                                return true;
                            case R.id.nav_start:
                                Intent vergehenActivity = new Intent(MainActivity.this, VergehenActivity.class);
                                vergehenActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(vergehenActivity);
                                return true;

                            case R.id.nav_klassen:
                                Intent klassenActivity = new Intent(MainActivity.this, KlassenActivity.class);
                                klassenActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(klassenActivity);
                                return true;

                            case R.id.nav_schuelerVorfaelle:
                                Intent schuelerVorfaelleTable = new Intent(MainActivity.this, VorfaelleTableActivity.class);
                                schuelerVorfaelleTable.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(schuelerVorfaelleTable);
                                return true;

                            case R.id.nav_camera:
                                // Version 4 ok
                                //Intent intentCamera = new Intent(MainActivity.this, CameraTest.class);
                                //Intent intentCamera = new Intent(MainActivity.this, Cameratest2Activity.class);
                                Intent intentCamera = new Intent(MainActivity.this, Kamera.class);

                                //Intent intentCamera = new Intent(MainActivity.this, Camera2.class);

                                //Intent intentCamera = new Intent(MainActivity.this, CameraActivity.class);
                                intentCamera.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intentCamera);
                                return true;


                            case R.id.nav_statistics:
                                //Intent intentCamera = new Intent(MainActivity.this, CameraTest.class);
                                Intent statistics = new Intent(MainActivity.this, Statistics.class);
                                startActivity(statistics);
                                return true;

                            case R.id.nav_mailsend:
                                //Intent intentCamera = new Intent(MainActivity.this, CameraTest.class);
                                //Intent mailsend = new Intent(MainActivity.this, SendMailActivity.class);
                                Intent mailsend = new Intent(MainActivity.this, EmailSendActivity.class);

                                startActivity(mailsend);
                                return true;
                        }

                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("item", "" + item.getItemId());

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_logout, menu);
        return true;
    }


}
