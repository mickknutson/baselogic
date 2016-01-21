package com.baselogic.model;

import java.util.*;
import javax.xml.bind.annotation.*;

@XmlRootElement
public class Order {

	private int number;
	private String desc;
	private Date entryDate;
	public Order() {}
	public Order (int number , String desc, Date entryDate){
		this.number=number;
		this.desc=desc;
		this.entryDate=entryDate;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public String toString() {
		return "Order number: " + number + " " + desc + " " + entryDate;
	}

}
