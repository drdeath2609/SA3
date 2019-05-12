package results.mis.itnu.ac.in.sa3;

import android.animation.ValueAnimator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    LinearLayout mLinearLayout[] = new LinearLayout[2];
    LinearLayout mLinearLayoutHeader[] = new LinearLayout[2];

    ArrayList<String> mSubject = new ArrayList<String>();
    ArrayList<String> mCode = new ArrayList<String>();
    ArrayList<String> mGrade = new ArrayList<String>();
    ArrayList<String> mCredits = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLinearLayout[0] = (LinearLayout) findViewById(R.id.expandableSemOne);
        mLinearLayout[1] = (LinearLayout) findViewById(R.id.expandableSemTwo);
        mLinearLayout[0].setVisibility(View.GONE);
        mLinearLayout[1].setVisibility(View.GONE);

        mLinearLayoutHeader[0] = (LinearLayout) findViewById(R.id.headerSemOne);
        mLinearLayoutHeader[1] = (LinearLayout) findViewById(R.id.headerSemTwo);

        mLinearLayoutHeader[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mLinearLayout[0].getVisibility() == View.GONE)
                    expand(0);
                else
                    collapse(0);
            }
        });

        mLinearLayoutHeader[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mLinearLayout[1].getVisibility() == View.GONE)
                    expand(1);
                else
                    collapse(1);
            }
        });

        initialise();
        initRows();

    }

    public void initialise()
    {
        //Subject
        mSubject.add("OOSE");
        mSubject.add("MC");
        mSubject.add("DIP");
        mSubject.add("NP");
        mSubject.add("Acc");
        mSubject.add("DOS");

        mSubject.add("CG");
        mSubject.add("WT");
        mSubject.add("MIS");
        mSubject.add("CN");
        mSubject.add("TAFL");
        mSubject.add("OPT");
        //Code
        mCode.add("2CE323");
        mCode.add("2CE440");
        mCode.add("2IT327");
        mCode.add("2IT309");
        mCode.add("2HS003");
        mCode.add("2IT322");

        mCode.add("2CE321");
        mCode.add("2IT302");
        mCode.add("2IT303");
        mCode.add("2IT321");
        mCode.add("2IT413");
        mCode.add("2IT308");
        //mCredits
        mCredits.add("5");
        mCredits.add("4");
        mCredits.add("3");
        mCredits.add("4");
        mCredits.add("3");
        mCredits.add("4");

        mCredits.add("4");
        mCredits.add("4");
        mCredits.add("3");
        mCredits.add("4");
        mCredits.add("3");
        mCredits.add("3");


        //mGrades
        mGrade.add("B+");
        mGrade.add("A");
        mGrade.add("B");
        mGrade.add("B+");
        mGrade.add("A");
        mGrade.add("B");

        mGrade.add("B+");
        mGrade.add("A");
        mGrade.add("C+");
        mGrade.add("C");
        mGrade.add("IF(S)");
        mGrade.add("A+");
    }

    public void initRows()
    {
        TableLayout mTableLayout[] = new TableLayout[2];
        mTableLayout[0] = (TableLayout)findViewById(R.id.semOne);
        mTableLayout[1] = (TableLayout)findViewById(R.id.semTwo);

        TextView temp_1, temp_2, temp_3, temp_4;
        int j=0, temp=0;
        for (int i=0;i < 2;i++)
        {
            for (j=6*(i);j < 6*(i+1);j++)
            {
                TableRow mRow = new TableRow(this);
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
                mRow.setLayoutParams(layoutParams);
                temp_1 = new TextView(this);
                temp_1.setPadding(40, 20, 40, 20);
                temp_1.setText(mSubject.get(j));
                temp_2 = new TextView(this);
                temp_2.setPadding(40, 20, 40, 20);
                temp_2.setText(mCode.get(j));
                temp_3 = new TextView(this);
                temp_3.setPadding(40, 20, 40, 20);
                temp_3.setText(mCredits.get(j));
                temp_4 = new TextView(this);
                temp_4.setPadding(40, 20, 40, 20);
                temp_4.setText(mGrade.get(j));
                mRow.addView(temp_1);
                mRow.addView(temp_2);
                mRow.addView(temp_3);
                mRow.addView(temp_4);
                mTableLayout[i].addView(mRow, (j+1)-(6*i));
            }
        }
    }

    public void expand(int i)
    {
        mLinearLayout[i].setVisibility(View.VISIBLE);

        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

        mLinearLayout[i].measure(widthSpec, heightSpec);

        ValueAnimator mAnimator = slideAnimator(0, mLinearLayout[i].getMeasuredHeight(), i);
        mAnimator.start();
    }

    public void collapse(int j)
    {
        final int i = j;
        int finalHeight = mLinearLayout[i].getHeight();
        ValueAnimator mAnimator = slideAnimator(finalHeight, 0, i);

        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mLinearLayout[i].setVisibility(View.GONE);
            }
        });
        mAnimator.start();
    }

    private ValueAnimator slideAnimator(int start, int end, int j)
    {
        final int i = j;
        ValueAnimator animator = ValueAnimator.ofInt(start,end);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (Integer) valueAnimator.getAnimatedValue();

                ViewGroup.LayoutParams layoutParams = mLinearLayout[i].getLayoutParams();

                layoutParams.height = value;
                mLinearLayout[i].setLayoutParams(layoutParams);
            }
        });

        return animator;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
