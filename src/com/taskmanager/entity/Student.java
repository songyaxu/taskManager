package com.taskmanager.entity;

public class Student {
	private int id;
	private String no;
	private String name;
	private String pwd;
	public Student(){
	}
	public Student(String no,String pwd){
		this.no=no;
		this.pwd=pwd;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
