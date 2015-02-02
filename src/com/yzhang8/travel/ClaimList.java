package com.yzhang8.travel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;


public class ClaimList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8627997992605907282L;
	protected ArrayList<Claim> claimList = null;
	protected transient ArrayList<Listener> listeners = null;

	public ClaimList() {
		claimList = new ArrayList<Claim>();
		listeners = new ArrayList<Listener>();

	}

	public Claim getPosition(int position) {
		return getClaimList().get(position);
	}

	private ArrayList<Listener> getListeners() {
		if (listeners == null) {
			listeners = new ArrayList<Listener>();
		}
		return listeners;
	}

	public void addListener(Listener l) {
		getListeners().add(l);
	}
	
	public void removeListener(Listener l) {
		getListeners().remove(l);
	}

	private void notifyListeners() {
		for (Listener listener : getListeners()) {
			listener.update();
		}
	}

	public void addClaim(Claim claim) {
		claimList.add(claim);
		notifyListeners();
	}

	public void deleteClaim(Claim claim) {
		claimList.remove(claim);
		notifyListeners();
	}

	public Collection<Claim> getClaim() {
		return claimList;
	}

	public ArrayList<Claim> getClaimList() {
		return claimList;
	}
}
