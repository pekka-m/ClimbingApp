package me.climbingti.climbingtrainer.mainview;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.NumberPicker;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.gordonwong.materialsheetfab.MaterialSheetFab;
import com.gordonwong.materialsheetfab.MaterialSheetFabEventListener;

import me.climbingti.climbingtrainer.R;
import me.climbingti.climbingtrainer.bouldering.ClimbPresenter;
import me.climbingti.climbingtrainer.bouldering.ClimbView;
import me.climbingti.climbingtrainer.common.view.Fab;
import me.climbingti.climbingtrainer.common.Navigator;
import me.climbingti.climbingtrainer.settings.navBarListener;


public class MainActivity extends AppCompatActivity implements
        ClimbView<Void> {
    // tabLayout
    private MainActivityPagerAdapter mainActivityPagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabs;

    //floating action button
    private Fab fab;
    private MaterialSheetFab morphedFab;
    private int statusBarColor;

    //bottomsheet
    private BottomSheetLayout bottomSheet;
    private AppCompatButton addNewClimbButton;
    private NumberPicker gradePicker;

    //toolbar
    private Toolbar toolbar;
    private android.support.v7.app.ActionBar actionBar;

    //nav drawer
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    //other
    private Navigator navigator;
    private ClimbPresenter climbPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        climbPresenter = new ClimbPresenter(this);
        navigator = new Navigator();
        setupDrawerLayout();
        setupToolbar();
        setupTabLayout();
        setupFab();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //enables back pressing to close the menu
    @Override
    public void onBackPressed(){
        if(morphedFab.isSheetVisible()){
            morphedFab.hideSheet();
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public void setData(Void param) { }
    @Override
    public void onResult(String result) {}

    private Context getContext(){return this;}

    private void setupTabLayout(){
        tabs = (TabLayout) findViewById(R.id.mainActivity_tabLayout);
        viewPager = (ViewPager) findViewById(R.id.mainActivity_ViewPager);
        viewPager.setOffscreenPageLimit(4);
        mainActivityPagerAdapter = new MainActivityPagerAdapter(getSupportFragmentManager());
        setupViewPager();
        tabs.setupWithViewPager(viewPager);
    }

    private void setupDrawerLayout(){
        drawerLayout = (DrawerLayout) findViewById(R.id.mainActivity_DrawerLayout);
        navigationView = (NavigationView) findViewById(R.id.mainActivity_NavigationView);
        navigationView.setNavigationItemSelectedListener(new navBarListener(drawerLayout, this));
    }

    private void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.mainActivity_toolbar);
        setSupportActionBar(toolbar);
         actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupFab(){
        fab = (Fab) findViewById(R.id.mainActivity_floatingActionButton);
        View sheetView = findViewById(R.id.mainActivity_cardView_morphedFab);
        View overlay = findViewById(R.id.mainActivity_dimOverlayFrameLayout);
        int sheetColor = ContextCompat.getColor(this,R.color.white);
        int fabColor = ContextCompat.getColor(this,R.color.colorAccent);
        morphedFab = new MaterialSheetFab<>(fab, sheetView, overlay, sheetColor, fabColor);

        morphedFab.setEventListener(new MaterialSheetFabEventListener() {
            @Override
            public void onShowSheet() {
                statusBarColor = getStatusBarColor();
                //might crash
                setStatusBarColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
            }

            @Override
            public void onHideSheet() {
                setStatusBarColor(statusBarColor);
                super.onHideSheet();
            }
        });

        findViewById(R.id.mainActivity_textView_morphedFab_climbTitle).setOnClickListener(indoorClimb());
        findViewById(R.id.mainActivity_textView_morphedFab_hangboardTitle).setOnClickListener(launchHangboardActivity());
        findViewById(R.id.mainActivity_textView_morphedFab_campusTitle).setOnClickListener(launchCampusActivity());
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
            public void onPageScrollStateChanged(int state) { }
        });
        viewPager.setAdapter(mainActivityPagerAdapter);
    }

    private int getStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return getWindow().getStatusBarColor();
        }
        return 0;
    }
    private void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(color);
        }
    }

    private View.OnClickListener indoorClimb() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                bottomSheet = (BottomSheetLayout) findViewById(R.id.mainActivity_BottomSheet);
                morphedFab.hideSheet();
                bottomSheet.showWithSheetView(LayoutInflater.from(getBaseContext())
                        .inflate(R.layout.main_bottomsheet, bottomSheet, false));
                addNewClimbButton = (AppCompatButton)
                        bottomSheet.findViewById(R.id.bottomsheet_appCompatButton_StoreClimb);

                gradePicker = (NumberPicker)
                        bottomSheet.findViewById(R.id.bottomsheet_NumberPicker_Grades);
                String[] grades= getResources().getStringArray(climbPresenter.getGradingScale());
                gradePicker.setMinValue(0);
                gradePicker.setMaxValue(grades.length - 1);
                gradePicker.setDisplayedValues(grades);
                gradePicker.setValue(3);

                addNewClimbButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Snackbar
                                .make(v, "Climb added", Snackbar.LENGTH_SHORT)
                                .setAction("action", null)
                                .show();
                        Log.d("MainActivity", "onClick: gradepicker value: " + gradePicker.getValue());
                        climbPresenter.add(gradePicker.getValue());
                        bottomSheet.dismissSheet();
                    }
                });

            }
        };
    }

    private void navigateToCampus(){
        navigator.navigateToCampusActivity(this);
    }
    private void navigateToHangboard(){
        navigator.navigateToHangboardActivity(this);
    }
    private View.OnClickListener launchCampusActivity(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                morphedFab.hideSheet();
                navigateToCampus();
            }
        };
    }
    private View.OnClickListener launchHangboardActivity(){
        return new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                morphedFab.hideSheet();
               navigateToHangboard();
            }
        };
    }
}