package com.example.Preclaimupdate.res;

public class Responce {

	private Object data;
	private String Status;

	public Responce(Object data, String status) {
		super();
		this.data = data;
		Status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Responce() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

}
