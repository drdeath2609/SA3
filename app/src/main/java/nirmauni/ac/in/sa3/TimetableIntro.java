package nirmauni.ac.in.sa3;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class TimetableIntro extends android.support.v4.app.Fragment {

    public TimetableIntro() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_timetable_intro, container, false);

        return rootView;
    }
}
