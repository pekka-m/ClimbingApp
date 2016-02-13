package me.climbingti.climbingtrainer.settings;

/**
 * Created by Aleksi on 1.1.2016.
 * in me.climbingti.climbingtrainer.settings
 */
public interface SettingsContract {

    interface View {
        void showGradingScale(int scale);

        void showCampusSpacing(int spacing);

        void showMeasurementUnits();

        void showGoogleFitSyncState();
    }

    interface Presenter {

        void setGradingScale(int scale);

        void setCampusSpacing(int spacing);

        void setMeasurementUnits();

        void setGoogleFitSync();

        void loadGradingScale();

        void loadCampusSpacing();

        void loadMeasurementUnits();

        void loadGoogleFitSyncState();


    }
}
