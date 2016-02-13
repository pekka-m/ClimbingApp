package me.climbingti.climbingtrainer.settings;

/**
 * Created by Aleksi on 15.11.2015.
 */
public class SettingsPresenter implements SettingsContract.Presenter {

    private SettingsInteractor settingsInteractor;
    private SettingsContract.View view;

    public SettingsPresenter(SettingsInteractor settingsInteractor, SettingsContract.View view) {
        this.settingsInteractor = settingsInteractor;
        this.view = view;
    }

    @Override
    public void setGradingScale(int scale) {
        settingsInteractor.switchGradingScale(scale);
    }

    @Override
    public void setCampusSpacing(int spacing) {
        settingsInteractor.switchCampusSpacing(spacing);
    }

    @Override
    public void setMeasurementUnits() {
        settingsInteractor.switchMeasurementUnits();
    }

    @Override
    public void setGoogleFitSync() {
        settingsInteractor.setGoogleFitSync();
    }

    @Override
    public void loadGradingScale() {
        int gradingScale = settingsInteractor.getGradingScale();
        this.view.showGradingScale(gradingScale);
    }

    @Override
    public void loadCampusSpacing() {
        int campusSpacing =         settingsInteractor.getCampusSpacing();
        this.view.showCampusSpacing(campusSpacing);

    }

    @Override
    public void loadMeasurementUnits() {

        settingsInteractor.getMeasurementUnits();
        this.view.showMeasurementUnits();
    }

    @Override
    public void loadGoogleFitSyncState() {
        settingsInteractor.getGoogleFitSyncState();
        this.view.showGoogleFitSyncState();
    }
}