package nirmauni.ac.in.sa3;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Priyesh Patel on 11-July-2015.
 */
public class User extends Fragment {

    private View mView;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private FragmentActivity myContext;
    private com.viewpagerindicator.CirclePageIndicator mIndicator;

    public User() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.user_fragment, container, false);

        int type = myContext.getIntent().getIntExtra(WelcomeLogin.KEY, 0);
        mSectionsPagerAdapter = new SectionsPagerAdapter(myContext.getSupportFragmentManager());
        mViewPager = (ViewPager) mView.findViewById(R.id.userPager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mIndicator = (com.viewpagerindicator.CirclePageIndicator)mView.findViewById(R.id.userCirclePageIndicator);
        mIndicator.setRadius(3*getResources().getDisplayMetrics().density);
        mIndicator.setStrokeColor(getResources().getColor(R.color.md_orange_900));
        mIndicator.setStrokeWidth(0);
        mIndicator.setViewPager(mViewPager);
        mIndicator.setPageColor(getResources().getColor((R.color.md_orange_900)));
        mIndicator.setSnap(true);

        if(type==1){
            mViewPager.setCurrentItem(1);
        }
        return mView;
    }

    @Override
    public void onAttach(Activity activity) {
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a AppIntro (defined as a static inner class
            // below) with the page number as its lone argument.
            android.support.v4.app.Fragment fragment;
            switch (position) {
                case 1:
                    fragment = new UserRegister();
                    break;
                default:
                    fragment = new UserLogin();
            }
            return fragment;
        }


        @Override
        public int getCount() {
            //No of Pages of Tabs
            return 2;
        }
    }
}
