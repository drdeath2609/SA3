package nirmauni.ac.in.sa3;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


public class WelcomeActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        WelcomeLogin welcomeLogin = new WelcomeLogin();
        Welcome welcome = new Welcome();
        getSupportFragmentManager().beginTransaction().add(R.id.large, welcome).commit();
        getFragmentManager().beginTransaction().add(R.id.small, welcomeLogin).commit();
    }
}
