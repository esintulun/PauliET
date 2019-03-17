package com.example.esintulun.pauli;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fragmentKlassen.Klasse5a;
import fragmentKlassen.Klasse5b;

public class KlassenActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener  {


    private ViewPager viewPager;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.hamburger);
        setContentView(R.layout.activity_klassen);

        //VIEWPAGER
        viewPager= (ViewPager) findViewById(R.id.viewpager);
        this.addPages();

        //TABLAYOUT
        tabLayout= (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(this);

    }



    private void addPages()
    {
        MyPagerAdapter pagerAdapter=new MyPagerAdapter(this.getSupportFragmentManager());


        pagerAdapter.addFragment(new Klasse5a());
        pagerAdapter.addFragment(new Klasse5b());
        pagerAdapter.addFragment(new Klasse5a());
        pagerAdapter.addFragment(new Klasse5b());
        pagerAdapter.addFragment(new Klasse5a());
        pagerAdapter.addFragment(new Klasse5b());


        //SET ADAPTER TO VP
        viewPager.setAdapter(pagerAdapter);
    }


    public void onTabSelected(TabLayout.Tab tab) {



        //viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
