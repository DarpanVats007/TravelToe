package com.example.dude.traveltoev11;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment {

    private TextView textView;


    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate (R.layout.fragment_map, container, false);
        textView = view.findViewById (R.id.map_display);
        textView.setText (getArguments ().getString ("mesaage"));
        return view;
    }

}
