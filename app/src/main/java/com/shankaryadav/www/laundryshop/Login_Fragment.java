package com.shankaryadav.www.laundryshop;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import de.hdodenhof.circleimageview.CircleImageView;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import org.json.JSONException;
import org.json.JSONObject;
import static com.facebook.AccessTokenManager.TAG;
import static com.facebook.FacebookSdk.getApplicationContext;


public class Login_Fragment extends Fragment implements OnClickListener {
    private static View view;

    private static EditText emailid, password;
    private static Button loginButton;
    private static TextView forgotPassword, signUp;
    private static CheckBox show_hide_password;
    private static LinearLayout loginLayout;
    private static Animation shakeAnimation;
    private static FragmentManager fragmentManager;

    CircleImageView fb;
    CircleImageView google;
    private GoogleSignInClient mGoogleSignInClient;
    
    public static final int RC_SIGN_IN = 111;

    private FirebaseAuth mAuth;
    private CallbackManager mCallbackManager;


    String getEmailId;
    String getPassword;

    ProgressDialog dialog;

    FirebaseAuth.AuthStateListener mAuthstatelistener;

    public Login_Fragment() {

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

        mAuth.addAuthStateListener (mAuthstatelistener);


    }

    @Override
    public void onStop() {
        super.onStop ();

        if(mAuthstatelistener != null) {
            mAuth.removeAuthStateListener (mAuthstatelistener);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login_layout, container, false);
        mAuth = FirebaseAuth.getInstance();

        mAuthstatelistener = new FirebaseAuth.AuthStateListener () {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                // Sign in logic here.
                if (user != null){
                    startActivity (new Intent (getActivity (),MainActivity.class));
                    getActivity ().finish ();
                }
            }
        };

        dialog = new ProgressDialog (getActivity ());

        dialog.setMessage ("Authenticating...");
        dialog.setTitle ("Please wait...");



//        FacebookSdk.sdkInitialize(getApplicationContext ());
//        AppEventsLogger.activateApp(getApplicationContext ());

        initViews();
        setListeners();
        gBuilder ();



        return view;
    }

    public void gBuilder(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getActivity (), gso);
    }

    // Initiate Views
    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();

        emailid = (EditText) view.findViewById(R.id.login_emailid);
        password = (EditText) view.findViewById(R.id.login_password);
        loginButton = (Button) view.findViewById(R.id.loginBtn);
        forgotPassword = (TextView) view.findViewById(R.id.forgot_password);
        signUp = (TextView) view.findViewById(R.id.createAccount);
        show_hide_password = (CheckBox) view
                .findViewById(R.id.show_hide_password);
        loginLayout = (LinearLayout) view.findViewById(R.id.login_layout);

        fb = view.findViewById (R.id.fb);
        google = view.findViewById (R.id.google);

        // Load ShakeAnimation
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.shake_animation);

        // Setting text selector over textviews
        XmlResourceParser xrp = getResources().getXml(R.xml.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);

            forgotPassword.setTextColor(csl);
            show_hide_password.setTextColor(csl);
            signUp.setTextColor(csl);
        } catch (Exception e) {
        }
    }

    // Set Listeners
    private void setListeners() {
        loginButton.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        signUp.setOnClickListener(this);

        fb.setOnClickListener (this);
        google.setOnClickListener (this);


        // Set check listener over checkbox for showing and hiding password
        show_hide_password
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton button,
                                                 boolean isChecked) {

                        // If it is checkec then show password else hide
                        // password
                        if (isChecked) {

                            show_hide_password.setText(R.string.hide_pwd);// change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT);
                            password.setTransformationMethod(HideReturnsTransformationMethod
                                    .getInstance());// show password
                        } else {
                            show_hide_password.setText(R.string.show_pwd);
                            // change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT
                                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            password.setTransformationMethod(PasswordTransformationMethod
                                    .getInstance());// hide password

                        }

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                checkValidation();


                break;

            case R.id.forgot_password:

                // Replace forgot password fragment with animation
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_exit)
                        .replace(R.id.frameContainer,
                                new ForgotPassword_Fragment(),
                                Utils.ForgotPassword_Fragment).commit();
                break;
            case R.id.createAccount:

                // Replace signup frgament with animation
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_exit)
                        .replace(R.id.frameContainer, new SignUp_Fragment(),
                                Utils.SignUp_Fragment).commit();
                break;


            case R.id.fb :
                    signinwithfb();
                break;
            case R.id.google:
                    signinwithgoogle();
                break;
        }

    }



    private void signinwithfb() {

// Initialize Facebook Login button

        mCallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email","user_photos","public_profile"));

        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(final LoginResult loginResult) {

                        dialog.show ();

                        handleFacebookAccessToken(loginResult.getAccessToken());

//                        Toast.makeText (getContext (), "hshhshshsh", Toast.LENGTH_SHORT).show ();
                        GraphRequest.newMeRequest(
                                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject json, GraphResponse response) {
                                        if (response.getError() != null) {
                                            // handle error
                                            System.out.println("ERROR");
                                        } else {
                                            System.out.println("Success");

                                            try {


                                                String jsonresult = String.valueOf(json);
                                                System.out.println("JSON Result"+jsonresult);

                                                String str_email = json.getString("email");
                                                String str_id = json.getString("id");
                                                String str_firstname = json.getString("first_name");
                                                String str_lastname = json.getString("last_name");

                                                Toast.makeText (getActivity (), ""+str_email+str_id+str_firstname, Toast.LENGTH_LONG).show ();

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }

                                }).executeAsync();

                    }

                    @Override
                    public void onCancel() {
                        Log.d("TAG_CANCEL","On cancel");
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d("TAG_ERROR",error.toString());
                    }
                });
    }

    private void handleFacebookAccessToken(AccessToken accessToken) {

        Log.d(TAG, "handleFacebookAccessToken:" + accessToken);


        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener ((Activity) getActivity (), new OnCompleteListener<AuthResult> () {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            if (dialog.isShowing ()){
                                dialog.dismiss ();
                            }
                            FirebaseUser user = mAuth.getCurrentUser();

                            startActivity (new Intent (getActivity ().getApplicationContext (),MainActivity.class));
                            getActivity ().finish ();

                        }else {
                            Toast.makeText (getActivity ().getApplicationContext (), ""+task.getException (), Toast.LENGTH_SHORT).show ();
                            dialog.dismiss ();
                        }
                    }
                });
    }


    private void signinwithgoogle(){



        signIn ();
    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("TAG---", "Google sign in failed", e);
                Toast.makeText (getActivity (), ""+e.getMessage (), Toast.LENGTH_LONG).show ();
                // ...
            }
        }else {
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.d("TAG____", "firebaseAuthWithGoogle:" + account.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener (new OnCompleteListener<AuthResult> () {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful ()){
                            Toast.makeText (getActivity (), "successful", Toast.LENGTH_SHORT).show ();
                            startActivity (new Intent (getActivity (),MainActivity.class));
                            getActivity ().finish ();
                        }else{
                            Toast.makeText (getActivity (), "something is wrong", Toast.LENGTH_SHORT).show ();
                        }
                    }
                });
    }

    // Check Validation before login
    private void checkValidation() {
        // Get email id and password
         getEmailId = emailid.getText().toString();
         getPassword = password.getText().toString();

        // Check patter for email id
        Pattern p = Pattern.compile(Utils.regEx);

        Matcher m = p.matcher(getEmailId);

        // Check for both field is empty or not
        if (getEmailId.equals("") || getEmailId.length() == 0
                || getPassword.equals("") || getPassword.length() == 0) {
            loginLayout.startAnimation(shakeAnimation);
            new CustomToast().Show_Toast(getActivity(), view,
                    "Enter both credentials.");

        }
        // Check if email id is valid or not
        else if (!m.find()) {
            new CustomToast ().Show_Toast (getActivity (), view,
                    "Your Email Id is Invalid.");
            // Else do login and do your stuff
        }else {
            dialog.show ();
            loginVerify (getEmailId, getPassword);
        }

    }

    private void loginVerify(String getEmailId, String getPassword) {

        mAuth.signInWithEmailAndPassword(getEmailId,getPassword)
                .addOnCompleteListener((Activity) getActivity (), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            dialog.dismiss ();

                            startActivity (new Intent (getActivity (),MainActivity.class));
                            getActivity ().finish ();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getActivity (), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            dialog.dismiss ();

                        }

                    }
                });

    }
}
