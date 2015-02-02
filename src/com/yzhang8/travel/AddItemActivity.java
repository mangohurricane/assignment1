package com.yzhang8.travel;



import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItemActivity extends ActionBarActivity {

	private Button itemadd;
	private EditText iName;
	private EditText Fee;
	private EditText Mt;
	private EditText Da;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.additem);
		Da = (EditText) findViewById(R.id.itemdate);
		Fee= (EditText) findViewById(R.id.editTextfee);
		Mt=(EditText) findViewById(R.id.editmoney);
		iName= (EditText) findViewById(R.id.expenseName);
		ClaimListManager.initManager(this.getApplicationContext());
		itemadd= (Button) findViewById(R.id.itemadd);
		Bundle extras = getIntent().getExtras();
		final int temp = extras.getInt("id"); 	
		if (extras.size() == 2) {
			final int pos = extras.getInt("pos");
			Item citem= ClaimController.getClaimList()
					.getPosition(temp).getPosition(pos);
			String itemName = citem.getiName();
			Fee.setText(citem.getfee());
			Toast.makeText(this, itemName, Toast.LENGTH_SHORT).show();
			itemadd.setOnClickListener(new Update());
		}
		else{
			
			itemadd.setOnClickListener(new AddItem());
		}
	}
	
	public class Update implements OnClickListener {
			public void onClick(View v) {
				iName= (EditText) findViewById(R.id.expenseName);
				Bundle extras = getIntent().getExtras();
				int pos1 = extras.getInt("id");
				int pos = extras.getInt("pos");
				ClaimController.getClaimList().getPosition(pos1).getPosition(pos)
						.setiName(iName.getText().toString());
				Intent intent = new Intent(AddItemActivity.this,
						ViewItemActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtra("id", pos1);
				ClaimController.saveClaimList();
				startActivity(intent);

			}
		}

	public class AddItem implements OnClickListener {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				iName= (EditText) findViewById(R.id.expenseName);
				Bundle extras = getIntent().getExtras();
				int p2 = extras.getInt("id");
				Item item = new Item(iName.getText().toString(),Fee.getText().toString(),Mt.getText().toString(),Da.toString());
				ClaimController.getClaimList().getPosition(p2).addItem(item);;
				Toast.makeText(AddItemActivity.this, "Done",
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(AddItemActivity.this,
						ViewItemActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtra("id", p2);
				ClaimController.saveClaimList();
				startActivity(intent);
			}

		
			
	}

	
	
	
		


@Override
public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.add, menu);
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



}
