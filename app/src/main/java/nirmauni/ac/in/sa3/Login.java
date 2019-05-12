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
 * Created by Priyesh on 6/14/2015.
 */
public class Login extends Fragment {

    public Login(){

    }

    private FragmentActivity myContext;
    private com.rey.material.widget.Button mSubmit;

    @Override
    public View onCreateView(final LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        View view = layoutInflater.inflate(R.layout.fragment_login, container, false);

        mSubmit = (com.rey.material.widget.Button) view.findViewById(R.id.submit);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*com.rey.material.app.Dialog mDialog = new com.rey.material.app.Dialog(myContext);
                LayoutInflater inflater = myContext.getLayoutInflater();
                View viewDialog = inflater.inflate(R.layout.layout_dialog_loading, null);
                mDialog.setContentView(viewDialog);
                mDialog.show();*/
                Intent intent = new Intent(myContext.getBaseContext(), DashboardActivity.class);
                //MaterialDialog mDialog = new MaterialDialog.Builder(myContext).content("Loading").progress(true, 0).show();
                myContext.finish();
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity){
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }
}
