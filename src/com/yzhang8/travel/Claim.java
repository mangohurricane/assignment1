package com.yzhang8.travel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;




public class Claim implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7745849442951123344L;
	protected String claimName; 
	protected String sdate;
	protected String rdate;
	protected String des;
	
	protected ArrayList<Item> itemList;
	protected transient ArrayList<Listener> listeneri;
	
	public Claim(String claimName,String sdate,String rdate,String des){
		this.sdate = sdate;
		this.claimName = claimName;
		this.rdate = rdate;
		this.des = des;
		itemList = new ArrayList<Item>();
		listeneri = new ArrayList<Listener>();
	}
	
	private ArrayList<Listener> getListeners() {
		if (listeneri == null) {
			listeneri = new ArrayList<Listener>();
		}
		return listeneri;
	}
	
	public Collection<Item> getItem(){
		return itemList;
	}
	public String getName(){
		return claimName;
	}
	
	public void setName(String s){
		this.claimName = s;
	}
	
	public void addItem(Item newItem){
		getItem().add(newItem);
		notifyListener();
	}
	
	private void notifyListener() {
		for (Listener listener : listeneri) {
			listener.update();
		}
	}
	
	public void removeItem(Item item){
		getItem().remove(item);
		notifyListener();
	}

	public String toString(){
		return getName();
	}
	
	public void addListener(Listener l) {
		getListeners().add(l);

	}

	public Item getPosition(int position) {
		return itemList.get(position);
	}

	public void removeListener(Listener l) {
		getListeners().remove(l);

	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getRdate() {
		return rdate;
	}

	public void setrdate(String rdate) {
		this.rdate = rdate;
	}

	public String getDescription() {
		return des;
	}

	public void setDescription(String des) {
		this.des = des;
	}
	public void setItems(ArrayList<Item> items) {
		this.itemList = items;
	}
}




