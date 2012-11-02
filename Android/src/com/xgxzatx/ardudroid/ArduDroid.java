package com.xgxzatx.ardudroid;

import android.content.Intent;
import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.xgxzatx.ardudroid.about.AboutActivity;
import com.xgxzatx.ardudroid.listener.ArduDroidTabListener.Tag;
import com.xgxzatx.ardudroid.listener.ArduDroidTabListener;
import com.xgxzatx.ardudroid.setting.SettingsActivity;

public class ArduDroid extends SherlockFragmentActivity {
	
	private int selectedTab = 0;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ardudroid);
        createTabs(selectedTab);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.ardudroid, menu);
        return true;
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.menu_about) {
			Intent intent = new Intent(this, AboutActivity.class);
			startActivity(intent);
		}
		if (item.getItemId() == R.id.menu_settings) {
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	private void createTabs(int selectedTab) {

		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		TabListener tabListener = new ArduDroidTabListener(this);

		Tab tab1 = getSupportActionBar().newTab().setTag(Tag.MONITOR)
				.setText(getString(R.string.monitor))
				.setTabListener(tabListener);
		getSupportActionBar().addTab(tab1, false);

		Tab tab2 = getSupportActionBar().newTab().setTag(Tag.CONTROL)
				.setText(getString(R.string.control))
				.setTabListener(tabListener);
		getSupportActionBar().addTab(tab2, false);
		
		getSupportActionBar().getTabAt(selectedTab).select();

		setSupportProgressBarIndeterminateVisibility(false);
		setSupportProgressBarVisibility(false);
	}
}
