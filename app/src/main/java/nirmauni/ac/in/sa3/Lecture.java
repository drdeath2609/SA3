package nirmauni.ac.in.sa3;

/**
 * Created by Rohit Mishra on 03-Jun-2015.
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;
import java.util.List;

public class Lecture extends Fragment {

    private ArrayList<String> listOfSubjects=new ArrayList<String>();
    public ArrayList<PieChart> chartReferences=new ArrayList<PieChart>();
    private boolean isViewShown = false;
    private boolean visibility = false;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.lecture,container,false);

        listOfSubjects.add("2IT309");
        listOfSubjects.add("2IT322");
        listOfSubjects.add("2CE415");
        listOfSubjects.add("2CE323");
        listOfSubjects.add("2IT337");
        listOfSubjects.add("2HS013");

        RecyclerView recList = (RecyclerView)v.getRootView().findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        AttendanceAdapter ca = new AttendanceAdapter(createList(listOfSubjects,v),"l");
        recList.setAdapter(ca);

        if (!isViewShown && visibility) {
            animateCharts();
        }

        return v;
    }

    private List<AttendanceInfo> createList(ArrayList<String> listOfSubjects,View v) {

        ChartAdapter adapter=new ChartAdapter(getActivity());
        RelativeLayout rl=(RelativeLayout)v.getRootView().findViewById(R.id.chartview);

        List<AttendanceInfo> attendance = new ArrayList<AttendanceInfo>();
        int numberOfSubjects=listOfSubjects.size();
        int smallestRadius=100-(numberOfSubjects*5);
        for (int i=0;i<listOfSubjects.size();i++) {
            AttendanceInfo att_info = new AttendanceInfo();
            att_info.subjectCode=listOfSubjects.get(i);
            att_info.subjectColor=getResources().getColor(att_info.colorpalette[i]);
            att_info.percentage=((float)(att_info.numberoflectures-att_info.Count(att_info.subjectCode,"l"))/att_info.numberoflectures)*100;
            chartReferences.add(adapter.createNewChart(v.getRootView(), rl, smallestRadius + (i * 5), att_info.colorpalette[i], att_info.percentage));
            attendance.add(att_info);
        }
        chartReferences.add(adapter.createNewChart(v.getRootView(), rl, 100, R.color.background_material_light, 100)); // Blank Chart to hide default legend
        return attendance;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            visibility=true;
            for(int i=0;i<chartReferences.size();i++) {
                chartReferences.get(i).setVisibility(View.VISIBLE);
                chartReferences.get(i).animateY((int) (Math.random() * 1000) + 1500);
            }
        }
        if (!isVisibleToUser) {
            visibility=false;
            for(int i=0;i<chartReferences.size();i++) {
                chartReferences.get(i).setVisibility(View.GONE);
            }
        }
        if (getView() != null)
            isViewShown = true;
        else
            isViewShown = false;
    }

    public void animateCharts()
    {
        for(int i=0;i<chartReferences.size();i++) {
            chartReferences.get(i).setVisibility(View.VISIBLE);
            chartReferences.get(i).animateY((int) (Math.random() * 1000) + 1500);
        }
    }
}