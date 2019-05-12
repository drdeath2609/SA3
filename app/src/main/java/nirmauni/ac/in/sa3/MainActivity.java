package nirmauni.ac.in.sa3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.internal.app.ToolbarActionBar;
import android.support.v7.widget.Toolbar;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialAccountListener;

/**
 * Created by Priyesh on 6/9/2015.
 */
public class MainActivity extends MaterialNavigationDrawer {

    @Override
    public void init(Bundle savedInstanceState){

        //hbvdfhbviusbvkusfbv huihfuihfshdiufhbds shgfushgfushgfusjf
        //Common to all Activity having Navigation Drawer
        MaterialAccount account = new MaterialAccount(this.getResources(), "Dr. Death", "dr.death2609@gmail.com", R.drawable.lp_logo, R.drawable.lp);
        this.addAccount(account);

        Dashboard dashboard = new Dashboard();
        MaterialSection section = newSection("Dashboard", dashboard);
        this.addSection(section);

        this.addSubheader("Attendance");
            Lectures lectures = new Lectures();
            section = newSection("     Lecture", lectures);
            this.addSection(section);

            Practicals practicals = new Practicals();
            section = newSection("     Practicals", practicals);
            this.addSection(section);

            Tutorials tutorials = new Tutorials();
            section = newSection("     Tutorials", tutorials);
            this.addSection(section);
        this.addDivisor();
        Timetable timetable = new Timetable();
        section = newSection("Timetable", timetable);
        this.addSection(section);
        Result result = new Result();
        section = newSection("Result", result);
        this.addSection(section);

        this.setAccountListener(new MaterialAccountListener() {
            @Override
            public void onAccountOpening(MaterialAccount materialAccount) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }

            @Override
            public void onChangeAccount(MaterialAccount materialAccount) {

            }
        });
    }
}
