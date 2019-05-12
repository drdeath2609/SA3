package nirmauni.ac.in.sa3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

/**
 * Created by Dr. Death on 7/15/2015.
 */
public class ResultDataFetcher {

    public static final String DB_NAME = "SA3";
    public static final String TB_SPI_NAME = "ResultSPI";
    public static final String COL_SPI_SEM = "Semester";
    public static final String COL_SPI_SPI = "SPI";
    public static final String COL_SPI_PPI = "PPI";
    public static final String TB_GRADE_NAME = "ResultGrade";
    public static final String COL_GRADE_COURCE_CODE = "CCode";
    public static final String COL_GRADE_GRADE = "Grade";
    public static final String COL_GRADE_SEM = "Semester";
    public static final int DB_VERSION = 2;

    public String mRollNumber;

    //Create Table Query
    public static final String TB_SPI_CREATE = "create table " + TB_SPI_NAME + " (" + COL_SPI_SEM + " text, " + COL_SPI_SPI + " text, " + COL_SPI_PPI + " text);";

    public static final String TB_GRADE_CREATE = "create table " + TB_GRADE_NAME + " (" + COL_GRADE_SEM + " text, " + COL_GRADE_COURCE_CODE + " text, " + COL_GRADE_GRADE + " text);";

    private SQLiteDatabase mDatabase;
    private DBController mController;

    private ResultSPIDataTask mResultSPIDataTask;
    private ResultGradeDataTask mResultGradeDataTask;

    //Constructor
    public ResultDataFetcher(Context context) {
        mController = new DBController(context, DB_NAME, null, DB_VERSION);
    }

    public class DBController extends SQLiteOpenHelper {

        //Constructor of Database Controller
        public DBController(Context context, String dbName, SQLiteDatabase.CursorFactory cursorFactory, int version) {
            super(context, dbName, cursorFactory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(TB_SPI_CREATE);
            db.execSQL(TB_GRADE_CREATE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS" + TB_GRADE_NAME);
            db.execSQL("DROP TABLE IF EXISTS" + TB_SPI_NAME);
            onCreate(db);
        }
    }

    public ResultDataFetcher open(String rollNumber) {
        mDatabase = mController.getWritableDatabase();
        mRollNumber = rollNumber;
        mResultSPIDataTask = new ResultSPIDataTask(mRollNumber);
        for (int i=1;i < 11;i++){
            mResultGradeDataTask = new ResultGradeDataTask(mRollNumber, Integer.toString(i));
        }
        mResultSPIDataTask.execute((Void) null);
        return this;
    }

    //Result SPI AsyncTask
    public class ResultSPIDataTask extends AsyncTask<Void, Void, JSONArray> {

        public final String mRollNumber;

        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;

        String mJSONStrSPI = null;
        JSONArray mJSONArray;

        ResultSPIDataTask(String rollNumber) {
            mRollNumber = rollNumber;
        }

        @Override
        protected JSONArray doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Result SPI
                final String baseSPIUrl = "http://nirma.byethost7.com/sa3/task_manager/v1/resultspi?roll_no=" + mRollNumber;
                URL url = new URL(baseSPIUrl);
                URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
                url = uri.toURL();

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                int responseCode = urlConnection.getResponseCode();

                if (responseCode >= 401) {
                    Log.v("ABC", "Bad response" + responseCode);
                }

                Log.v("ABC", "Above input stream");
                InputStream inputStream = urlConnection.getInputStream();
                Log.v("ABC", "Below input stream");
                StringBuffer buffer = new StringBuffer();

                if (inputStream == null) {
                    return null;
                }

                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line;

                while ((line = bufferedReader.readLine()) != null) {

                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }

                mJSONStrSPI = buffer.toString();

                Log.v("String : ", mJSONStrSPI);
                Log.v("Length : ", "Length " + mJSONStrSPI.length());

            } catch (Exception e) {
                return null;
            }

            //Return from server here
            try{
                mJSONArray = new JSONArray("");
            }catch(Exception e){

            }
            return mJSONArray;
        }
    }

    //Result Grade AsyncTask
    public class ResultGradeDataTask extends AsyncTask<Void, Void, Boolean> {

        public final String mRollNumber;
        public final String mSemester;

        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;

        String jsonStrSPI = null;

        ResultGradeDataTask(String rollNumber, String semester) {
            mRollNumber = rollNumber;
            mSemester = semester;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Result SPI
                final String baseGradeUrl = "http://nirma.byethost7.com/sa3/task_manager/v1/resultgrade?roll_no=" + mRollNumber + "&sem=" + mSemester;
                URL url = new URL(baseGradeUrl);
                URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
                url = uri.toURL();

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                if (inputStream == null) {
                    return null;
                }

                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line;

                while ((line = bufferedReader.readLine()) != null) {

                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }

                jsonStrSPI = buffer.toString();

                Log.v("String : ", jsonStrSPI);
                Log.v("Length : ", "Length " + jsonStrSPI.length());

            } catch (Exception e) {
                return false;
            }

            //Return from server here
            try {

            } catch (Exception e) {
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {

            if (success) {

            } else {

            }
        }
    }
}
