package com.example.dude.traveltoev11;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    int tabCount;


    public ViewPagerAdapter (FragmentManager fm, int tabCount) {

        super(fm);
        this.tabCount=tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                Tab1Frag tab1Frag=new Tab1Frag ();
                return tab1Frag;
            case 1:
                Tab2Frag tab2Frag=new Tab2Frag ();
                return tab2Frag;
            case 2:
                Tab3Frag tab3Frag=new Tab3Frag ();
                return tab3Frag;

                default:return null;


        }

//        MapFragment mapFragment = new MapFragment ();
//
//        position = position+1;
//        Bundle bundle = new Bundle ();
//        bundle.putString ("mesaage","Fragment :"+position);
//        mapFragment.setArguments (bundle);
//
//        return mapFragment;
    }


    //    @Override
//    public Fragment getItem(int position) {
//        DemoFragment demoFragment=new DemoFragment ();
//
//        return null;
//    }

    @Override
    public int getCount() {
        return tabCount;
    }
//
//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        position = position+1;
//        return "Fragment"+position;
//    }
}
