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

public class ViewItemActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_item);
		Bundle extras = getIntent().getExtras();
		final int pos1 = extras.getInt("id");
		ClaimListManager.initManager(this.getApplicationContext());
		ListView listView = (ListView) findViewById(R.id.itemlistView);
		Collection<Item> itemCollection = ClaimController.getClaimList().getPosition(pos1).getItem();
		final ArrayList<Item> ilist = new ArrayList<Item>(itemCollection);
		final ArrayAdapter<Item> itemAdapter = new ArrayAdapter<Item>(this,android.R.layout.simple_list_item_1, ilist);
		listView.setAdapter(itemAdapter);

		
		// Add a change observer here!
		ClaimController.getClaimList().getPosition(pos1).addListener(new Listener() {
			@Override
			public void update() {
				ilist.clear();
				Collection<Item> itemCollection = ClaimController.getClaimList()
						.getPosition(pos1).getItem();
				ilist.addAll(itemCollection);
				itemAdapter.notifyDataSetChanged();
			}
		});
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view,
					int pos, long id) {
				final int finalPosition = pos;
				AlertDialog.Builder adb = new AlertDialog.Builder(ViewItemActivity.this);
				adb.setMessage("Delete "+ilist.get(pos).toString()+"?");
				adb.setCancelable(true);
			
				adb.setPositiveButton("Delete", new OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
					Item item = ilist.get(finalPosition);				
					ClaimController.getClaimList().getPosition(pos1).removeItem(item);	
					ClaimController.saveClaimList();
				
					}
				});
				
				adb.setNegativeButton("view", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(ViewItemActivity.this,
								AddItemActivity.class);
						Toast.makeText(
								ViewItemActivity.this,
								"Edit/View " + ilist.get(finalPosition).toString()
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
		getMenuInflater().inflate(R.menu.view_item, menu);
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
	
	public void addnewitem(View V) {
		Bundle extras = getIntent().getExtras();

		int temp = extras.getInt("id");
		Toast.makeText(this, "Claim"+temp, Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(ViewItemActivity.this, AddItemActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("id", temp);
		startActivity(intent);
	}
	
	public void calculate(View V) {
		Toast.makeText(this, "The sum of your trip is", Toast.LENGTH_SHORT).show();
		
	}
}
