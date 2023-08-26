package com.somesh.model;

public class AddressResp {
	private int id;
	private String lane;
	private String zip;
	private String state;
	private int emp_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLane() {
		return lane;
	}

	public void setLane(String lane) {
		this.lane = lane;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	@Override
	public String toString() {
		return "AddressResp [id=" + id + ", lane=" + lane + ", zip=" + zip + ", state=" + state + ", emp_id=" + emp_id
				+ "]";
	}

}
