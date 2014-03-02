package com.liferun.liferun;

import java.util.ArrayList;

import android.app.Application;
import android.widget.Toast;

import com.liferun.data.Interest;
import com.liferun.qb.QBQueries;
import com.quickblox.core.QBCallback;
import com.quickblox.core.QBSettings;
import com.quickblox.core.result.Result;
import com.quickblox.module.auth.QBAuth;


public class LifeRunApp extends Application {

	public static LifeRunApp APP;
	private ArrayList<Interest> interests = new ArrayList<Interest>();
	
	public static LifeRunApp getInstance() {
		return APP;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();

		APP = this;        
		
	}

	public ArrayList<Interest> getInterests() {
		return interests;
	}
	
	

	
}
