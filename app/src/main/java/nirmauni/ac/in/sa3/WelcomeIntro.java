package nirmauni.ac.in.sa3;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A placeholder fragment containing a simple view.
 */
public class WelcomeIntro extends Fragment {

    public WelcomeIntro() {
    }

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private View view;
    private FragmentActivity myContext;
    private com.viewpagerindicator.CirclePageIndicator mIndicator;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        view = inflater.inflate(R.layout.welcome_fragment, container, false);
        mSectionsPagerAdapter = new SectionsPagerAdapter(myContext.getSupportFragmentManager());
        mViewPager = (ViewPager) view.findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mIndicator = (com.viewpagerindicator.CirclePageIndicator)view.findViewById(R.id.circlePageIndicator);
        mIndicator.setRadius(3*getResources().getDisplayMetrics().density);
        mIndicator.setStrokeColor(getResources().getColor(R.color.md_orange_900));
        mIndicator.setStrokeWidth(0);
        mIndicator.setViewPager(mViewPager);
        mIndicator.setPageColor(getResources().getColor((R.color.md_orange_900)));
        mIndicator.setSnap(true);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter{

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a AppIntro (defined as a static inner class
            // below) with the page number as its lone argument.
            Fragment fragment;
            switch (position) {
                case 1:
                    fragment = new WelcomeIntroAttendance();
                    break;
                case 2:
                    fragment = new WelcomeIntroTimetable();
                    break;
                case 3:
                    fragment = new WelcomeIntroResult();
                    break;
                default:
                    fragment = new WelcomeIntroApp();
            }
            return fragment;
        }


        @Override
        public int getCount() {
            //No of Pages of Tabs
            return 4;
        }
    }
}