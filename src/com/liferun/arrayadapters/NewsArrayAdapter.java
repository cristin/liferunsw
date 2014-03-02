package com.liferun.arrayadapters;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liferun.arrayadapters.InterestsArrayAdapter.ViewHolder;
import com.liferun.data.Challenge;
import com.liferun.liferun.R;

public class NewsArrayAdapter extends ArrayAdapter<ArrayList<Challenge>>{
	
	public static class ViewHolder {
		public TextView name;
		public TextView descr;
		public boolean isChecked;
	}
	
	Context mContext;
	ArrayList<Challenge> mItems;
	public NewsArrayAdapter(Context context,int res, ArrayList<Challenge> objects) {
		super(context,res);
		mContext = context;
		mItems = objects;
	}
	
	@Override
	public int getCount() {
	    // TODO Auto-generated method stub
	    return mItems.size();
	}

	
	@SuppressLint("NewApi")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//Log.e("","@@@I AM HERE@@@");
		View rowView = convertView;
		ViewHolder holder=null;
		
	    if (rowView == null) {
	        LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        rowView = vi.inflate(R.layout.newsfeed_item, null);
	        
	        holder = new ViewHolder();
			holder.name = (TextView) rowView.findViewById(R.id.challeng_name);
			holder.descr = (TextView) rowView.findViewById(R.id.descript);
			holder.isChecked = false;
			rowView.setTag(holder);
	    }
	    else{
	    	holder = (ViewHolder) rowView.getTag();
	    }
        
        //RadioButton rb = (RadioButton) rb.findViewById(R.id.)
        holder.name.setText(mItems.get(position).getName());
        holder.descr.setText(mItems.get(position).getDescription());
        
        /*
	    if(holder.isChecked)
	    	holder.circle.setBackground(mContext.getResources().getDrawable(R.drawable.circlegreen));
	    else
	    	holder.circle.setBackground(mContext.getResources().getDrawable(R.drawable.circlegray));
	    	*/
	    return rowView;
	}

}
