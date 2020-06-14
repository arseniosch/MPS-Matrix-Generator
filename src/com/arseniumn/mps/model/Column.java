package com.arseniumn.mps.model;

public class Column {

	private String variable, TypeConstraint, TypeObjConstraint;
	private double value, objCoefficient;
	
	public Column(String aVariable, String aTypeConstraint, String aValue, String aTypeObjConstraint, String anObjCoefficient) {
		this.variable = aVariable;
		this.TypeConstraint = aTypeConstraint;
		this.value = Double.parseDouble(aValue);
		this.TypeObjConstraint = aTypeObjConstraint;
		this.objCoefficient = Double.parseDouble(anObjCoefficient);
	}

	public Column(String aVariable, String aTypeConstraint, String aValue) {
		this.variable = aVariable;
		this.TypeConstraint = aTypeConstraint;
		this.value = Double.parseDouble(aValue);	
	}

	public String getVariable() {
		return this.variable;
	}
	public String getTypeConstraint() {
		return this.TypeConstraint;
	}

	public double getValue() {
		return this.value;
	}

	public String getTypeObjConstraint() {
		return this.TypeObjConstraint;
	}

	public double getObjCoefficient() {
		return this.objCoefficient;
	}
	
}
