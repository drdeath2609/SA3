package nirmauni.ac.in.sa3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class AttendanceIntro extends android.support.v4.app.Fragment {

    public AttendanceIntro() {
    }

    private ImageView mAttendanceIntroImage;
    private TextView mAttendanceIntroText;
    private boolean isViewShown = false;
    private boolean visibility = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_attendance_intro, container, false);

        mAttendanceIntroImage = (ImageView) rootView.findViewById(R.id.attendanceIntroImage);
        mAttendanceIntroText = (TextView) rootView.findViewById(R.id.attendanceIntroText);
        if (!isViewShown && visibility) {
            Animation transition = AnimationUtils.loadAnimation(getActivity(), R.anim.intro_transit);
            mAttendanceIntroImage.startAnimation(transition);

            AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
            animation.setDuration(1200);
            mAttendanceIntroText.startAnimation(new AlphaAnimation(0.0f, 1.0f));
        }
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            visibility=true;
            Animation transition = AnimationUtils.loadAnimation(getActivity(), R.anim.intro_transit);
            mAttendanceIntroImage.startAnimation(transition);

            AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
            animation.setDuration(1200);
            mAttendanceIntroText.startAnimation(new AlphaAnimation(0.0f, 1.0f));
        }
        if (!isVisibleToUser) {
            visibility=false;
        }
        if (getView() != null)
            isViewShown = true;
        else
            isViewShown = false;
    }

}
