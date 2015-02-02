package com.yzhang8.travel;

import java.util.ArrayList;
import java.util.Collection;


import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ClaimListManager.initManager(this.getApplicationContext());
		ListView listView = (ListView) findViewById(R.id.claimlistview);
		Collection<Claim> claimCollection = ClaimController.getClaimList().getClaim();
		final ArrayList<Claim> list = new ArrayList<Claim>(claimCollection);
		final ArrayAdapter<Claim> claimAdapter = new ArrayAdapter<Claim>(this,android.R.layout.simple_list_item_1, list);
		listView.setAdapter(claimAdapter);

		
		// Add a change observer here!
		ClaimController.getClaimList().addListener(new Listener() {
			@Override
			public void update() {
				list.clear();
				Collection<Claim> claimCollection = ClaimController.getClaimList()
						.getClaim();
				list.addAll(claimCollection);
				claimAdapter.notifyDataSetChanged();
			}
		});
		
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
				adb.setMessage(""+list.get(position).toString()+"");
				adb.setCancelable(true);
				final int finalPosition = position;
				adb.setPositiveButton("Delete", new OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
					Claim claim = list.get(finalPosition);				
					ClaimController.getClaimList().deleteClaim(claim);												
				
					}
				});
				
				adb.setNegativeButton("Add/View Items", new OnClickListener() {					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						Intent intent = new Intent(MainActivity.this,ViewItemActivity .class);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						intent.putExtra("id", finalPosition);
						startActivity(intent);
					}
				});
				adb.setNeutralButton("Edit/View", new OnClickListener() {					
					@Override
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

						Intent intent = new Intent(MainActivity.this,
								AddActivity.class);
						Toast.makeText(
								MainActivity.this,
								"Edit/View " + list.get(finalPosition).toString()
										, Toast.LENGTH_SHORT).show();
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						intent.putExtra("id", finalPosition);
						startActivity(intent);
				}
				});
				
				adb.show();
				return false;
		
		
			}				
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public void addnewclaim(View V) {
		Toast.makeText(this, "Claim", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(MainActivity.this, AddActivity.class);
		startActivity(intent);
	}
	public void sendemail(View V) {
		Toast.makeText(this, "Sending Email", Toast.LENGTH_SHORT).show();
		
	}

}
