package com.mitu.contactbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddContact extends ActionBarActivity {

	  EditText etName, etPhone;
	    DBHelper dbHelper;
	    int _id;
	    TextView tv;
	    Button del;
	    MainActivity mainActivity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_contact);
		etName = (EditText) findViewById(R.id.editText);
        etPhone = (EditText) findViewById(R.id.editText2);
        tv = (TextView)findViewById(R.id.textView1);
        del = (Button)findViewById(R.id.delete1);
        dbHelper = new DBHelper(getApplicationContext());
        del.setVisibility(View.INVISIBLE);
        
        Intent i = getIntent();
        _id = i.getIntExtra("_id",-1);
        if(_id>0){
            String name = i.getStringExtra("namep");
            String phone = i.getStringExtra("phonep");
            etName.setText(name);
            etPhone.setText(phone);
            tv.setText("Edit Contact");
            del.setVisibility(View.VISIBLE);
        }
	}

	public void addContact(View v){
        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();
        contactHolder ch = new contactHolder(name,phone);
        if(_id > 0){
            dbHelper.updatec(_id, ch);
        }else
        dbHelper.insertContact(ch);
        //startActivity( new Intent(getApplicationContext(),MainActivity.class) );
        setResult(RESULT_OK);
        finish();
	
	}
	
	public void deletecontact(View v){
		 dbHelper.deleteContact(_id);
		// startActivityForResult( new Intent(getApplicationContext(),MainActivity.class),102 );
		setResult(RESULT_OK);
	        finish();
		 
	}
	
	 @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if(requestCode == 102){
	            if(resultCode == RESULT_OK){
	            	mainActivity.clicklist();
	            }
	        }
	        
	        super.onActivityResult(requestCode, resultCode, data);
	    }
	
	
	
	public void back(View v){
		// startActivity( new Intent(getApplicationContext(),MainActivity.class) );
		setResult(RESULT_OK);
        finish();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_contact, menu);
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
		else
			if(id==R.id.delete){
				dbHelper.deleteContact(_id);
				 setResult(RESULT_OK);
			        finish();
			}
		return super.onOptionsItemSelected(item);
	}
}
