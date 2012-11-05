package com.xgxzatx.ardudroid.model;

import android.content.ContentValues;

import com.xgxzatx.ardudroid.model.support.Entity;

public class Lamp implements Entity {
	
	private long id;
	private String name;
	private boolean on;
	
	@Override
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isOn() {
		return on;
	}
	
	@Override
	public String getTableName() {
		return getClass().getSimpleName();
	}
	
	@Override
	public ContentValues toContentValues() {
		ContentValues values = new ContentValues();
		// TODO chave valor
		return values;
	}
}
