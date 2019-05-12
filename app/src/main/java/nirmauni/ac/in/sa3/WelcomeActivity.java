package nirmauni.ac.in.sa3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


public class WelcomeActivity extends FragmentActivity {

    public final static String LOGINPREFERENCES = "Logged In";
    private Boolean mLogIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        SharedPreferences sharedPreferences = getSharedPreferences(WelcomeActivity.LOGINPREFERENCES, Context.MODE_PRIVATE);
        //Default Value false indicates no user logged in
        mLogIn = sharedPreferences.getBoolean("login", false);
        if(mLogIn){
            Intent intent = new Intent(WelcomeActivity.this, DashboardActivity.class);
            startActivity(intent);
        }


        WelcomeLogin welcomeLogin = new WelcomeLogin();
        Welcome welcome = new Welcome();
        getSupportFragmentManager().beginTransaction().add(R.id.large, welcome).commit();
        getFragmentManager().beginTransaction().add(R.id.small, welcomeLogin).commit();
    }
}
