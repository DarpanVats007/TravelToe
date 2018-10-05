package com.example.dude.traveltoev11;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText e1,e2;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);

        e1 = (EditText) findViewById (R.id.edit1);
        e2 = (EditText) findViewById (R.id.edit2);

        auth = FirebaseAuth.getInstance ();

    }

    public void loginUser (View v) {

        if(e1.getText ().toString ().equals ("") && e2.getText ().toString ().equals ("")){

            Toast.makeText (getApplicationContext (),"Blank space not allowed",Toast.LENGTH_SHORT).show ();

        }

        else{

            auth.signInWithEmailAndPassword (e1.getText ().toString (),e2.getText ().toString ())
                    .addOnCompleteListener (new OnCompleteListener<AuthResult> () {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful ()) {
                                Toast.makeText (getApplicationContext (), "User loggedin successfully", Toast.LENGTH_SHORT).show ();

                                finish ();
                                Intent i = new Intent (getApplicationContext (), Tabbed_profile.class);
                                startActivity (i);


                            }

                            else {

                                Toast.makeText (getApplicationContext (),"User could not be Logged in",Toast.LENGTH_SHORT).show ();

                            }
                        }


                    });
        }
    }
}
