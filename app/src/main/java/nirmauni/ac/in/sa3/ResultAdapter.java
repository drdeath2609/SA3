package nirmauni.ac.in.sa3;

import android.animation.ValueAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Priyesh on 6/12/2015.
 */
public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder>{

    private List<ResultInfo> mResultInfoList;
    private int mSemesterCount = 0;
    private int mSubjectCount = 0;

    public ResultAdapter(List<ResultInfo> resultInfoList) {
        this.mResultInfoList = resultInfoList;
    }

    @Override
    public int getItemCount(){
        return getSemesterCount();
    }

    public int getSemesterCount(){
        return mResultInfoList.size();
    }

    @Override
    public void onBindViewHolder(ResultViewHolder resultViewHolder, int position){

        ResultInfo mSemester = mResultInfoList.get(position);
        resultViewHolder.mSemester.setText("Semester " + mSemester.mSemesterNumber);
        resultViewHolder.mSPI.setText("SPI : " + mSemester.mSPI);
        resultViewHolder.mPPI.setText("PPI : " + mSemester.mPPI);
        for(int i=0;i < mSubjectCount;i++){
            resultViewHolder.mSubjectName.get(i).setText(mSemester.mSubjectName.get(i).toString());
            resultViewHolder.mSubjectCode.get(i).setText(mSemester.mSubjectCode.get(i).toString());
            resultViewHolder.mSubjectCredit.get(i).setText(mSemester.mSubjectCredits.get(i).toString());
            resultViewHolder.mSubjectGrade.get(i).setText(mSemester.mSubjectGrades.get(i).toString());
        }
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_result, viewGroup, false);

        ResultInfo resultInfo = mResultInfoList.get(mSemesterCount);
        mSubjectCount = resultInfo.getNumberOfSubject();

        return new ResultViewHolder(itemView, mSubjectCount);
    }


    //View Holders
    public static class ResultViewHolder extends RecyclerView.ViewHolder {
        protected TextView mSemester;
        protected TextView mSPI;
        protected TextView mPPI;
        protected TableLayout mTableLayout;

        protected ArrayList<TextView> mSubjectName;
        protected ArrayList<TextView> mSubjectCode;
        protected ArrayList<TextView> mSubjectCredit;
        protected ArrayList<TextView> mSubjectGrade;

        protected LinearLayout mCardExpandable;
        protected LinearLayout mCardHeader;

        public ResultViewHolder(View view, int subjectCount) {
            super(view);
            mSemester = (TextView) view.findViewById(R.id.resultCardHeader);
            mSPI = (TextView) view.findViewById(R.id.SPI);
            mPPI = (TextView) view.findViewById(R.id.PPI);
            mCardHeader = (LinearLayout) view.findViewById(R.id.cardHeader);
            mCardExpandable = (LinearLayout) view.findViewById(R.id.resultExpandable);

            mCardHeader.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mCardExpandable.getVisibility() == View.GONE)
                        expand();
                    else
                        collapse();
                }
            });

            mTableLayout = (TableLayout) view.findViewById(R.id.resultSemester);
            mSubjectName = new ArrayList<TextView>();
            mSubjectCode = new ArrayList<TextView>();
            mSubjectCredit = new ArrayList<TextView>();
            mSubjectGrade = new ArrayList<TextView>();

            //Creating expandable part of per Semester Result Card
            for (int i=0;i < subjectCount;i++){
                TableRow tableRow = new TableRow(view.getContext());
                TableRow.LayoutParams  layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
                tableRow.setLayoutParams(layoutParams);
                mSubjectName.add(new TextView(view.getContext()));
                mSubjectName.get(i).setPadding(15, 15, 15, 15);
                mSubjectCode.add(new TextView(view.getContext()));
                mSubjectCode.get(i).setPadding(15, 15, 15, 15);
                mSubjectCredit.add(new TextView(view.getContext()));
                mSubjectCredit.get(i).setPadding(15, 15, 15, 15);
                mSubjectGrade.add(new TextView(view.getContext()));
                mSubjectGrade.get(i).setPadding(15, 15, 15, 15);

                tableRow.addView(mSubjectName.get(i));
                tableRow.addView(mSubjectCode.get(i));
                tableRow.addView(mSubjectCredit.get(i));
                tableRow.addView(mSubjectGrade.get(i));
                mTableLayout.addView(tableRow);
            }
        }

        public void expand()
        {
            mCardExpandable.setVisibility(View.VISIBLE);

            final int widthSpec = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
            final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

            mCardExpandable.measure(widthSpec, heightSpec);

            ValueAnimator mAnimator = slideAnimator(0, mCardExpandable.getMeasuredHeight());
            mAnimator.start();
        }

        public void collapse()
        {
            int finalHeight = mCardExpandable.getHeight();
            ValueAnimator mAnimator = slideAnimator(finalHeight, 0);

            mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    mCardExpandable.setVisibility(View.GONE);
                }
            });
            mAnimator.start();
        }

        //Animator for Card expand and collapse
        private ValueAnimator slideAnimator(int start, int end)
        {
            ValueAnimator animator = ValueAnimator.ofInt(start, end);

            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int value = (Integer) valueAnimator.getAnimatedValue();

                    ViewGroup.LayoutParams layoutParams = mCardExpandable.getLayoutParams();

                    layoutParams.height = value;
                    mCardExpandable.setLayoutParams(layoutParams);
                }
            });

            return animator;
        }

    }

}
