package com.liferun.activities;

import java.util.ArrayList;


import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.liferun.liferun.R;

public class MapActivity extends FragmentActivity implements OnCameraChangeListener, OnMarkerClickListener,
OnConnectionFailedListener,ConnectionCallbacks,LocationListener {

	private GoogleMap myMap;
	private ArrayList<Marker> venuesMarkers;
	
	private Location mCurrentLocation; // last acquired location
	private String mCurrentAddress; // last acquired address on the map
	LocationRequest mLocationRequest; // Define an object that holds accuracy
	// and frequency parameters
	private LocationClient mLocationClient;
	  @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main_map);
	        myMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapId)).getMap();
	        //setUpMap();
	  }
	  
	  @Override
		protected void onStart() {
			super.onStart();
		// Connect the client.
				if (mLocationClient != null)
					mLocationClient.connect();
		
	}
		@Override
		protected void onStop() {
		super.onStop();	
		if(mLocationClient!=null)
			mLocationClient.disconnect();
		}
		
		private boolean servicesConnected() {
			
			// Check that Google Play services is available
			int resultCode = GooglePlayServicesUtil
					.isGooglePlayServicesAvailable(this);

			// If Google Play services is available
			if (ConnectionResult.SUCCESS == resultCode) {

				
				// Continue
				return true;
				// Google Play services was not available for some reason
			} else {
				

				if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
					//showErrorDialog(resultCode);
				} else {
					Toast.makeText(this, "This device is not supported.",
							Toast.LENGTH_LONG).show();
					finish();
				}
				return false;
			}
		}
		
		public void setUpMap(){
			myMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			myMap.getUiSettings().setZoomControlsEnabled(true);
			myMap.getUiSettings().setZoomGesturesEnabled(true);
			myMap.getUiSettings().setCompassEnabled(true);
			myMap.getUiSettings().setMyLocationButtonEnabled(true);
			myMap.setOnCameraChangeListener(this);
			myMap.setOnMarkerClickListener(this);
			
			if (servicesConnected())
				mLocationClient = new LocationClient(this, this, this);
			
		}
		
		
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			//getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}
		
		public Marker placeMarker(int resource, String title,
				String description, LatLng position, String id) {
			
			Marker marker;
					
				marker = myMap.addMarker(new MarkerOptions().position(position)				
					/*.title(title).snippet(description)*/
					.icon(BitmapDescriptorFactory.fromResource(resource)));
			
			
			return marker;

		}
		@Override
		public boolean onMarkerClick(Marker arg0) {
			
			return false;
		}

		@Override
		public void onCameraChange(CameraPosition arg0) {
			
			
		}

		@Override
		public void onLocationChanged(Location arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onConnected(Bundle arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onDisconnected() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onConnectionFailed(ConnectionResult arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderEnabled(String arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			// TODO Auto-generated method stub
			
		}
	  
	  
}
