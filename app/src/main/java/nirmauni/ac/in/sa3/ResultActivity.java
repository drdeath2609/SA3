package nirmauni.ac.in.sa3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class ResultActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    private CharSequence mTitle;
    private int mCurrentActivityPosition = 3; //To Sync Navigation Drawer between different
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setTitle("Navigation Drawer");
            setSupportActionBar(mToolbar);
        }

        Toast.makeText(getApplicationContext(), "Yo Result!", Toast.LENGTH_SHORT).show();

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

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
                intent = new Intent(ResultActivity.this, DashboardActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(ResultActivity.this, AttendanceActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(ResultActivity.this, TimetableActivity.class);
                startActivity(intent);
                break;
        }
    }
}
