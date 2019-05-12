package nirmauni.ac.in.sa3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;

import java.text.DecimalFormat;
import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class DashboardActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    public String mRollNumber;
    private int mCurrentActivityPosition = 0; //To Sync Navigation Drawer between different Activities
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);

        //SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(BlankActivity.LOGINPREFERENCES, MODE_PRIVATE);
        mRollNumber = sharedPreferences.getString(getString(R.string.shared_preference_key_roll_number), null);

        LinearLayout rootView=(LinearLayout)findViewById(R.id.dashboard);
        rootView.setBackgroundColor(getResources().getColor(R.color.postLoginBackground));

        int randomDuration=(int)(Math.random()*1000)+1500;

        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setTitle(getString(R.string.title_dashboard));
            mToolbar.setBackgroundColor(getResources().getColor(R.color.ColorPrimary));
            setSupportActionBar(mToolbar);
        }

        CardView attendanceCard=(CardView)findViewById(R.id.card_view_dashboard_attendance);
        CardView resultCard=(CardView)findViewById(R.id.card_view_dashboard_result);

        attendanceCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(DashboardActivity.this,AttendanceActivity.class);
                startActivity(i);
            }
        });
        resultCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(DashboardActivity.this,ResultActivity.class);
                startActivity(i);
            }
        });

        // Attendance Card

        AttendanceInfo ai=new AttendanceInfo();
        ArrayList<AttendanceInfo> data=new ArrayList<AttendanceInfo>();
        data=ai.dummydata();
        int lectureCount=0,labCount=0,tutorialCount=0,totalNumberOfLectures=60;

        for(int i=0;i<data.size();i++)
        {
            if(data.get(i).lectureType.equals("l"))
                lectureCount++;
            else if(data.get(i).lectureType.equals("la"))
                labCount++;
            else if(data.get(i).lectureType.equals("t"))
                tutorialCount++;
        }

        PieChart attendancePieChart=(PieChart)findViewById(R.id.dashboard_attendance_chart);
        ArrayList<Entry> values = new ArrayList<Entry>();
        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("Lecture");
        xVals.add("Lab");
        xVals.add("Tutorial");

        values.add(new Entry(lectureCount, 0));
        values.add(new Entry(labCount, 1));
        values.add(new Entry(tutorialCount,2));

        PieDataSet dataSet = new PieDataSet(values,"");
        dataSet.setColors(new int[]{getResources().getColor(R.color.md_orange_300),getResources().getColor(R.color.md_green_300),getResources().getColor(R.color.md_blue_300)});

        attendancePieChart.setData(new PieData(xVals,dataSet));
        attendancePieChart.setCenterText("Attendance\nSummary");
        attendancePieChart.setCenterTextSize(10f);
        attendancePieChart.setHoleColorTransparent(true);
        attendancePieChart.setTouchEnabled(false);
        attendancePieChart.setUsePercentValues(false);
        attendancePieChart.setDrawSliceText(false);
        attendancePieChart.setDescription("Sessions Not Attended");
        attendancePieChart.animateY(randomDuration);
        /*attendancePieChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(DashboardActivity.this,AttendanceActivity.class);
                startActivity(i);
            }
        });*/

        float percentage=((float)(totalNumberOfLectures-data.size())/totalNumberOfLectures)*100;
        DecimalFormat df=new DecimalFormat("##.##");
        String percent=df.format(percentage)+"%";

        TextView attendanceStatistics=(TextView)findViewById(R.id.attendancestatistics);
        attendanceStatistics.setTypeface(null, Typeface.BOLD_ITALIC);
        attendanceStatistics.setText("Sessions Conducted: "+Integer.toString(totalNumberOfLectures)
                                    +"\n\nSessions Attended: "+Integer.toString(totalNumberOfLectures-data.size())
                                    +"\n\nSessions Not Attended: "+Integer.toString(data.size())
                                    +"\n\n\nPunctuality: "+percent
                                    +"\n\n\nRoll Number:"+mRollNumber);

        // Result Card

        LineChart resultLineChart=(LineChart)findViewById(R.id.dashboard_result_chart);
        ArrayList<Entry> spivalues = new ArrayList<Entry>();
        ArrayList<Entry> ppivalues = new ArrayList<Entry>();
        ArrayList<String> rxVals = new ArrayList<String>();
        int no_of_semesters=6;
        for(int i=0;i<no_of_semesters;i++)
            rxVals.add("Sem "+Integer.toString(i+1));

        spivalues.add(new Entry(5.35f,0));
        spivalues.add(new Entry(7.45f,1));
        spivalues.add(new Entry(4.60f,2));
        spivalues.add(new Entry(9.81f,3));
        spivalues.add(new Entry(8.30f,4));
        spivalues.add(new Entry(8.14f,5));

        ppivalues.add(new Entry(5.35f,0));
        ppivalues.add(new Entry(7.16f,1));
        ppivalues.add(new Entry(4.24f,2));
        ppivalues.add(new Entry(6.61f,3));
        ppivalues.add(new Entry(9.83f,4));
        ppivalues.add(new Entry(6.87f,5));

        LineDataSet spi=new LineDataSet(spivalues,"SPI");
        LineDataSet ppi=new LineDataSet(ppivalues,"PPI");

        spi.setColor(getResources().getColor(R.color.md_blue_400));
        ppi.setColor(getResources().getColor(R.color.md_green_400));
        spi.setDrawValues(false);
        ppi.setDrawValues(false);
        spi.setLineWidth(2.5f);
        ppi.setLineWidth(2.5f);
        spi.setDrawCircles(false);
        ppi.setDrawCircles(false);

        ArrayList<LineDataSet> sets=new ArrayList<LineDataSet>();
        sets.add(spi);
        sets.add(ppi);

        resultLineChart.setData(new LineData(rxVals,sets));
        resultLineChart.setDrawGridBackground(false);
        resultLineChart.setDrawBorders(false);
        resultLineChart.setDescription("");
        resultLineChart.setTouchEnabled(false);

        XAxis xAxis=resultLineChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis rightAxis = resultLineChart.getAxisRight();
        //rightAxis.setDrawAxisLine(false);
        //rightAxis.setDrawLabels(false);

        resultLineChart.animateXY(randomDuration,randomDuration);

        TextView quote=(TextView)findViewById(R.id.quote);
        quote.setTypeface(null,Typeface.BOLD_ITALIC);
        quote.setText("Defeat is not the Worst of Failures\nNot to have tried is the True Failure");

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout), mCurrentActivityPosition, mToolbar);
    }

    @Override
    public void onResume(){
        super.onResume();
        mNavigationDrawerFragment.highlightItem(mCurrentActivityPosition);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        Intent intent;
        switch (position){
            case 1:
                intent = new Intent(DashboardActivity.this, AttendanceActivity.class);
                startActivity(intent);
                break;
            case 2:
                SnackbarManager.show(
                        Snackbar.with(DashboardActivity.this)
                                .text("Coming Soon..!!"));
                mNavigationDrawerFragment.highlightItem(mCurrentActivityPosition);
                break;
            case 3:
                intent = new Intent(DashboardActivity.this, ResultActivity.class);
                startActivity(intent);
                break;
        }
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
