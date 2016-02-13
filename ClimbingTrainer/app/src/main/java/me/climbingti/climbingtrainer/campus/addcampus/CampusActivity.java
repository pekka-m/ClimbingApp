package me.climbingti.climbingtrainer.campus.addcampus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import me.climbingti.climbingtrainer.R;
import me.climbingti.climbingtrainer.common.Injection;


/**
 * Created by Pekka Melgin on 23.10.2015.
 */
public class CampusActivity extends AppCompatActivity implements AddCampusContract.View {

    private EditText editTextCampusSteps;
    private Button buttonAddRep;

    private AppCompatSpinner spinnerCampusType;
    private AddCampusContract.Presenter campusPresenter;


    public static Intent getCallingIntent(Context context){
        return new Intent(context, CampusActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.campus_activity);

        initViews();

        campusPresenter =
                new CampusPresenter(Injection.createCampusInteractor(this), this);

    }





    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void showDataBaseError() {
        this.createSnackBar("Database error.");
    }

    @Override
    public void showRepSaved() {
        this.createSnackBar("Rep added");
    }

    private void createSnackBar(String message){
        Snackbar.make((RelativeLayout) findViewById(R.id.campusActivity_rootView),
                message,
                Snackbar.LENGTH_SHORT).show();
    }

    private void initViews(){
        buttonAddRep = (Button) findViewById(R.id.campusActivity_button_addCampus);
        editTextCampusSteps = (EditText) findViewById(R.id.campusActivity_editText_campusSteps);
        spinnerCampusType = (AppCompatSpinner) findViewById(R.id.campusActivity_spinner_campusType);

        buttonAddRep.setOnClickListener(addCampus());

        editTextCampusSteps.addTextChangedListener(addHyphen());

        // prevents from selecting string from the middle
        editTextCampusSteps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextCampusSteps.setSelection(editTextCampusSteps.getText().length());
            }
        });

        // for when the user presses enter I thiink???
        editTextCampusSteps.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                campusPresenter.addNewCampusRep(editTextCampusSteps.getText().toString(),
                        spinnerCampusType.getSelectedItem().toString());
                return true;
            }
        });
    }

    private View.OnClickListener addCampus() {
        return new View.OnClickListener() {
            public void onClick(View v) {
                campusPresenter.addNewCampusRep(editTextCampusSteps.getText().toString(),
                        spinnerCampusType.getSelectedItem().toString());
            }
        };
    }

    private TextWatcher addHyphen(){
        return new TextWatcher() {
            boolean isFormatting;
            boolean deleting;
            int hyphenStart;
            boolean deletingBackward;

            @Override
            public void afterTextChanged(Editable text) {
                if(isFormatting){
                    return;
                }
                isFormatting = true;

                // jos poistetaan viiva niin poistetaan merkki sitä ennnen tai sen jälkeen
                if (deleting && hyphenStart > 0){
                    if (deletingBackward) {
                        if (hyphenStart - 1 < text.length()) {
                            text.delete(hyphenStart - 1, hyphenStart);
                        }
                    } else if (hyphenStart < text.length()) {
                        text.delete(hyphenStart, hyphenStart + 1);
                    }
                }
                // add hyphen if more than one char and not deleting
                if  (text.length() > 1 && !deleting) {
                    text.insert(text.length() - 1, "-");
                }

                isFormatting = false;
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (isFormatting) {
                    return;
                }

                // make sure user is deleting one char, without a selection
                final int selectionStart = Selection.getSelectionStart(s);
                final int selectionEnd = Selection.getSelectionEnd(s);
                if (s.length() > 1 // can delete another character
                        && count == 1 //deleting only one character
                        && after == 0 // deleting
                        && selectionStart == selectionEnd) { // no selection
                    deleting = true;
                    hyphenStart = start;
                    // check if user is deleting forward or backward
                    if (selectionStart == start + 1) {
                        deletingBackward = true;
                    } else {
                        deletingBackward = false;
                    }
                } else {
                    deleting = false;
                }
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        };
    }
}