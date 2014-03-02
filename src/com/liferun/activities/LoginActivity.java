package com.liferun.activities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;
import com.liferun.definitions.ActionResultDelegate;
import com.liferun.definitions.QBQueriesDef;
import com.liferun.definitions.QBQueriesDef.QBQueryType;
import com.liferun.definitions.QueryMethod;
import com.liferun.definitions.ResponseHttpStatus;
import com.liferun.liferun.R;
import com.liferun.objects.RestResponse;
import com.liferun.qb.AlertManager;
import com.liferun.qb.NumberToLetterConverter;
import com.liferun.qb.Query;
import com.liferun.qb.Store;

public class LoginActivity extends Activity implements ActionResultDelegate {

	private ProgressBar queryProgressBar;
	private Facebook facebook;
	private JSONObject fbUserBody;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		Button fbB = (Button)findViewById(R.id.btn_login_with_fb);
		fbB.setOnClickListener(onfbClick);

		try {
			PackageInfo info = getPackageManager().getPackageInfo(
					this.getPackageName(), PackageManager.GET_SIGNATURES);
			for (Signature signature : info.signatures) {

				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				Log.d("====Hash Key===",
						Base64.encodeToString(md.digest(), Base64.DEFAULT));

			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}

		facebook = new Facebook(getResources().getString(
				R.string.facebook_app_id));

		// queryProgressBar = (ProgressBar)
		// findViewById(R.id.queryLogin_progressBar);
	}

	OnClickListener onfbClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		
		switch (v.getId()) {
			case R.id.btn_login_with_fb: {
				// queryProgressBar.setVisibility(View.VISIBLE);
				facebook.authorize(LoginActivity.this, new String[] {},
						new Facebook.DialogListener() {
							@Override
							public void onComplete(Bundle values) {
	
								// get information about the currently logged in
								// user
								AsyncFacebookRunner asyncFacebookRunner = new AsyncFacebookRunner(
										facebook);
								asyncFacebookRunner.request("me",
										new AsyncFacebookRunner.RequestListener() {
											@Override
											public void onComplete(String response,
													Object state) {
	
												JSONObject user = null;
												try {
													user = new JSONObject(response);
												} catch (JSONException e) {
													e.printStackTrace();
													return;
												}
	
												fbUserBody = user;
	
												String id = null;
												try {
													id = (String) user.get("id");
												} catch (JSONException e) {
													e.printStackTrace();
													return;
												}
	
												final String login = NumberToLetterConverter
														.convertNumbersToLetters(id);
												final String password = String
														.valueOf(login.hashCode());
	
												// sign in
												runOnUiThread(new Runnable() {
													@Override
													public void run() {
														login(login, password);
													}
												});
											}
	
											@Override
											public void onIOException(
													IOException e, Object state) {
											}
	
											@Override
											public void onFileNotFoundException(
													FileNotFoundException e,
													Object state) {
											}
	
											@Override
											public void onMalformedURLException(
													MalformedURLException e,
													Object state) {
											}
	
											@Override
											public void onFacebookError(
													FacebookError e, Object state) {
											}
										});
							}
	
							@Override
							public void onFacebookError(FacebookError error) {
								//queryProgressBar.setVisibility(View.INVISIBLE);
							}
	
							@Override
							public void onError(DialogError e) {
								//queryProgressBar.setVisibility(View.INVISIBLE);
							}
	
							@Override
							public void onCancel() {
								//queryProgressBar.setVisibility(View.INVISIBLE);
							}
						});
			}
			break;
		}
	}
	};

	// sign in
	private void login(String login, String password) {
		// create entity
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("login", login));
		formparams.add(new BasicNameValuePair("password", password));
		formparams.add(new BasicNameValuePair("token", Store.getInstance()
				.getAuthToken()));
		UrlEncodedFormEntity postEntity = null;
		try {
			postEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		// make query
		Query.performQueryAsync(QueryMethod.Post,
				QBQueriesDef.LOGIN_USER_QUERY, postEntity, null, this,
				QBQueriesDef.QBQueryType.QBQueryTypeLoginUser);

		//queryProgressBar.setVisibility(View.VISIBLE);
	}

	public void onStart() {
		super.onStart();
	}

	public void onStop() {
		super.onStop();
	}

	@Override
	public void completedWithResult(QBQueryType queryType, RestResponse response) {
		// no internet connection
		if (response == null) {
			//queryProgressBar.setVisibility(View.GONE);
			AlertManager.showServerError(this,
					"Please check your internet connection");
			return;
		}

		Log.d("FB Login:", "completeWithResult");

		switch (queryType) {
		case QBQueryTypeLoginUser:
			//queryProgressBar.setVisibility(View.GONE);

			if (response.getResponseStatus() == ResponseHttpStatus.ResponseHttpStatus202) {

				// store current user
				Store.getInstance().setCurrentUser(response.getBody());

				Toast.makeText(this, "Login was successful!", Toast.LENGTH_LONG)
						.show();

				Intent intent = new Intent();
				intent.setClass(this, InterestsActivity.class);
				startActivity(intent);
				finish();

			} else if (response.getResponseStatus() == ResponseHttpStatus.ResponseHttpStatus401) {
				if (fbUserBody != null) {
					// Register FB user

					String fullName = null;
					try {
						fullName = (String) fbUserBody.get("name");
					} catch (JSONException e) {
						e.printStackTrace();
						return;
					}

					String id = null;
					try {
						id = (String) fbUserBody.get("id");
					} catch (JSONException e) {
						e.printStackTrace();
						return;
					}

					String login = NumberToLetterConverter
							.convertNumbersToLetters(id);
					String password = String.valueOf(login.hashCode());

					List<NameValuePair> formparamsUser = new ArrayList<NameValuePair>();
					formparamsUser.add(new BasicNameValuePair(
							"user[full_name]", fullName));
					formparamsUser.add(new BasicNameValuePair("user[login]",
							login));
					formparamsUser.add(new BasicNameValuePair("user[password]",
							password));
					formparamsUser.add(new BasicNameValuePair(
							"user[facebook_id]", id));
					formparamsUser.add(new BasicNameValuePair("token", Store
							.getInstance().getAuthToken()));

					UrlEncodedFormEntity postEntityUser = null;
					try {
						postEntityUser = new UrlEncodedFormEntity(
								formparamsUser, "UTF-8");
					} catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					}

					// make query for creating a user
					Query.performQueryAsync(QueryMethod.Post,
							QBQueriesDef.CREATE_USER_QUERY, postEntityUser,
							null, this,
							QBQueriesDef.QBQueryType.QBQueryTypeCreateUser);

					//queryProgressBar.setVisibility(View.VISIBLE);

					Log.w("LogibActivity", "Create FB User");

				} else {
					AlertManager
							.showServerError(this,
									"Unauthorized. Please check you login and password");
				}

				// validation error
			} else if (response.getResponseStatus() == ResponseHttpStatus.ResponseHttpStatus422) {
				String error = response.getBody().getChildren().get(0)
						.getText();
				AlertManager.showServerError(this, error);
			} else {
				AlertManager.showServerError(this,
						"Oops!. Something went wrong");
			}
			break;

		case QBQueryTypeCreateUser:
			//queryProgressBar.setVisibility(View.GONE);

			if (response.getResponseStatus() == ResponseHttpStatus.ResponseHttpStatus201) {
				String id = null;
				try {
					id = (String) fbUserBody.get("id");
				} catch (JSONException e) {
					e.printStackTrace();
					return;
				}

				String login = NumberToLetterConverter
						.convertNumbersToLetters(id);
				String password = String.valueOf(login.hashCode());

				// sign in
				login(login, password);
			} else {
				String error = response.getBody().getChildren().get(0)
						.getText();
				AlertManager.showServerError(this, error);
			}
		}
	}
	
	@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        facebook.authorizeCallback(requestCode, resultCode, data);
    }

}
