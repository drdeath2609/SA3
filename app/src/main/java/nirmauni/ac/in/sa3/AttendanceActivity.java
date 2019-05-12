package nirmauni.ac.in.sa3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class AttendanceActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    public String mRollNumber;
    public int mCurrentActivityPosition = 1; //To Sync Navigation Drawer between different Activities
    private Toolbar mToolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    AttendanceSlidingTabLayout tabs;
    CharSequence Titles[]={"Summary","Lectures","Labs","Tutorials"};
    int Numboftabs =4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance_activity);

        //SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(BlankActivity.LOGINPREFERENCES, MODE_PRIVATE);
        mRollNumber = sharedPreferences.getString(getString(R.string.shared_preference_key_roll_number), null);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setTitle(getString(R.string.title_attendance));
            mToolbar.setBackgroundColor(getResources().getColor(R.color.ColorPrimary));
            setSupportActionBar(mToolbar);
        }

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(0);

        // Assiging the Sliding Tab Layout View
        tabs = (AttendanceSlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(false); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new AttendanceSlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout), mCurrentActivityPosition, mToolbar);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        Intent intent;
        switch (position){
            case 0:
                finish();
                break;
            case 2:
                SnackbarManager.show(
                        Snackbar.with(AttendanceActivity.this)
                                .text("Coming Soon..!!"));
                mNavigationDrawerFragment.highlightItem(mCurrentActivityPosition);
                break;
            case 3:
                intent = new Intent(AttendanceActivity.this, ResultActivity.class);
                finish();
                startActivity(intent);
                break;
        }
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
