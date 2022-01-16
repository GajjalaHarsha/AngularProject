package com.cts.hospitalnearby.dto;

import java.math.BigDecimal;

public class DoctorDTO {

	private Long doctorId;
	private String doctorName;
	private String splName;
	private int experience;
	private int noOfOperationsTaken;
	private int operationsSuccess;
	private BigDecimal successRatio;

	public Long getDoctorId() {
		return doctorId;
	}

	public String getSplName() {
		return splName;
	}

	public void setSplName(String splName) {
		this.splName = splName;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getNoOfOperationsTaken() {
		return noOfOperationsTaken;
	}

	public void setNoOfOperationsTaken(int noOfOperationsTaken) {
		this.noOfOperationsTaken = noOfOperationsTaken;
	}

	public int getOperationsSuccess() {
		return operationsSuccess;
	}

	public void setOperationsSuccess(int operationsSuccess) {
		this.operationsSuccess = operationsSuccess;
	}

	public BigDecimal getSuccessRatio() {
		return successRatio;
	}

	public void setSuccessRatio(BigDecimal successRatio) {
		this.successRatio = successRatio;
	}

}
