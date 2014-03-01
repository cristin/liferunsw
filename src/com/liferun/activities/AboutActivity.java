package com.liferun.activities;

import com.liferun.liferun.R;
import com.quickblox.core.QBCallbackImpl;
import com.quickblox.core.result.Result;
import com.quickblox.module.auth.QBAuth;

import android.app.Activity;
import android.os.Bundle;

public class AboutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
	QBAuth.createSession(new QBCallbackImpl() {
	    @Override
	    public void onComplete(Result result) {
	        // result comes here
	        // check if result success
	        if (result.isSuccess()) {
	            // do stuff you need
	        }
	    }
	});
	}
}
