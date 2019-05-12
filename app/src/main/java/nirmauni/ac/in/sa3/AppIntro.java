package nirmauni.ac.in.sa3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Dummy.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Dummy#newInstance} factory method to
 * create an instance of this fragment.
 */
/**
 * A dummy fragment representing a section of the app, but that simply
 * displays dummy text.
 */
public class AppIntro extends android.support.v4.app.Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */

    public AppIntro() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_app_intro, container, false);

        return rootView;
    }
}
