package nirmauni.ac.in.sa3;

/**
 * Created by Rohit Mishra on 03-Jun-2015.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class Summary extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.summary,container,false);
        TableLayout attendanceMaster=(TableLayout)v.findViewById(R.id.attendanceMaster);
        AttendanceInfo ai=new AttendanceInfo();
        ArrayList<AttendanceInfo> data=ai.dummydata();

        for(int i=0;i<data.size();i++) {
            LayoutInflater li = (LayoutInflater) v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = li.inflate(R.layout.tablerowsummary, null);
            TableRow newDataRow = (TableRow) rowView.findViewById(R.id.row);
            TextView date = (TextView) rowView.findViewById(R.id.date);
            TextView lecturenumber = (TextView) rowView.findViewById(R.id.lecturenumber);
            TextView lecturetype = (TextView) rowView.findViewById(R.id.lecturetype);
            TextView subjectcode = (TextView) rowView.findViewById(R.id.subjectcode);
            date.setText(data.get(i).date);
            lecturenumber.setText(data.get(i).lectureNumber);
            lecturetype.setText(data.get(i).lectureType);
            subjectcode.setText(data.get(i).subjectCode);
            attendanceMaster.startAnimation(AnimationUtils.loadAnimation(v.getContext(), android.R.anim.slide_in_left));
            attendanceMaster.addView(newDataRow);
        }

        return v;
    }
}