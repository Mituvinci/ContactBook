package com.mitu.contactbook;

import java.util.ArrayList;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	
	DBHelper dbHelper;
    ListView lvContactList;
   // ImageView img;
    ArrayList<contactHolder> alContacts =  new ArrayList<>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lvContactList = (ListView) findViewById(R.id.listView);
		//img = (ImageView)findViewById(R.id.contactimage);
        dbHelper = new DBHelper(getApplicationContext());
    //new start
        
        alContacts = dbHelper.getContactList();
        lvContactList.setAdapter( new ContactAdapter() );
        //new end
        
         clicklist();
         edit();
	}
	
	public void  clicklist(){
		alContacts = dbHelper.getContactList();
        lvContactList.setAdapter( new ContactAdapter() );

        lvContactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent intent = new Intent(Intent.ACTION_DIAL);
                //startActivity(intent);
            	
            	Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+alContacts.get(position).phone.trim()) );
                startActivity(intent);

             /*
            	//edit start
            	int _id = alContacts.get(position).id;
                
                String namec = alContacts.get(position).name;
                String phonec = alContacts.get(position).phone;

              //  Toast.makeText(getApplicationContext(),String.valueOf(_id),Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),AddContact.class);
				i.putExtra("_id",_id);
                i.putExtra("namep",namec);
                i.putExtra("phonep",phonec);
                startActivityForResult(i,101);
                
                //edit end
                */

            }
        });
	} 
	
	
	void edit(){
		lvContactList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                

             
            	//edit start
            	int _id = alContacts.get(position).id;
                
                String namec = alContacts.get(position).name;
                String phonec = alContacts.get(position).phone;

              //  Toast.makeText(getApplicationContext(),String.valueOf(_id),Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),AddContact.class);
				i.putExtra("_id",_id);
                i.putExtra("namep",namec);
                i.putExtra("phonep",phonec);
                startActivityForResult(i,101);
                
                //edit end
                
                return true;
            }
            
            
            
        });
	}
	
	  class ContactAdapter extends BaseAdapter{

	        @Override
	        public int getCount() {
	            return alContacts.size();
	        }

	        @Override
	        public Object getItem(int position) {
	            return alContacts.get(position);
	        }

	        @Override
	        public long getItemId(int position) {
	            return position;
	        }

	        @Override
	        public View getView(int position, View convertView, ViewGroup parent) {

	            convertView = getLayoutInflater().inflate(R.layout.layout_phonebook,parent,false);

	            TextView tvName = (TextView) convertView.findViewById(R.id.textView);
	            TextView tvPhone = (TextView) convertView.findViewById(R.id.textView2);

	            contactHolder ch = (contactHolder) getItem(position);
	            tvName.setText(ch.name);
	            tvPhone.setText(ch.phone);

	            return convertView;
	        }
	    }
	  public void goToAdd(View v){
		  startActivityForResult(new Intent(getApplicationContext(),AddContact.class),100);
	    }
	  
	  @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if(requestCode == 100){
	            if(resultCode == RESULT_OK){
	            	clicklist();
	            }
	        }
	        else if(requestCode == 101){
	            if(resultCode == RESULT_OK){
	            	clicklist();
	            }
	        }
	        if(requestCode == 102){
	            if(resultCode == RESULT_OK){
	            	clicklist();
	            }
	        }
	        super.onActivityResult(requestCode, resultCode, data);
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
		else if(id == R.id.edit){
			finish();
		}
		
			
		return super.onOptionsItemSelected(item);
	}
}
