package com.example.machine2.movietime.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.machine2.movietime.R;
import com.example.machine2.movietime.activities.MainActivity;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
//fragment for the login activity

public class LoginActivityFragment extends Fragment {

    LoginButton loginButton;
    CallbackManager callbackManager;
    TextView textDetails;
    ImageView Profpicture;
    AccessTokenTracker tracker;
    ProfileTracker profileTracker;
    AccessToken accessToken;
    Profile profile;
    String image_url;


    //facebook login
    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {


        @Override
        public void onSuccess(LoginResult loginResult) {

            accessToken = loginResult.getAccessToken();
            profile = Profile.getCurrentProfile();
            displayWelcomeMessage(profile);

            if (AccessToken.getCurrentAccessToken() != null) {

                Log.v("User is login", "YES");
                loginButton.setVisibility(View.INVISIBLE);

                Intent intent = new Intent(getActivity(),MainActivity.class);
                intent.putExtra("url",image_url);

                startActivity(intent);
                getActivity().finish();

            } else {

                Log.v("User is not login", "OK");
                LoginManager.getInstance().logInWithReadPermissions(getActivity(), (Arrays.asList("public_profile", "user_friends", "user_birthday", "user_about_me", "email")));
            }
        }


        @Override
        public void onCancel() {
        }

        @Override
        public void onError(FacebookException error) {
        }
    };

    public LoginActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();





        tracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            }
        };
        accessToken = AccessToken.getCurrentAccessToken();

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                displayWelcomeMessage(currentProfile);
            }
        };
        tracker.startTracking();
        profileTracker.startTracking();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_loginactivity_fragment, container, false);
    }

    //set the display messages


    public void displayWelcomeMessage(Profile profile)
    {
        if(profile!=null)
        {

            textDetails.setText("Welcome"+"\t"+profile.getName());
            //Profpicture.setImageResource(profile.getProfilePictureUri(150,1500),);

            image_url = profile.getProfilePictureUri(150,200).toString();




        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        // If using in a fragment
        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager, callback);
        textDetails = (TextView) view.findViewById(R.id.details);
    }

    //resuming the data of the user before login
    @Override
    public void onResume() {

        super.onResume();
        profile = Profile.getCurrentProfile();
        displayWelcomeMessage(profile);
    }

    //dismiss the data
    @Override
    public void onStop() {

        super.onStop();
        tracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
