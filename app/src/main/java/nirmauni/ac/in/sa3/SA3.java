package nirmauni.ac.in.sa3;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Rohit Mishra on 19-Jun-2015.
 */
public class SA3 extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/Ubuntu-R.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
    }
}
