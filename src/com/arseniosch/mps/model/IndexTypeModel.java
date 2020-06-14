package com.arseniosch.mps.model;

public class IndexTypeModel {

	private int index;
	private String type;

	public IndexTypeModel(int anIndex, String aType) {
		this.index = anIndex;
		this.type = aType;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
