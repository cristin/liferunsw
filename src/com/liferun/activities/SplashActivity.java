package com.liferun.activities;

import com.liferun.data.DataHolder;
import com.liferun.liferun.R;
import com.liferun.liferun.R.layout;
import com.liferun.liferun.R.menu;
import com.liferun.qb.QBQueries;
import com.quickblox.core.QBCallback;
import com.quickblox.core.QBSettings;
import com.quickblox.core.result.Result;
import com.quickblox.module.auth.QBAuth;
import com.quickblox.module.users.QBUsers;
import com.quickblox.module.users.result.QBUserPagedResult;


import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.Toast;

public class SplashActivity extends Activity implements QBCallback{

	private static int SPLASH_TIME_OUT = 1500;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		configApp();
		 new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				Class activityToStart = LoginActivity.class;//MapActivity.class;
				 Intent i = new Intent(SplashActivity.this, activityToStart);
                 //i.putExtra("call","startup");
                 startActivity(i);
			}
			 
		 }, SPLASH_TIME_OUT);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}
	
	private void configApp() {
		QBSettings.getInstance().fastConfigInit("7774", "muRG3KueHCcSy7N", "pNvXQvEdLRkLrTD");	
		QBAuth.createSession((QBCallback) this, QBQueries.QB_QUERY_AUTHORIZE_APP);
	}

	@Override
	public void onComplete(Result arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onComplete(Result result, Object context) {
		 QBQueries qbQueryType = (QBQueries) context;
	        if (result.isSuccess()) {
	            switch (qbQueryType) {
	                case QB_QUERY_AUTHORIZE_APP:
	                    getAllUser();
	                    break;
	            }
	        } else {
	            // print errors that came from server
	            Toast.makeText(getBaseContext(), result.getErrors().get(0), Toast.LENGTH_SHORT).show();
//	            /progressBar.setVisibility(View.INVISIBLE);
	        }
		
	}
	
	 private void getAllUser() {
	        // Get all users for the current app
	        QBUsers.getUsers(new QBCallback() {
	            @Override
	            public void onComplete(Result result) {
	                // return QBUserPagedResult for getUsers query
	                QBUserPagedResult qbUserPagedResult = (QBUserPagedResult) result;
	                DataHolder.getDataHolder().setQbUserList(qbUserPagedResult.getUsers());
	                //startGetAllUsersActivity();
	            }

	            @Override
	            public void onComplete(Result result, Object o) {
	            }
	        });
	    }

}
