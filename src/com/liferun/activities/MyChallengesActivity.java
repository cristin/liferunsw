package com.liferun.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.liferun.arrayadapters.MyChallengesArrayAdapter;
import com.liferun.arrayadapters.MyChallengesArrayAdapter.ViewHolder;
import com.liferun.liferun.R;

public class MyChallengesActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_my_challenges);
	    
	    ListView lv = (ListView) findViewById(R.id.listView1);

        ArrayList<String> yourArrayList = new ArrayList<String>();
        yourArrayList.add("foo");
        yourArrayList.add("bar");

        final MyChallengesArrayAdapter arrayAdapter = new MyChallengesArrayAdapter (getApplicationContext(),R.layout.challenges_array_item, yourArrayList);
        
        lv.setAdapter(arrayAdapter);
       
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
				ViewHolder holder = (ViewHolder)view.getTag();
				
				arrayAdapter.notifyDataSetChanged();
			}
        });
	    
	    // Initialize member TextView so we can manipulate it later
	    //Button buttonAccept = (Button) findViewById(R.id.button_accept);
	}
}
