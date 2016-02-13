package me.climbingti.climbingtrainer.practice.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import me.climbingti.climbingtrainer.R;

/**
 * Created by H4198 on 17.11.2015.
 */
public class PracticeDetailActivity extends AppCompatActivity {

    private TabLayout tabs;
    private ViewPager viewPager;
    private PracticeDetailActivityPagerAdapter practiceDetailActivityPagerAdapter;
    private static final String PRACTICE_ID ="practice_id";


    public static Intent getCallingIntent(Context context, long practiceId){
        Intent callingIntent = new Intent(context, PracticeDetailActivity.class);
        Bundle args = new Bundle();
        args.putLong(PRACTICE_ID, practiceId);
        callingIntent.putExtras(args);
        return callingIntent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practice_details_activity);
        setupTabLayout();
        setupViewPager();
    }

    private void setupViewPager() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void setupTabLayout() {
        tabs = (TabLayout) findViewById(R.id.practiceDetailActivity_tabLayout);
        viewPager = (ViewPager) findViewById(R.id.practiceDetailActivity_viewPager);
        viewPager.setOffscreenPageLimit(4);
        practiceDetailActivityPagerAdapter =
                new PracticeDetailActivityPagerAdapter(getSupportFragmentManager(), (int)getPracticeIdFromIntent());
        viewPager.setAdapter(practiceDetailActivityPagerAdapter);
        tabs.setupWithViewPager(viewPager);
    }

    private long getPracticeIdFromIntent(){
        Intent intent = getIntent();
        return intent.getLongExtra(PRACTICE_ID,0);
    }
}