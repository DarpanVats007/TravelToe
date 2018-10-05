package com.example.dude.traveltoev11;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.net.URI;

public class CameraActivity extends AppCompatActivity {

    public  static final int CAMERA_REQUEST= 999;
    private Button mUploadBtn;
    private ImageView mImageView;

   // private static final int GALLERY_INTENT = 2;



//    private StorageReference mStorage;
//
//    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_camera);
//        mStorage = FirebaseStorage.getInstance ().getReference ();
      mUploadBtn = (Button) findViewById (R.id.uploadBtn2);
        mImageView = (ImageView) findViewById (R.id.imageViewMain);



     //
//        mProgress = new ProgressDialog(this);

//        mUploadBtn.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
//
//                startActivityForResult (intent,CAMERA_REQUEST_CODE);
//
//
//
//            }
//        });


        mUploadBtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult (intent,CAMERA_REQUEST);



            }
        });

    }


//    protected void OpenCamera(View view){
//
//
//        Intent intent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult (intent,CAMERA_REQUEST);
//
//
//    }


    //............Image to screen.......///


    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data){

        super.onActivityResult (requestCode,resultCode, data);

        if(requestCode==CAMERA_REQUEST){



            Bitmap bitmap = (Bitmap)data.getExtras ().get ("data");

            mImageView.setImageBitmap (bitmap);


            Toast.makeText (CameraActivity.this,"Uploading Finished", Toast.LENGTH_LONG).show ();


            }




    }

    //............Image to screen.......///





    //............Upload Image to Firebase.......///


//    @Override
//    protected void onActivityResult (int requestCode, int resultCode, Intent data){
//
//        super.onActivityResult (requestCode,resultCode, data);
//
//        if(requestCode==CAMERA_REQUEST_CODE && resultCode == RESULT_OK){
//
////            Bitmap bitmap = (Bitmap)data.getExtras ().get ("data");
////
////            imageView.setImageBitmap (bitmap);
//            mProgress.setMessage ("Uploading Image...");
//            mProgress.show ();
//
//            Uri uri = data.getData ();
//            StorageReference filePath = mStorage.child ("Photos").child (uri.getLastPathSegment ());
//
//            filePath.putFile (uri).addOnSuccessListener (new OnSuccessListener<UploadTask.TaskSnapshot> () {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                    mProgress.dismiss ();
//
////
//
//                    Toast.makeText (CameraActivity.this,"Uploading Finished", Toast.LENGTH_LONG).show ();
//                }
//
//            });
//
//
//        }
//
//    }

    //............Upload Image to Firebase.......///



}
