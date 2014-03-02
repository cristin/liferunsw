package com.liferun.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

import com.liferun.data.DataHolder;
import com.liferun.definitions.ActionResultDelegate;
import com.liferun.definitions.QBQueriesDef.QBQueryType;
import com.liferun.definitions.ResponseHttpStatus;
import com.liferun.liferun.R;
import com.liferun.objects.RestResponse;
import com.liferun.qb.AlertManager;
import com.liferun.qb.QBQueries;
import com.liferun.qb.Query;
import com.liferun.qb.Store;
import com.quickblox.core.QBCallback;
import com.quickblox.core.QBSettings;
import com.quickblox.core.result.Result;
import com.quickblox.module.auth.QBAuth;
import com.quickblox.module.users.QBUsers;
import com.quickblox.module.users.result.QBUserPagedResult;

public class SplashActivity extends Activity implements ActionResultDelegate {

	private static int SPLASH_TIME_OUT = 1500;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		configApp();

		new Handler().postDelayed(new Runnable() {

			@Override

			public void run() {
				Class activityToStart = MyChallengesActivity.class;//LoginActivity.class;//MapActivity.class;
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
		/*QBSettings.getInstance().fastConfigInit("7774", "muRG3KueHCcSy7N",
				"pNvXQvEdLRkLrTD");
		QBAuth.createSession((QBCallback) this,
				QBQueries.QB_QUERY_AUTHORIZE_APP);*/
	}

	// QuickBlox queries callback
	@Override
	public void completedWithResult(QBQueryType queryType, RestResponse response) {
		// no internet connection
		if (response == null) {
			AlertManager.showServerError(this,
					"Please check your internet connection");
			return;
		}

		switch (queryType) {
		case QBQueryTypeGetAuthToken:
			if (response.getResponseStatus() == ResponseHttpStatus.ResponseHttpStatus201) {
				Store.getInstance().setAuthToken(
						response.getBody().findChild("token").getText());

				// show main screen
				Intent intent = new Intent();
				intent.setClass(this, LoginActivity.class);
				startActivity(intent);
				finish();

			} else if (response.getResponseStatus() == ResponseHttpStatus.ResponseHttpStatus422) {
				String error = response.getBody().getChildren().get(0)
						.getText();
				AlertManager.showServerError(this, error);
			} else {
				AlertManager
						.showServerError(this, "Oops! Something went wrong");
			}
			break;
		}
	}

	private void getAllUser() {
		// Get all users for the current app
		QBUsers.getUsers(new QBCallback() {
			@Override
			public void onComplete(Result result) {
				// return QBUserPagedResult for getUsers query
				QBUserPagedResult qbUserPagedResult = (QBUserPagedResult) result;
				DataHolder.getDataHolder().setQbUserList(
						qbUserPagedResult.getUsers());
				// startGetAllUsersActivity();
			}

			@Override
			public void onComplete(Result result, Object o) {
			}
		});
	}

}
