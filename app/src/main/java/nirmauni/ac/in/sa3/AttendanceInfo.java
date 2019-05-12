package nirmauni.ac.in.sa3;

import java.util.ArrayList;

/**
 * Created by Rohit Mishra on 10-Jun-2015.
 */
public class AttendanceInfo {
    protected String date;
    protected String subjectCode;
    protected String lectureNumber;
    protected String lectureType;
    protected String timeStamp;
    protected float percentage;
    protected int subjectColor;
    protected int numberoflectures=15;
    public int colorpalette[]={
            R.color.md_teal_300,
            R.color.md_red_300,
            R.color.md_blue_300,
            R.color.md_amber_300,
            R.color.md_green_300,
            R.color.md_deep_purple_300,
            R.color.md_deep_orange_300,
            R.color.md_deep_purple_600,
            R.color.md_yellow_900,
            R.color.md_red_900};

    public AttendanceInfo()
    {

    }
    public AttendanceInfo(String date,String subjectCode,String lectureNumber,String lectureType,String timeStamp)
    {
        this.date=date;
        this.subjectCode=subjectCode;
        this.lectureNumber=lectureNumber;
        this.lectureType=lectureType;
        this.timeStamp=timeStamp;
    }
    public int Count(String subjectCode,String lectureType)
    {
        int count=0;
        AttendanceInfo ai=new AttendanceInfo();
        ArrayList<AttendanceInfo> data=ai.dummydata();
        for(int i=0;i<data.size();i++)
            if(data.get(i).subjectCode.equals(subjectCode) && data.get(i).lectureType.equals(lectureType))
                count++;
        return count;
    }

    public ArrayList<AttendanceInfo> dummydata()
    {
        ArrayList<AttendanceInfo> data=new ArrayList<AttendanceInfo>();

        data.add(new AttendanceInfo("10-05-2015","2IT309","3","l","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("12-07-2015","2IT322","2","l","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("16-04-2015","2IT309","4","l","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("14-02-2015","2IT322","5","l","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("19-02-2015","2IT322","1","l","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("10-07-2015","2IT322","7","l","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("15-09-2015","2HS013","5","l","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("12-06-2015","2IT309","8","l","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("11-04-2015","2IT322","5","l","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("15-09-2015","2CE323","2","l","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("16-08-2015","2CE323","4","l","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("14-03-2015","2CE415","1","l","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("12-07-2015","2CE323","1","l","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("10-04-2015","2CE415","7","l","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("13-01-2015","2IT337","4","l","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("15-09-2015","2CE323","2","t","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("16-08-2015","2CE323","4","la","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("14-03-2015","2CE415","1","t","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("12-07-2015","2CE323","1","t","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("10-04-2015","2CE415","7","la","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("13-01-2015","2IT337","4","la","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("10-05-2015","2IT309","3","l","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("12-07-2015","2IT322","2","l","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("16-04-2015","2IT309","4","l","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("14-02-2015","2IT322","5","l","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("19-02-2015","2IT322","1","l","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("10-07-2015","2IT322","7","l","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("15-09-2015","2HS013","5","l","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("15-07-2015","2HS013","5","l","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("12-06-2015","2IT309","8","l","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("11-04-2015","2IT322","5","l","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("15-09-2015","2CE323","2","l","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("16-08-2015","2CE323","4","l","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("14-03-2015","2CE415","1","l","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("12-07-2015","2CE323","1","l","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("10-04-2015","2CE415","7","l","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("13-01-2015","2IT337","4","l","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("15-09-2015","2CE323","2","t","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("16-08-2015","2CE323","4","la","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("14-03-2015","2CE415","1","t","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("12-07-2015","2CE323","1","t","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("10-04-2015","2CE415","7","la","2015-05-29 01:21:36"));
        data.add(new AttendanceInfo("13-01-2015","2IT337","4","la","2015-05-29 01:21:36"));

        return data;
    }
}