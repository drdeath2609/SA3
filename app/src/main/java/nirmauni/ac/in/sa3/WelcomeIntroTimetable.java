package nirmauni.ac.in.sa3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class WelcomeIntroTimetable extends android.support.v4.app.Fragment {

    public WelcomeIntroTimetable() {
    }

    ImageView mTimetableIntroImage;

    //create animation
    Animation anim_fade_in,anim_fade_out;
    int flag = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.intro_timetable_fragment, container, false);
        mTimetableIntroImage = (ImageView) rootView.findViewById(R.id.timetableIntroImage);
        mTimetableIntroImage.setVisibility(View.GONE);
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            //load and call animation
            mTimetableIntroImage.setVisibility(View.VISIBLE);
            anim_fade_in = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fade_in);
            mTimetableIntroImage.startAnimation(anim_fade_in);
            flag = 1;
        }
        else {
            if (flag == 1) {
                anim_fade_out = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.fade_out);
                mTimetableIntroImage.startAnimation(anim_fade_out);
            }
        }
    }

}
