package com.yzhang8.travel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;


public class ClaimListManager {
	Context context;
	static final String clKey = "ClaimList";
	static final String preFile = "Claimlist";
	static private ClaimListManager claimListManager = null;
	
	public static ClaimListManager getManager() {
		if (claimListManager == null) {
			throw new RuntimeException("Did not initialize Manager");
		}
		return claimListManager;
	}
	
	public ClaimListManager(Context context) {
		this.context = context;
	}

	public static void initManager(Context context) {
		if (claimListManager == null) {
			if (context == null) {
				throw new RuntimeException(
						"missing context for ClaimListManager");
			}
			claimListManager = new ClaimListManager(context);
		}
	}

	private ClaimList claimListFromString(String claimListData)
			throws StreamCorruptedException, IOException,
			ClassNotFoundException {
		ByteArrayInputStream bi = new ByteArrayInputStream(Base64.decode(
				claimListData, Base64.DEFAULT));
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (ClaimList) oi.readObject();
	}

	private String claimListToString(ClaimList cl)
			throws IOException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(cl);
		oo.close();
		byte[] bytes = bo.toByteArray();
		return Base64.encodeToString(bytes, Base64.DEFAULT);
	}

	public ClaimList loadClaimList() throws StreamCorruptedException,
			IOException, ClassNotFoundException {
		SharedPreferences settings = context.getSharedPreferences(preFile,
				Context.MODE_PRIVATE);
		String claimListData = settings.getString(clKey, "");
		if (claimListData == "") {
			return new ClaimList();
		} else {
			return claimListFromString(claimListData);
		}
	}



	public void saveClaimList(ClaimList cl) throws IOException {
		SharedPreferences settings = context.getSharedPreferences(preFile,
				Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		editor.putString(clKey, claimListToString(cl));
		editor.commit();
	}

}
