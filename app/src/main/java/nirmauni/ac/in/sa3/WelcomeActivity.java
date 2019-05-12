package nirmauni.ac.in.sa3;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class WelcomeActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        WelcomeLogin welcomeLogin = new WelcomeLogin();
        WelcomeIntro welcomeIntro = new WelcomeIntro();
        getSupportFragmentManager().beginTransaction().add(R.id.large, welcomeIntro).commit();
        getFragmentManager().beginTransaction().add(R.id.small, welcomeLogin).commit();
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
