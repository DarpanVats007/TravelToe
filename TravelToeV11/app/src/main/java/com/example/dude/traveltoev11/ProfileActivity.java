package com.example.dude.traveltoev11;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
//import android.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;



import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private ViewPager viewPager;

    private ViewPagerAdapter adapter;
    private TabLayout tabLayout;


    FirebaseAuth auth;
    FirebaseUser user;
    TextView profileTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_profile);

//        profileTxt = (TextView)findViewById (R.id.textView);

       Toolbar toolbar = (Toolbar) findViewById (R.id.toolBar);
        setSupportActionBar(toolbar);


        tabLayout = (TabLayout) findViewById (R.id.tabs);
        tabLayout.setupWithViewPager (viewPager);

        tabLayout.addTab (tabLayout.newTab ().setText ("connect"));
        tabLayout.addTab (tabLayout.newTab ().setText ("Tab2"));
        tabLayout.addTab (tabLayout.newTab ().setText ("Tab3"));
        tabLayout.setTabGravity (TabLayout.GRAVITY_FILL);

        //viewPager = findViewById (R.id.pager);
        viewPager = (ViewPager) findViewById (R.id.pager);

       // adapter = new ViewPagerAdapter (getSupportFragmentManager (),tabLayout.getTabCount ());
        ViewPagerAdapter adapter = new ViewPagerAdapter (getSupportFragmentManager (),tabLayout.getTabCount ());


        viewPager.setAdapter (adapter);

        viewPager.addOnPageChangeListener (new ViewPager.OnPageChangeListener () {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                tabLayout.setScrollPosition (position,0, true);
                tabLayout.setSelected (true);


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });






        tabLayout.addOnTabSelectedListener (new TabLayout.OnTabSelectedListener () {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem (tab.getPosition ());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                //viewPager.setCurrentItem (tab.getPosition ());

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });







//        auth = FirebaseAuth.getInstance ();
//
//        user = auth.getCurrentUser ();
//
//
//        profileTxt.setText (user.getEmail ());




    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(, menu);
//        return true;
//    }



    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_map, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.text_Map);
           // textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }




//
//        public void buttonCameraOpen (View v)
//    {
//
//        Intent i = new Intent (this, CameraActivity.class);
//        startActivity (i);
//    }
//
//
//    public void signOut(View v){
//        auth.signOut ();
//        finish ();
//        Intent i = new Intent (this,MainActivity.class);
//        startActivity (i);
//
//    }
}
