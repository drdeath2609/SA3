package com.example.rohitmishra.sa3;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class HomePage extends ActionBarActivity implements View.OnClickListener{

    private Button timeTable,attendance,results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        timeTable=(Button)findViewById(R.id.timeTable);
        attendance=(Button)findViewById(R.id.attendance);
        results=(Button)findViewById(R.id.results);
        timeTable.setOnClickListener(this);
        attendance.setOnClickListener(this);
        results.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_page, menu);
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

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.timeTable)
        {
            Intent timeTable=new Intent(HomePage.this,TimeTable.class);
            startActivity(timeTable);
        }
        if(v.getId()==R.id.attendance)
        {
            Intent attendance=new Intent(HomePage.this,Attendance.class);
            startActivity(attendance);
        }
        if(v.getId()==R.id.results)
        {
            Intent results=new Intent(HomePage.this,Results.class);
            startActivity(results);
        }

    }
}
