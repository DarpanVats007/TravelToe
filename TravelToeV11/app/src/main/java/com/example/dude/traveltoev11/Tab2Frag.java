package com.example.dude.traveltoev11;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;

import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.UploadTask;




import com.squareup.picasso.Picasso;

import java.io.File;
import java.security.Permission;



public class Tab2Frag extends Fragment {

    View mView;
    Button mButtonChooseImage;
    Button mButtonUpload;
    Button mOpenCamera;
    ImageView mImageView;
    ImageView mCameraImageView;
    ProgressBar mProgressbar;
    Button mAddLocation;
    Uri mImageUri;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;
    private EditText mEditTextFileName;
    private TextView mTextViewShowUploads;

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int TAKE_PICTURE = 1;



    // private static final int CAMERA_REQUEST = 1888;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // View  view=inflater.inflate (R.layout.frag_tab2,container,false);
        mView = inflater.inflate (R.layout.frag_tab2, container, false);
        mOpenCamera = (Button) mView.findViewById (R.id.openCamera);

        mButtonChooseImage = (Button) mView.findViewById (R.id.fileChose);
        mButtonUpload = (Button) mView.findViewById (R.id.buttonUpload);
        mImageView = (ImageView) mView.findViewById (R.id.imageViewGallery);
        mCameraImageView = (ImageView) mView.findViewById (R.id.imageViewClick);


        mProgressbar = (ProgressBar) mView.findViewById (R.id.progressBar);
        mAddLocation = (Button) mView.findViewById (R.id.addLocation);
        mEditTextFileName = (EditText) mView.findViewById (R.id.edit_text_file_name);
        mStorageRef = FirebaseStorage.getInstance ().getReference ("uploads");
        mDatabaseRef = FirebaseDatabase.getInstance ().getReference ("uploads");
        mTextViewShowUploads = (Button) mView.findViewById(R.id.buttonUpload);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);

        mButtonChooseImage.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                openFileChooser ();

            }
        });

        mButtonUpload.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress ()) {
                    Toast.makeText (getContext (), "Upload in progress", Toast.LENGTH_SHORT).show ();
                } else {
                    uploadFile ();
                }
            }
        });
        mTextViewShowUploads.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
             //   openImagesActivity ();

            }
        });

        mOpenCamera.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                   openCamera ();

//                if (getActivity ().checkPermission (Permission.))



            }
        });


    }


    private void openFileChooser() {

        Intent intent = new Intent ();
        intent.setType ("image/*");
        intent.setAction (Intent.ACTION_GET_CONTENT);

        startActivityForResult (Intent.createChooser (intent, "Choose Picture"), 0);

//        startActivityForResult(intent, );
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult (requestCode, resultCode, data);
//
//        if (requestCode == 1)
//
//        {
//            mImageUri = data.getData ();
//
//            Picasso.with (getActivity ()).load (mImageUri).into (mImageView);
//        }
//
//        if (resultCode == 0) {
//
//            Uri selectedImage = mImageUri;
//            getActivity ().getContentResolver().notifyChange(selectedImage, null);
//            //ImageView mCameraImageView = (ImageView) mView.findViewById (R.id.imageViewClick);
//            ContentResolver cr = getActivity ().getContentResolver();
//            Bitmap bitmap;
//            try {
//                bitmap = android.provider.MediaStore.Images.Media
//                        .getBitmap(cr, selectedImage);
//
//                mCameraImageView.setImageBitmap(bitmap);
//                Toast.makeText(getContext (), selectedImage.toString(),
//                        Toast.LENGTH_LONG).show();
//            } catch (Exception e) {
//                Toast.makeText(getActivity (), "Failed to load", Toast.LENGTH_SHORT)
//                        .show();
//                Log.e("Camera", e.toString());
//            }
//        }
//
////
//
//
//
//    }

    private String getFileExtension(Uri Uri) {

        ContentResolver cR = getActivity ().getContentResolver ();
        MimeTypeMap mime = MimeTypeMap.getSingleton ();
        return mime.getExtensionFromMimeType (cR.getType (Uri));
    }

    private void uploadFile() {

        if (mImageUri != null) {
            StorageReference fileReference = mStorageRef.child (System.currentTimeMillis ()
                    + "." + getFileExtension (mImageUri));

            mUploadTask = fileReference.putFile (mImageUri)
                    .addOnSuccessListener (new OnSuccessListener<UploadTask.TaskSnapshot> () {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler ();
                            handler.postDelayed (new Runnable () {
                                @Override
                                public void run() {
                                    mProgressbar.setProgress (0);
                                }
                            }, 500);

                            Toast.makeText (getContext (), "Upload successful", Toast.LENGTH_LONG).show ();
                            ImageUpload upload = new ImageUpload (mEditTextFileName.getText ().toString ().trim (),
                                    taskSnapshot.getUploadSessionUri ().toString ());
                            String uploadId = mDatabaseRef.push ().getKey ();
                            mDatabaseRef.child (uploadId).setValue (upload);
                        }
                    })
                    .addOnFailureListener (new OnFailureListener () {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText (getContext (), e.getMessage (), Toast.LENGTH_SHORT).show ();

                        }
                    })
                    .addOnProgressListener (new OnProgressListener<UploadTask.TaskSnapshot> () {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred () / taskSnapshot.getTotalByteCount ());
                            mProgressbar.setProgress ((int) progress);
                        }
                    });
        } else {
            Toast.makeText (getContext (), "No file selected", Toast.LENGTH_SHORT).show ();
        }


    }

    private void openImagesActivity() {
//        Intent intentImage = new Intent (getActivity (), Tab3Frag.class);
//        startActivity (intentImage);
        startActivity(new Intent(getActivity (), Tab3Frag.class));



    }

    private void openCamera(){


        //Intent intent = new Intent(getActivity (), "android.media.action.IMAGE_CAPTURE");
       // Intent intent = new Intent(getActivity (), CameraActivity.class);
      //  Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        Intent intentCamera = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
      //  startActivityForResult (intentCamera,CAMERA_REQUEST_CODE);
       // startActivityForResult (Intent.createChooser (intent, "Choose Picture"), 1);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        File photo = new File(Environment.getExternalStorageDirectory(),  "Pic.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                FileProvider.getUriForFile(getContext (),
                BuildConfig.APPLICATION_ID + ".provider",
                photo));
        //Uri.fromFile(photo));


        mImageUri = FileProvider.getUriForFile(getContext (),
                BuildConfig.APPLICATION_ID + ".provider", photo);






       // Uri photoURI = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".my.package.name.provider", createImageFile());


        // startActivityForResult (Intent.createChooser (intent, "Save image"), 1);


//        startActivityForResult(intent, 1888);

      //  startActivityForResult (Intent.createChooser (intent, "Choose Picture"), 1);
       // startActivityForResult (Intent.createChooser (intent, TAKE_PICTURE, 2);
        startActivityForResult(intent, TAKE_PICTURE);
        // startActivityForResult (intent,TAKE_PICTURE, 2);




    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PICTURE:
//                if (resultCode == Activity.RESULT_OK) {
                if (resultCode == Activity.RESULT_OK) {

                    Uri selectedImage = mImageUri;
                    getActivity ().getContentResolver().notifyChange(selectedImage, null);
                    //ImageView mCameraImageView = (ImageView) mView.findViewById (R.id.imageViewClick);
                    ContentResolver cr = getActivity ().getContentResolver();
                    Bitmap bitmap;
                    try {
                        bitmap = android.provider.MediaStore.Images.Media
                                .getBitmap(cr, selectedImage);

                        mCameraImageView.setImageBitmap(bitmap);
                        Toast.makeText(getContext (), selectedImage.toString(),
                                Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity (), "Failed to load", Toast.LENGTH_SHORT)
                                .show();
                        Log.e("Camera", e.toString());
                    }
                }
        }
    }




}
