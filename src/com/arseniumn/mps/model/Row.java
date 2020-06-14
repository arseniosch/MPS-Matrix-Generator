package com.arseniumn.mps.model;

public class Row {

	private String MPSValue, TypeConstraint;

	public Row(String anMPSValue, String aTypeConstraint) {
		this.MPSValue = anMPSValue;
		this.TypeConstraint = aTypeConstraint;
	}

	public String getMPSValue() {
		return MPSValue;
	}

	public String getTypeConstraint() {
		return TypeConstraint;
	}
}
