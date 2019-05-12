package nirmauni.ac.in.sa3;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Priyesh
 */
public class WelcomeLogin extends Fragment {

    public WelcomeLogin() {
        // Required empty public constructor
    }

    public final static String KEY = "Type";
    private com.rey.material.widget.FloatingActionButton mLogin;
    private com.rey.material.widget.FloatingActionButton mRegister;
    private FragmentActivity myContext;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome_login, container, false);

        mLogin = (com.rey.material.widget.FloatingActionButton) view.findViewById(R.id.login);
        mRegister = (com.rey.material.widget.FloatingActionButton) view.findViewById(R.id.register);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myContext.getBaseContext(), User.class);
                intent.putExtra(KEY, 0);
                startActivity(intent);
            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myContext.getBaseContext(), User.class);
                intent.putExtra(KEY, 1);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }
}
