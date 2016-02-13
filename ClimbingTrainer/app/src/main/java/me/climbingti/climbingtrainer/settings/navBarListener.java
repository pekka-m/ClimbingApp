package me.climbingti.climbingtrainer.settings;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import me.climbingti.climbingtrainer.R;
import me.climbingti.climbingtrainer.common.Navigator;

/**
 * Created by H3173 on 17.11.2015.
 */
public class navBarListener implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Navigator navigator;
    private Context context;

    public navBarListener(DrawerLayout drawerLayout, Context context) {
        this.drawerLayout = drawerLayout;
        this.context = context;
        navigator = new Navigator();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int selectedMenuItem = menuItem.getItemId();
        itemSelection(selectedMenuItem);
        return false;
    }

    private void itemSelection(int item) {
        switch (item) {
            case R.id.menu_hangboardSessionEditor:
                navigator.navigateToHangboardEditorActivity(context);
                break;
            case R.id.menu_history:
                navigator.navigateToOverviewActivity(context);
                break;
            case R.id.menu_details:
                navigator.navigateToPracticeBrowserActivity(context);
                break;
            case R.id.menu_settings:
                navigator.navigateToSettings(context);
                break;
            case R.id.menu_about:
                break;
            case R.id.menu_debug_detail:
                // 0 is for testing, replace with actual practice id if you want to see data
                navigator.navigateToPracticeDetailActivity(context, 0);
            default:
                break;
        }
        drawerLayout.closeDrawers();
    }
}

