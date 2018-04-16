package arsi0001.hs_kl.androfirequiz;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import arsi0001.hs_kl.androfirequiz.Model.SliderAdapter;

public class Startseite extends AppCompatActivity {

    private ViewPager _slideViewPager;
    private LinearLayout _punkteLayout;

    private SliderAdapter sliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startseite);

        /*_slideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        _punkteLayout = (LinearLayout) findViewById(R.id.punkteLayout);

        sliderAdapter = new SliderAdapter(this);

        _slideViewPager.setAdapter(sliderAdapter);*/
    }
}
