package com.xgxzatx.ardudroid.model;

import java.util.List;

import android.content.ContentValues;

import com.xgxzatx.ardudroid.model.support.Entity;

public class Room implements Entity {

	private long id;
	private List<Lamp> lamps;
	
	@Override
	public long getId() {
		return id;
	}
	
	public List<Lamp> getLamps() {
		return lamps;
	}

	@Override
	public String getTableName() {
		return getClass().getSimpleName();
	}

	@Override
	public ContentValues toContentValues() {
		// TODO Auto-generated method stub
		return null;
	}

}
