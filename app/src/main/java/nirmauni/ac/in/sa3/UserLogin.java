package nirmauni.ac.in.sa3;


import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

/**
 * Created by Priyesh on 6/14/2015.
 */
public class UserLogin extends Fragment {

    public UserLogin(){

    }

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */

    /*
    ('12bit009', 'QHIbdAmNDf', 0, 0, 1),
    ('12bit065', 'zgY0dNP1pQ', 0, 0, 1),
    ('12bit012', 'jKcDYwIZFr', 0, 0, 1);*/
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private EditText mRollNumberView;
    private EditText mPasswordView;
    private MaterialDialog mDialog;

    private FragmentActivity myContext;
    private com.rey.material.widget.Button mSubmit;

    @Override
    public View onCreateView(final LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        View view = layoutInflater.inflate(R.layout.user_login_fragment, container, false);

        // Set up the login form.
        mRollNumberView = (EditText) view.findViewById(R.id.rollNo);

        mPasswordView = (EditText) view.findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        mRollNumberView.setText("12bit009");
        mPasswordView.setText("password");

        mSubmit = (com.rey.material.widget.Button) view.findViewById(R.id.submit);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNetworkAvailable())
                    attemptLogin();
                else
                    Toast.makeText(myContext, "No Connectivity found", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }


    public Boolean isNetworkAvailable(){

        ConnectivityManager connectivityManager = (ConnectivityManager) myContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()){
            return true;
        }
        else
            return false;

    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mRollNumberView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mRollNumberView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mRollNumberView.setError(getString(R.string.error_field_required));
            focusView = mRollNumberView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mRollNumberView.setError(getString(R.string.error_invalid_email));
            focusView = mRollNumberView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            mDialog = new MaterialDialog.Builder(myContext).content(getString(R.string.login_loader)).progress(true, 0).show();
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        //return email.contains("@");
        return true;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    @Override
    public void onAttach(Activity activity){
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mRollNumber;
        private final String mPassword;
        private Boolean mError;

        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;

        String jsonStr = null;

        UserLoginTask(String email, String password) {
            mRollNumber = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                final String baseUrl = "http://nirma.byethost7.com/sa3/task_manager/v1/login?roll_no=" + mRollNumber + "&password=" + mPassword;

                URL url = new URL(baseUrl);
                URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
                url = uri.toURL();

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                int responseCode = urlConnection.getResponseCode();

                if (responseCode >= 401){
                    Log.v("ABC","Bad response" + responseCode);
                }

                Log.v("ABC","Above input stream");
                InputStream inputStream = urlConnection.getInputStream();
                Log.v("ABC","Below input stream");
                StringBuffer buffer = new StringBuffer();

                if (inputStream == null){
                    return null;
                }

                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line;

                while ((line = bufferedReader.readLine()) != null) {

                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0){
                    return null;
                }

                jsonStr = buffer.toString();

                Log.v("String : ",jsonStr);
                Log.v("Length : ", "Length " + jsonStr.length());

            } catch (Exception e) {
                return false;
            }

            //Return from server here
            try {
                JSONObject jsonObject = new JSONObject(jsonStr);
                mError = jsonObject.getBoolean("error");
                if(mError)
                    return false;
            }catch (Exception e){
                return false;
            }
            /*for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mRollNumber)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }*/
            if(!mError)            {
                ResultDataFetcher mResultDataFetcher = new ResultDataFetcher(myContext);
                mResultDataFetcher.open(mRollNumber);
                AttendanceDataFetcher mAttendanceDataFetcher = new AttendanceDataFetcher(myContext);
                mAttendanceDataFetcher.open(mRollNumber);

            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;

            if (success) {
                SharedPreferences sharedPreferences = myContext.getSharedPreferences(BlankActivity.LOGINPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(getString(R.string.shared_preference_key_login_status), true);
                editor.putString(getString(R.string.shared_preference_key_roll_number), mRollNumber);
                editor.commit();

                myContext.finish();
                Intent intent = new Intent(myContext.getApplicationContext(), BlankActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            } else {
                mDialog.hide();
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
        }
    }
}
