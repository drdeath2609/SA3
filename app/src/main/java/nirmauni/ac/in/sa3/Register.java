package nirmauni.ac.in.sa3;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Priyesh on 6/14/2015.
 */
public class Register extends Fragment {

    public Register() {

    }

    private FragmentActivity myContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle onSavedInstanceState){
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        return view;
    }


    @Override
    public void onAttach(Activity activity){
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }
}
