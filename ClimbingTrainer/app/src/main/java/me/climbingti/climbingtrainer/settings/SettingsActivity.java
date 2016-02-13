package me.climbingti.climbingtrainer.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import me.climbingti.climbingtrainer.R;
import me.climbingti.climbingtrainer.common.Injection;

/**
 * Created by Aleksi on 15.11.2015.
 */
public class SettingsActivity extends AppCompatActivity implements SettingsContract.View {

    private SettingsContract.Presenter presenter;
    private Spinner gradingScaleSpinner;
    private Spinner campusSpacingSpinner;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, SettingsActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        gradingScaleSpinner = (Spinner) findViewById(R.id.settings_Spinner_gradingScale);
        gradingScaleSpinner.setOnItemSelectedListener(gradingScaleSpinnerListener());

        campusSpacingSpinner = (Spinner) findViewById(R.id.settings_Spinner_campusSpacing);
        campusSpacingSpinner.setOnItemSelectedListener(campusSpacingSpinnerListener());


        presenter = new SettingsPresenter(Injection.createSettingsInteractor(this), this);
        this.loadSetting();
    }


    private void loadSetting(){
        presenter.loadGradingScale();
        presenter.loadCampusSpacing();
        presenter.loadMeasurementUnits();
        presenter.loadGoogleFitSyncState();
    }

    @Override
    public void showGradingScale(int scale) {
        gradingScaleSpinner.setSelection(scale);
    }

    @Override
    public void showCampusSpacing(int spacing) {
        campusSpacingSpinner.setSelection(spacing);
    }

    @Override
    public void showMeasurementUnits() {
    }

    @Override
    public void showGoogleFitSyncState() {
    }



    private AdapterView.OnItemSelectedListener gradingScaleSpinnerListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.setGradingScale(gradingScaleSpinner.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }

    private AdapterView.OnItemSelectedListener campusSpacingSpinnerListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.setCampusSpacing(campusSpacingSpinner.getSelectedItemPosition());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }
}