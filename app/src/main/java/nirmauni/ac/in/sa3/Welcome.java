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

import java.util.Locale;


/**
 * A placeholder fragment containing a simple view.
 */
public class Welcome extends Fragment {

    public Welcome() {
    }

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private View view;
    private FragmentActivity myContext;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        view = inflater.inflate(R.layout.fragment_welcome, container, false);
        mSectionsPagerAdapter = new SectionsPagerAdapter(myContext.getSupportFragmentManager());
        mViewPager = (ViewPager) view.findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

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
                    fragment = new AttendanceIntro();
                    break;
                case 2:
                    fragment = new TimetableIntro();
                    break;
                case 3:
                    fragment = new ResultIntro();
                    break;
                default:
                    fragment = new AppIntro();
            }
            return fragment;
        }


        @Override
        public int getCount() {
            //No of Pages of Tabs
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return ("Teste1").toUpperCase(l);
                case 1:
                    return ("Teste2").toUpperCase(l);
                case 2:
                    return ("Teste3").toUpperCase(l);
            }
            return null;
        }
    }
}