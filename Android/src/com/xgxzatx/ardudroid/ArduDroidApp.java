package com.xgxzatx.ardudroid;

import android.app.Application;

public class ArduDroidApp extends Application {

	private String nome;
	
	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	public String getNome() {
		return nome;
	}
	
}
