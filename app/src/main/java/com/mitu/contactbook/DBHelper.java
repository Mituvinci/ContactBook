package com.mitu.contactbook;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
	
	 public static final int DATABASE_VERSION = 1;
	    public static final String DATABASE_NAME = "phonebook";
	    public static final String TABLE_CONTACT = "contact";
	    public static final String CONTACT_ID = "_id";
	    public static final String CONTACT_NAME = "_name";
	    public static final String CONTACT_NUMBER = "_number";


	    public static final String QUERY_CREATE = "CREATE TABLE IF NOT EXISTS "+TABLE_CONTACT+" ("+
	            CONTACT_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
	            CONTACT_NAME +" VARCHAR(100), "+
	            CONTACT_NUMBER +" VARCHAR(100) ); "
	            ;

	    public static final String QUERY_DROP = "DROP TABLE IF EXISTS "+TABLE_CONTACT;

	    private Context context;
	    public DBHelper(Context context) {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	        this.context = context;
	        Log.d("DBHELPER","Constructed");
	      //  Toast.makeText(this.context,"Constructed",Toast.LENGTH_SHORT).show();
	    }

	    @Override
	    public void onCreate(SQLiteDatabase db) {
	        db.execSQL(QUERY_CREATE);
	       // Toast.makeText(this.context,"Created",Toast.LENGTH_SHORT).show();
	        Log.d("DBHELPER","Created");
	    }

	    @Override
	    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	        db.execSQL(QUERY_DROP);
	        db.execSQL(QUERY_CREATE);
	       // Toast.makeText(this.context,"Upgraded",Toast.LENGTH_SHORT).show();
	        Log.d("DBHELPER","Upgraded");
	    }

	    public void insertContact(contactHolder ch){
	        SQLiteDatabase db = getWritableDatabase();
	        ContentValues values = new ContentValues();
	        values.put( CONTACT_NAME, ch.name);
	        values.put( CONTACT_NUMBER, ch.phone);
	        db.insert(TABLE_CONTACT,CONTACT_NAME, values);
	      //  Toast.makeText(this.context,"Inserted",Toast.LENGTH_SHORT).show();
	        Log.d("DBHELPER","Inserted");
	    }
	    
//	    public void update(int id,contactHolder ch){
//	    	
//	    }

	    public ArrayList<contactHolder> getContactList(){
	        ArrayList<contactHolder> contact = new ArrayList<>();
	        SQLiteDatabase db = getWritableDatabase();
	        Cursor cursor = db.query(TABLE_CONTACT,null,null,null,null,null,null);
	        if(cursor != null && cursor.getCount()>0) {
	            cursor.moveToFirst();
	            for (int i = 0; i < cursor.getCount(); i++){
	                int id = cursor.getInt(cursor.getColumnIndex(CONTACT_ID));
	                String name = cursor.getString( cursor.getColumnIndex(CONTACT_NAME ));
	                String number = cursor.getString( cursor.getColumnIndex(CONTACT_NUMBER ));
	                contactHolder contactHolder = new contactHolder(id,name,number);
	                contact.add(contactHolder);
	                cursor.moveToNext();
	            }
	        }
	      //  Toast.makeText(this.context,"Fetched",Toast.LENGTH_SHORT).show();
	        Log.d("DBHELPER","Fetched");
	        return contact;
	    }

		public void updatec(int id, contactHolder ch) {
			// TODO Auto-generated method stub
			SQLiteDatabase db = getWritableDatabase();
	        ContentValues values = new ContentValues();
	        values.put( CONTACT_NAME, ch.name);
	        values.put( CONTACT_NUMBER, ch.phone);
	        
	        db.update(TABLE_CONTACT, values, CONTACT_ID+"="+id, null);
			
			
			
		}
		
		 public void deleteContact(int id){
		        SQLiteDatabase db = getWritableDatabase();
		        String[] arg = new String[]{ String.valueOf(id) };
		        //db.delete(TABLE_CONTACT,CONTACT_ID+"=?", arg );
		       
		        db.delete(TABLE_CONTACT," _id=? ", arg );
		    }

		

}
