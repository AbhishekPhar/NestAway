package com.example.admin.nestaway;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment {
    CallbackManager callbackManager;
    AccessTokenTracker accessTokenTracker;
    ProfileTracker profileTracker;
    LoginButton loginButton;
    FacebookCallback<LoginResult> callback;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        TabHost th;
        Button btn,signbtn;
        //ImageView image;
        final EditText fname,sname,regemail,regispassword,semail,spassword,rpassword;
        loginButton = view.findViewById(R.id.login);
        th = (TabHost) view.findViewById(R.id.host);
        btn = (Button) view.findViewById(R.id.regi);
        signbtn = (Button) view.findViewById(R.id.signin);
        fname = (EditText) view.findViewById(R.id.firstname);
        sname = (EditText) view.findViewById(R.id.surname);
        regemail = (EditText) view.findViewById(R.id.registeremail);
        semail = (EditText) view.findViewById(R.id.signemail);
        spassword = (EditText) view.findViewById(R.id.signpassword);
        rpassword = (EditText) view.findViewById(R.id.password);
        progressBar = view.findViewById(R.id.progress);
        mAuth = FirebaseAuth.getInstance();

        th.setup();

        TabHost.TabSpec tabSpec = th.newTabSpec("TabOne");
        tabSpec.setIndicator("LogIn");
        tabSpec.setContent(R.id.tab1);
        th.addTab(tabSpec);

        tabSpec = th.newTabSpec("TabTwo");
        tabSpec.setIndicator("Register");
        tabSpec.setContent(R.id.tab2);
        th.addTab(tabSpec);

        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = semail.getText().toString();
                String password = spassword.getText().toString();

                if(password.isEmpty())
                {
                    spassword.setError("Please Enter Password");
                    spassword.requestFocus();
                    return;
                }

                if(email.isEmpty())
                {
                    semail.setError("Please Enter Email");
                    semail.requestFocus();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    semail.setError("Please Enter Valid Email");
                    semail.requestFocus();
                    return;
                }

                if(password.length()<6)
                {
                    spassword.setError("Please Enter Password With Minimum 6");
                    spassword.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            FragmentManager fragmentManager = getFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.main_layout,new Home());
                            fragmentTransaction.commit();
                        }else {
                            Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strpassword = rpassword.getText().toString().trim();
                String strfname = fname.getText().toString().trim();
                String strsname = sname.getText().toString().trim();
                String stremail = regemail.getText().toString().trim();

                if (strsname.isEmpty()) {
                    sname.setError("Please Enter Last Name");
                    sname.requestFocus();
                    return;
                }

                if(strfname.isEmpty())
                {
                    fname.setError("Please Enter First Name");
                    fname.requestFocus();
                    return;
                }

                if(strpassword.isEmpty())
                {
                    rpassword.setError("Please Enter Password");
                    rpassword.requestFocus();
                    return;
                }

                if(stremail.isEmpty())
                {
                    regemail.setError("Please Enter Email");
                    regemail.requestFocus();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(stremail).matches()){
                    regemail.setError("Please Enter Valid Email");
                    regemail.requestFocus();
                    return;
                }

                if(strpassword.length()<6)
                {
                    rpassword.setError("Please Enter Password With Minimum 6");
                    rpassword.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                mAuth.createUserWithEmailAndPassword(stremail,strpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if(task.isSuccessful())
                        {
                            Toast.makeText(getActivity(), "Registration SuccessFull", Toast.LENGTH_SHORT).show();
                        }else{
                            if(task.getException() instanceof FirebaseAuthUserCollisionException ){
                                Toast.makeText(getActivity(), "User is Already Registered", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });

        FacebookSdk.sdkInitialize(getApplicationContext());

        callbackManager = CallbackManager.Factory.create();

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                displayMessage(currentProfile);
            }
        };

        accessTokenTracker.startTracking();
        profileTracker.startTracking();

        callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                Profile profile = Profile.getCurrentProfile();
                displayMessage(profile);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile profile, Profile profile1) {

                displayMessage(profile1);

            }
        };

        accessTokenTracker.startTracking();
        profileTracker.startTracking();


        callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                AccessToken accessToken = loginResult.getAccessToken();
                Profile profile = Profile.getCurrentProfile();
                displayMessage(profile);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }
        };


        loginButton.setReadPermissions("user_friends");
        loginButton.registerCallback(callbackManager, callback);

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }


    @Override
    public void onResume() {
        super.onResume();

        Profile profile = Profile.getCurrentProfile();
        displayMessage(profile);
    }

    private void displayMessage(Profile profile) {
        if (profile != null) {
            String name = profile.getName();
            String url = profile.getProfilePictureUri(150, 150).toString();

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_layout, new Home());
            fragmentTransaction.commit();
        }else{
        /*    FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_layout, new Home());
            fragmentTransaction.commit();*/
            Toast.makeText(getActivity(), "No Profile", Toast.LENGTH_SHORT).show();
        }
    }
}