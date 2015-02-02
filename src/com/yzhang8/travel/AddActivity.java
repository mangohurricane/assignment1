package com.yzhang8.travel;



import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends ActionBarActivity {

	private Button addbutton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		ClaimListManager.initManager(this.getApplicationContext());
		addbutton= (Button) findViewById(R.id.addbutton);
		EditText na =(EditText) findViewById(R.id.editname);
		EditText sd =(EditText) findViewById(R.id.editstartdate);
		EditText rd =(EditText) findViewById(R.id.editreturndate);
		EditText ds =(EditText) findViewById(R.id.editdescription);
		//http://stackoverflow.com/questions/4233873/how-to-get-extra-data-from-intent-in-android
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			int temp = extras.getInt("id");
			Claim cclaim = ClaimController.getClaimList().getPosition(temp);
			na.setText(cclaim.getName());
			sd.setText(cclaim.getSdate());
			rd.setText(cclaim.getRdate());
			ds.setText(cclaim.getDescription());
			addbutton.setOnClickListener(new UpdateClaim(cclaim));
		}
		if (extras == null) {
			addbutton.setOnClickListener(new AddClaim());
		}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add, menu);
		return true;
	}


private class AddClaim implements OnClickListener {

		public void onClick(View v) {
		
			Toast.makeText(AddActivity.this, "Added", Toast.LENGTH_LONG).show();
			ClaimController cl =new ClaimController();
			EditText na =(EditText) findViewById(R.id.editname);
			EditText sd =(EditText) findViewById(R.id.editstartdate);
			EditText rd =(EditText) findViewById(R.id.editreturndate);
			EditText ds =(EditText) findViewById(R.id.editdescription);
			cl.addClaim(new Claim(na.getText().toString(),sd.getText().toString(),rd.getText().toString(),ds.getText().toString()));
			Intent intent = new Intent( AddActivity.this,MainActivity.class);
			startActivity(intent);
		// TODO Auto-generated method stub
		
	}

	

	}


public class UpdateClaim implements OnClickListener {
	private Claim claim;

	public UpdateClaim(Claim claim) {
		this.setName(claim);
	}

	

		@SuppressLint("FieldGetter")
		public void onClick(View v) {
			EditText na =(EditText) findViewById(R.id.editname);
			EditText sd =(EditText) findViewById(R.id.editstartdate);
			EditText rd =(EditText) findViewById(R.id.editreturndate);
			EditText ds =(EditText) findViewById(R.id.editdescription);
			this.getClaim().setName(na.getText().toString());
			this.getClaim().setDescription(ds.getText().toString());
			this.getClaim().setrdate(rd.getText().toString());
			this.getClaim().setSdate(sd.getText().toString());
			ClaimController.saveClaimList();
			Intent intent = new Intent(AddActivity.this,
					MainActivity.class);
			startActivity(intent);
		}


		public Claim getClaim() {
			return claim;
		}

		public void setName(Claim claim) {
			this.claim = claim;
		}

	}


}

