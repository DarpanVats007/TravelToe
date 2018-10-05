package com.example.dude.traveltoev11;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Tab1Frag extends Fragment implements OnMapReadyCallback {

    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;

//    public Tab1Frag(){
//
//
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate (savedInstanceState);
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView =inflater.inflate (R.layout.frag_tab1,container,false);
        return mView;
    }


    @Override
    public void onViewCreated(@NonNull View view,  Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);

        mMapView = (MapView) mView.findViewById (R.id.travelMap);
        if (mMapView !=null){

            mMapView.onCreate (null);
            mMapView.onResume ();
            mMapView.getMapAsync (this);

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        MapsInitializer.initialize (getContext ());
        mGoogleMap = googleMap;
        googleMap.setMapType (GoogleMap.MAP_TYPE_NORMAL);
        googleMap.addMarker (new MarkerOptions ().position (new LatLng (40.689247,-74.044502)).title ("status").snippet ("abc"));
        CameraPosition Liberty = CameraPosition.builder ().target(new LatLng (40.689247,-74.044502)).zoom (16).bearing (0).tilt (45).build ();

        googleMap.moveCamera (CameraUpdateFactory.newCameraPosition (Liberty));

    }
}
