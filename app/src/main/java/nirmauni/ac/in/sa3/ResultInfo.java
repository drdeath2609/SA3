package nirmauni.ac.in.sa3;

import java.util.ArrayList;

/**
 * Created by Priyesh on 6/11/2015.
 *
 * Each object of ResultInfo is an information of each Semester
 */
public class ResultInfo {
    protected String mSemesterNumber;                             //Semester Number
    protected String mSPI;                                  //Semester SPI
    protected String mPPI;                                  //PPI after Semester Number
    protected ArrayList<String> mSubjectName;                //Names od Subject in Semester Number
    protected ArrayList<String> mSubjectCode;               //Code of Subject in Semester Number
    protected ArrayList<String> mSubjectCredits;            //Credits of Subject in Semester Number
    protected ArrayList<String> mSubjectGrades;             //Grades of Subject in Semester Number

    protected ResultInfo(String semesterNumber, String SPI, String PPI, ArrayList<String> SubjectName, ArrayList<String> SubjectCode, ArrayList<String> SubjectCredits, ArrayList<String> SubjectGrades){
        this.mSemesterNumber = semesterNumber;
        this.mSPI = SPI;
        this.mPPI = PPI;
        this.mSubjectName = SubjectName;
        this.mSubjectCode = SubjectCode;
        this.mSubjectCredits = SubjectCredits;
        this.mSubjectGrades = SubjectGrades;
    }

    protected ResultInfo(){

    }

    public int getNumberOfSubject(){
        return mSubjectName.size();
    }

    public String getSubjectCode(String subjectName){
        return mSubjectCode.get((this.mSubjectName).indexOf(subjectName));
    }

    public String getSubjectName(String subjectCode){
        return mSubjectName.get((this.mSubjectCode).indexOf(subjectCode));
    }
}
