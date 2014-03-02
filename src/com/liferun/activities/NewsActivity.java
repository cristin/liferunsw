package com.liferun.activities;

import java.util.ArrayList;

import com.liferun.arrayadapters.InterestsArrayAdapter;
import com.liferun.arrayadapters.InterestsArrayAdapter.ViewHolder;
import com.liferun.arrayadapters.NewsArrayAdapter;
import com.liferun.data.Challenge;
import com.liferun.liferun.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class NewsActivity extends Activity {
	
	ArrayList<Challenge> yourChallenges = new ArrayList<Challenge>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		ListView lv = (ListView) findViewById(R.id.listView1);
		getAllChallenges();
		final NewsArrayAdapter arrayAdapter = new NewsArrayAdapter(getApplicationContext(),R.layout.newsfeed_item, yourChallenges);
        
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
	
	
	private void getAllChallenges(){
		
		Challenge ch = new Challenge("firsty", "desr", "", "", 2);
		yourChallenges.add(ch);
		ch = new Challenge("firsty2", "desr", "", "", 2);
		yourChallenges.add(ch);
		ch = new Challenge("firsty3", "desr", "", "", 2);
		yourChallenges.add(ch);
		
		
	}
	
}
