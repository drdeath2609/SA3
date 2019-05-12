package nirmauni.ac.in.sa3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AttendanceIntro extends android.support.v4.app.Fragment {

    public AttendanceIntro() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_result_intro, container, false);

        return rootView;
    }
}
