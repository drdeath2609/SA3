package nirmauni.ac.in.sa3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;


public class ResultActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    private CharSequence mTitle;
    private int mCurrentActivityPosition = 3; //To Sync Navigation Drawer between different
    private Toolbar mToolbar;
    private List<ResultInfo> mResultInfoList;
    ResultAdapter mAdapter;
    protected int mCurrentSemester = 5;
    LineChart mLineChart;
    ArrayList<LineDataSet> mDataSets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setTitle("Result");
            mToolbar.setBackgroundColor(getResources().getColor(R.color.ColorPrimary));
            setSupportActionBar(mToolbar);
        }

        //Initialising Data
        mResultInfoList = new ArrayList<ResultInfo>();
        for (int i=0;i < mCurrentSemester;i++){
            ResultInfo resultInfo = new ResultInfo();

            resultInfo.mSemesterNumber = Integer.toString(i+1);
            resultInfo.mSPI = Integer.toString((((i+1)*10)-((i+1)*3))%10);
            resultInfo.mPPI = Integer.toString((((i+1)*10)-((i+1)*3))%10);
            resultInfo.mSubjectName = initialiseSubjectName();
            resultInfo.mSubjectCode = initialiseSubjectCode();
            resultInfo.mSubjectCredits = initialiseSubjectCredits();
            resultInfo.mSubjectGrades = initialiseSubjectGrade();

            mResultInfoList.add(resultInfo);
        }

        //Chart View
        mLineChart = (LineChart) findViewById(R.id.chart);
        mLineChart.setTouchEnabled(false);
        mLineChart.setScaleEnabled(false);
        mLineChart.setPinchZoom(false);
        mLineChart.setDoubleTapToZoomEnabled(false);
        mLineChart.setDescription("Your Performance Throughout");

        ArrayList<Entry> yValue = new ArrayList<Entry>();
        mDataSets = new ArrayList<LineDataSet>();

        //SPI
        for (int i=0;i < mCurrentSemester;i++){
            yValue.add(new Entry((Float.parseFloat(mResultInfoList.get(i).mSPI)),i));
        }

        LineDataSet SPI = new LineDataSet(yValue, "SPI");
        SPI.setAxisDependency(YAxis.AxisDependency.LEFT);
        mDataSets.add(SPI);
        yValue.clear();

        //PPI
        for (int i=0;i < mCurrentSemester;i++){
            yValue.add(new Entry((Float.parseFloat(mResultInfoList.get(i).mPPI)),i));
        }
        LineDataSet PPI = new LineDataSet(yValue, "PPI");
        PPI.setAxisDependency(YAxis.AxisDependency.LEFT);
        mDataSets.add(PPI);

        //X-Axis
        ArrayList<String> xValue = new ArrayList<String>();
        for (int i=0;i < mCurrentSemester;i++){
            xValue.add("Semester " + (i+1));
        }

        LineData chartData = new LineData(xValue, mDataSets);
        mLineChart.setData(chartData);

        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        mLineChart.invalidate();//Refreshing Chart

        //Card View
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.cardList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        mAdapter = new ResultAdapter(mResultInfoList);
        recyclerView.setAdapter(mAdapter);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout), mCurrentActivityPosition, mToolbar);
    }

    public ArrayList<String> initialiseSubjectName(){
        ArrayList<String> subjectName = new ArrayList<String>();

        subjectName.add("Mathematics - I");
        subjectName.add("Mathematics - I");
        subjectName.add("Mathematics - I");
        subjectName.add("Mathematics - I");
        subjectName.add("Mathematics - I");
        subjectName.add("Mathematics - I");

        return subjectName;
    }

    public ArrayList<String> initialiseSubjectCode(){
        ArrayList<String> subjectCode = new ArrayList<String>();

        subjectCode.add("2MA203");
        subjectCode.add("2MA203");
        subjectCode.add("2MA203");
        subjectCode.add("2MA203");
        subjectCode.add("2MA203");
        subjectCode.add("2MA203");

        return subjectCode;
    }

    public ArrayList<String> initialiseSubjectCredits(){
        ArrayList<String> subjectCredit = new ArrayList<String>();

        subjectCredit.add("4");
        subjectCredit.add("4");
        subjectCredit.add("4");
        subjectCredit.add("4");
        subjectCredit.add("4");
        subjectCredit.add("4");

        return subjectCredit;
    }

    public ArrayList<String> initialiseSubjectGrade(){
        ArrayList<String> subjectGrade = new ArrayList<String>();

        subjectGrade.add("A+");
        subjectGrade.add("A+");
        subjectGrade.add("A+");
        subjectGrade.add("A+");
        subjectGrade.add("A+");
        subjectGrade.add("A+");

        return subjectGrade;
    }
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        Intent intent;
        switch (position){
            case 0:
                finish();
                break;
            case 1:
                intent = new Intent(ResultActivity.this, AttendanceActivity.class);
                finish();
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(ResultActivity.this, TimetableActivity.class);
                finish();
                startActivity(intent);
                break;
        }
    }
}
