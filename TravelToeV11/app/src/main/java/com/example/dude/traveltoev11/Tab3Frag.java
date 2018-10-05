package com.example.dude.traveltoev11;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.MapView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Tab3Frag extends Fragment {

    View mView;
    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;

    private ProgressBar mProgressCircle;

    private DatabaseReference mDatabaseRef;
    private List<ImageUpload> mUploads;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate (R.layout.frag_tab3, container, false);

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);

        //  setContentView(R.layout.activity_images);

        mRecyclerView = (RecyclerView) mView.findViewById (R.id.recycler_view);
        mUploads = new ArrayList<> ();

        //mButtonChooseImage = (Button) mView.findViewById (R.id.fileChose);
//        mRecyclerView = ()mView. findViewById<RecyclerView>(R.id.recycler_view).apply {
//            //         mRecyclerView = ()mView. findViewById<RecyclerView>(R.id.recycler_view).apply {


        mRecyclerView.setHasFixedSize (true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager (getActivity ());
        layoutManager.setOrientation (LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager (layoutManager);

        mProgressCircle = (ProgressBar) mView.findViewById(R.id.progress_circle);


        mDatabaseRef = FirebaseDatabase.getInstance ().getReference ("uploads");

        mDatabaseRef.addValueEventListener (new ValueEventListener () {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren ()) {
                    ImageUpload upload = postSnapshot.getValue (ImageUpload.class);
                    mUploads.addAll (mUploads);
                }

                mAdapter = new ImageAdapter (getActivity (), mUploads);

                mRecyclerView.setAdapter (mAdapter);
                mProgressCircle.setVisibility (View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText (getActivity (), databaseError.getMessage (), Toast.LENGTH_SHORT).show ();
                mProgressCircle.setVisibility (View.INVISIBLE);


            }
        });


    }


}
