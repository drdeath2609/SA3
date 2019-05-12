package nirmauni.ac.in.sa3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class BlankActivity extends ActionBarActivity {

    public final static String LOGINPREFERENCES = "Logged In";
    private Boolean mLogIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(BlankActivity.LOGINPREFERENCES, Context.MODE_PRIVATE);
        //Default Value false indicates no user logged in
        mLogIn = sharedPreferences.getBoolean(getString(R.string.shared_preference_key_login_status), false);
        if(mLogIn){
            Intent intent = new Intent(BlankActivity.this, DashboardActivity.class);
            finish();
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(BlankActivity.this, WelcomeActivity.class);
            finish();
            startActivity(intent);
        }
    }
}
