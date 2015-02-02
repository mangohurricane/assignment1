package com.yzhang8.travel;

import java.io.Serializable;

public class Item implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9098691135380046552L;
	protected String itemName;
	protected  String fee;
	protected String moneytype;
	protected String date;

	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMoneytype() {
		return moneytype;
	}

	public void setMoneytype(String moneytype) {
		this.moneytype = moneytype;
	}

	public Item(String iName,String string,String moneytype,String date){
		this.setiName(iName);
		this.setfee(string);
		this.setMoneytype(moneytype);
		this.setDate(date);
		
	}
	
	public void setiName(String iName) {
		this.itemName = iName;
		
	}

	public String getiName(){
		return this.itemName;
	}
	
	public String toString(){
		
		return getiName()+getfee();
	} 




	public String getfee() {
		return this.fee;
	}

	public void setfee(String string) {
		this.fee = string;
	}




	

	
}