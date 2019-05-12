package nirmauni.ac.in.sa3;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


public class User extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        int type = getIntent().getIntExtra(WelcomeLogin.KEY, 0);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        if (type == 0){
            Login login = new Login();
            fragmentTransaction.add(R.id.fragmentContainer, login).commit();
        }
        else {
            Register register = new Register();
            fragmentTransaction.add(R.id.fragmentContainer, register).commit();
        }
    }
}
