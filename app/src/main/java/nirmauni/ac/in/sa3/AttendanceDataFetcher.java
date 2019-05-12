package nirmauni.ac.in.sa3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

/**
 * Created by Dr. Death on 7/15/2015.
 */
public class AttendanceDataFetcher {

    public static final String DB_NAME = "SA3";
    public static final String TB_NAME = "Attendance";
    public static final String COL_COURSE_CODE = "CCode";
    public static final String COL_LECTURE_NO = "LecNo";
    public static final String COL_LECTURE_TYPE = "LecType";
    public static final String COL_LECTURE_YET = "LecYet";
    public static final String COL_DATE = "Date";
    public static final String COL_TIMESTAMP = "Timestamp";
    public static final int DB_VERSION = 2;

    public String mRollNumber;

    //Create Table Query
    public static final String TB_CREATE = "create table " + TB_NAME + " (" + COL_DATE + " text, " + COL_COURSE_CODE + " text, " + COL_LECTURE_NO + " text, " +  COL_LECTURE_TYPE + " text, " + COL_LECTURE_YET + " text, " + COL_TIMESTAMP + " text);";

    private SQLiteDatabase mDatabase;
    private DBController controller;

    private AttendanceDataTask mAttendanceDataTask;

    //Constructor
    public AttendanceDataFetcher(Context context){
        controller = new DBController(context, DB_NAME, null, DB_VERSION);
    }

    public class DBController extends SQLiteOpenHelper {

        //Constructor of Database Controller
        public DBController(Context context, String dbName, SQLiteDatabase.CursorFactory cursorFactory, int version) {
            super(context, dbName, cursorFactory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(TB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS" + TB_NAME);
            onCreate(db);
        }
    }

    public AttendanceDataFetcher open(String rollNumber) {
        mDatabase = controller.getWritableDatabase();
        mRollNumber = rollNumber;
        mAttendanceDataTask = new AttendanceDataTask(mRollNumber);
        mAttendanceDataTask.execute((Void) null);
        return this;
    }

    //Result AsyncTask
    public class AttendanceDataTask extends AsyncTask<Void, Void, Boolean> {

        public final String mRollNumber;

        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;

        String jsonStrSPI = null;
        String jsonStrGrade = null;

        AttendanceDataTask(String rollNumber) {
            mRollNumber = rollNumber;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Result SPI
                final String baseSPIUrl = "http://nirma.byethost7.com/sa3/task_manager/v1/attendance?roll_no=" + mRollNumber;
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
