package com.mitu.contactbook;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class EditActivity extends ActionBarActivity {
	 EditText etName, etPhone;
	    DBHelper dbHelper;
	    String name,phone;
	    int id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		etName = (EditText) findViewById(R.id.editText);
        etPhone = (EditText) findViewById(R.id.editText2);
        dbHelper = new DBHelper(getApplicationContext());
        
        Intent i = getIntent();
        id = i.getIntExtra("_id", 0);
        name = i.getStringExtra("namep");
        phone = i.getStringExtra("phonep");
        etName.setText(name);
        etPhone.setText(phone);
	}
	
	public void editContact(View v){
        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();
        contactHolder ch = new contactHolder(name,phone);
         dbHelper.updatec(id, ch);
        startActivity( new Intent(getApplicationContext(),MainActivity.class) );
    }
	
	public void back(View v) {
		startActivity( new Intent(getApplicationContext(),MainActivity.class) );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit, menu);
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
