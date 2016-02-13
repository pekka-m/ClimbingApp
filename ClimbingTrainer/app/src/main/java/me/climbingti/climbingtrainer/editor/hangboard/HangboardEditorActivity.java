package me.climbingti.climbingtrainer.editor.hangboard;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by H3173 on 19.11.2015.
 */
public class HangboardEditorActivity extends AppCompatActivity {
    public static Intent getCallingIntent(Context context){
        Intent callingIntent = new Intent(context, HangboardEditorActivity.class);
        return callingIntent;
    }
}
