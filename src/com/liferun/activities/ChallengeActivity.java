package com.liferun.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.liferun.liferun.R;

public class ChallengeActivity extends Activity{
	
	private ImageView challengeImage;
	private TextView challengeName, challengeDescription; 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    // Set the user interface layout for this Activity
	    // The layout file is defined in the project res/layout/main_activity.xml file
	    setContentView(R.layout.activity_challenge_description);
	    
	    // Initialize member TextView so we can manipulate it later
	    Button buttonAccept = (Button) findViewById(R.id.button_accept);
	    Button buttonDecline = (Button) findViewById(R.id.button_decline);
	    Button buttonHelp = (Button) findViewById(R.id.button_help);
	    challengeImage = (ImageView) findViewById(R.id.challenge_image);
	    challengeName = (TextView) findViewById(R.id.challenge_name);
	    challengeDescription = (TextView) findViewById(R.id.challenge_description);
	}
	
	OnClickListener onBtnClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.button_accept:
					
					break;
					
				case R.id.button_decline:
					
					break;
					
				case R.id.button_help:
					
					break;
	
				default:
					break;
			}
		}
	};
}
