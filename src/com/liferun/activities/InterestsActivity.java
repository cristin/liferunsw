package com.liferun.activities;

import java.util.ArrayList;

import com.liferun.arrayadapters.InterestsArrayAdapter;
import com.liferun.arrayadapters.InterestsArrayAdapter.ViewHolder;
import com.liferun.liferun.R;

import android.app.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class InterestsActivity extends Activity{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_interests);
		Button btnNext =(Button) findViewById(R.id.button1);
		ListView lv = (ListView) findViewById(R.id.listView1);

		btnNext.setOnClickListener(onNextBtn);
        ArrayList<String> yourArrayList = new ArrayList<String>();
        yourArrayList.add("foo");
        yourArrayList.add("bar");

        // This is the array adapter, it takes the context of the activity as a 
        // first parameter, the type of list view as a second parameter and your 
        // array as a third parameter.
        final InterestsArrayAdapter arrayAdapter = new InterestsArrayAdapter(getApplicationContext(),R.layout.interests_array_item, yourArrayList);
        
        lv.setAdapter(arrayAdapter);
       
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
				ViewHolder holder = (ViewHolder)view.getTag();
				if(holder.isChecked)
					holder.isChecked = false;
				else
					holder.isChecked = true;
				arrayAdapter.notifyDataSetChanged();
			}
        });
	}
	
	
	
	
	
	OnClickListener onNextBtn = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
	};
}
