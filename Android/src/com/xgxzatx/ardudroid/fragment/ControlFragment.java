package com.xgxzatx.ardudroid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.xgxzatx.ardudroid.ArduDroidApp;
import com.xgxzatx.ardudroid.R;

public class ControlFragment extends SherlockFragment {

	//private ArduDroidApp app;
	private SherlockFragmentActivity activity;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.activity = getSherlockActivity();
		//this.app = (ArduDroidApp) activity.getApplication();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_control_tab, container, false);
		
		return view;
	}
	
}
