package com.example.rohitmishra.sa3;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;


public class WelcomeSplashScreen extends ActionBarActivity implements View.OnClickListener{

    private RelativeLayout welcomeFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_splash_screen);
        welcomeFrame=(RelativeLayout)findViewById(R.id.welcomeFrame);
        welcomeFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signIn=new Intent(WelcomeSplashScreen.this,SignIn.class);
                startActivity(signIn);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome_splash_screen, menu);
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

    }
}
