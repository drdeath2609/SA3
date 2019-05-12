package nirmauni.ac.in.sa3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class ResultActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private ResultDataTask mAuthTask = null;

    public String mRollNumber;
    private CharSequence mTitle;
    private int mCurrentActivityPosition = 3; //To Sync Navigation Drawer between different
    private Toolbar mToolbar;
    private List<ResultInfo> mResultInfoList;
    ResultAdapter mAdapter;
    protected int mCurrentSemester = 6;
    BarChart mBarChart;
    ArrayList<BarDataSet> mDataSets;
    private int mColorpalette[] = new int[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        //SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(BlankActivity.LOGINPREFERENCES, MODE_PRIVATE);
        mRollNumber = sharedPreferences.getString(getString(R.string.shared_preference_key_roll_number), null);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setTitle(getString(R.string.title_result));
            mToolbar.setBackgroundColor(getResources().getColor(R.color.ColorPrimary));
            setSupportActionBar(mToolbar);
        }

        mColorpalette[0] = getResources().getColor(R.color.md_teal_300);
        mColorpalette[1] = getResources().getColor(R.color.md_red_300);
        mColorpalette[2] = getResources().getColor(R.color.md_blue_300);
        mColorpalette[3] = getResources().getColor(R.color.md_amber_300);
        mColorpalette[4] = getResources().getColor(R.color.md_green_300);
        mColorpalette[5] = getResources().getColor(R.color.md_deep_purple_300);
        mColorpalette[6] = getResources().getColor(R.color.md_deep_orange_300);
        mColorpalette[7] = getResources().getColor(R.color.md_deep_purple_600);
        mColorpalette[8] = getResources().getColor(R.color.md_yellow_900);
        mColorpalette[9] = getResources().getColor(R.color.md_red_900);

        //Initialising Data
        mResultInfoList = new ArrayList<ResultInfo>();
        for (int i=0;i < mCurrentSemester-1;i++){
            ResultInfo resultInfo = new ResultInfo();

            resultInfo.mSemesterNumber = Integer.toString(i + 1);
            resultInfo.mSPI = String.format("%.2f", (Math.random()*5)+5);
            resultInfo.mPPI = String.format("%.2f", (Math.random()*5)+5);
            resultInfo.mSubjectName = initialiseSubjectName();
            resultInfo.mSubjectCode = initialiseSubjectCode();
            resultInfo.mSubjectCredits = initialiseSubjectCredits();
            resultInfo.mSubjectGrades = initialiseSubjectGrade();

            mResultInfoList.add(resultInfo);
        }

        //Chart View
        mBarChart = (BarChart) findViewById(R.id.chart);
        mBarChart.setHighlightEnabled(true);
        mBarChart.setDragEnabled(false);
        mBarChart.setTouchEnabled(false);
        mBarChart.setScaleEnabled(false);
        mBarChart.setPinchZoom(false);
        mBarChart.setDoubleTapToZoomEnabled(false);
        mBarChart.setDrawGridBackground(false);
        mBarChart.setDescription("");
        mBarChart.getLegend().setEnabled(false);
        mBarChart.animateY(2000);

        XAxis x = mBarChart.getXAxis();
        //x.setEnabled(false);
        YAxis y = mBarChart.getAxisLeft();
        //y.setEnabled(false);
        y = mBarChart.getAxisRight();
        y.setEnabled(false);


        ArrayList<BarEntry> yValue = new ArrayList<BarEntry>();
        mDataSets = new ArrayList<BarDataSet>();

        //SPI
        for (int i=0;i < mCurrentSemester;i++){
            yValue.add(new BarEntry((Float.parseFloat(mResultInfoList.get(i).mSPI)),i));
        }

        BarDataSet SPI = new BarDataSet(yValue, "SPI");
        SPI.setAxisDependency(YAxis.AxisDependency.LEFT);
        SPI.setColors(mColorpalette);
        mDataSets.add(SPI);

        //X-Axis
        ArrayList<String> xValue = new ArrayList<String>();
        for (int i=0;i < mCurrentSemester;i++){
            xValue.add("Sem " + (i+1));
        }

        BarData chartData = new BarData(xValue, mDataSets);
        mBarChart.setData(chartData);

        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        mBarChart.invalidate();//Refreshing Chart

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
                SnackbarManager.show(
                        Snackbar.with(ResultActivity.this)
                                .text("Coming Soon..!!"));
                mNavigationDrawerFragment.highlightItem(mCurrentActivityPosition);
                break;
        }
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class ResultDataTask extends AsyncTask<Void, Void, Boolean> {

        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;

        private String mRollNumber;

        String jsonStr = null;

        ResultDataTask() {
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                final String baseUrl = "http://nirma.byethost7.com/sa3/task_manager/v1/resultspi?roll_no=" + mRollNumber;

                URL url = new URL(baseUrl);
                URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
                url = uri.toURL();

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("accept","text/html,application/xhtml+xml,application/xml;q=0.9,/;q=0.8");
                urlConnection.setRequestProperty("user-agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31");
                urlConnection.connect();

                int responseCode = urlConnection.getResponseCode();

                if (responseCode >= 401){
                    Log.v("ABC", "Bad response" + responseCode);
                }

                Log.v("ABC","Above input stream");
                InputStream inputStream = urlConnection.getInputStream();
                Log.v("ABC","Below input stream");
                StringBuffer buffer = new StringBuffer();

                if (inputStream == null){
                    return null;
                }

                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line;

                while ((line = bufferedReader.readLine()) != null) {

                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0){
                    return null;
                }

                jsonStr = buffer.toString();

                Log.v("String : ",jsonStr);
                Log.v("Length : ", "Length " + jsonStr.length());

            } catch (Exception e) {
                return false;
            }

            //Return from server here
            try {
                JSONObject jsonObject = new JSONObject(jsonStr);
                Boolean error = jsonObject.getBoolean("error");

            }catch (Exception e){
                return false;
            }
            /*for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }*/

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;

            //Data fetching successful
            if (success) {

            } else {

            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
        }
    }
}
