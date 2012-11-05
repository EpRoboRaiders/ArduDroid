package com.xgxzatx.ardudroid.listener;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.xgxzatx.ardudroid.R;
import com.xgxzatx.ardudroid.fragment.ControlFragment;
import com.xgxzatx.ardudroid.fragment.MonitorFragment;

public class ArdudroidTabListener implements ActionBar.TabListener {
	
	private final SherlockFragmentActivity activity;
	
	private SherlockFragment fragment;
	
	public static enum Tag {
		MONITOR, CONTROL;
	}

	public ArdudroidTabListener(SherlockFragmentActivity activity) {
		this.activity = activity;
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		Tag tag = (Tag) tab.getTag();
		
		FragmentManager manager = activity.getSupportFragmentManager();
		fragment = (SherlockFragment) manager.findFragmentByTag(tag.toString());
		
		if(fragment == null) {
			switch(tag){
			case MONITOR:
				fragment = new MonitorFragment();
				break;
			case CONTROL:
				fragment = new ControlFragment();
				break;
			}
			ft.add(R.id.ardudroid_main_content, fragment, tag.toString());
		} else {
			ft.attach(fragment);
		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		Log.d("APPLICATION", "Nothing to do here!");
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		if(fragment != null) {
			ft.detach(fragment);
		}		
	}
}
