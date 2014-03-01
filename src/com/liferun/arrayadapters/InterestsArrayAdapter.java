package com.liferun.arrayadapters;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liferun.liferun.R;

public class InterestsArrayAdapter extends ArrayAdapter<ArrayList<String>>{
	
	public static class ViewHolder {
		public TextView text;
		public ImageView circle;
		public boolean isChecked;
	}
	
	Context mContext;
	ArrayList<String> mItems;
	public InterestsArrayAdapter(Context context,int res, ArrayList<String> objects) {
		super(context,res);
		mContext = context;
		mItems = objects;
	}
	
	@Override
	public int getCount() {
	    // TODO Auto-generated method stub
	    return mItems.size();
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//Log.e("","@@@I AM HERE@@@");
		View rowView = convertView;
		ViewHolder holder=null;
		
	    if (rowView == null) {
	        LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        rowView = vi.inflate(R.layout.interests_array_item, null);
	        
	        holder = new ViewHolder();
			holder.text = (TextView) rowView.findViewById(R.id.textView1);
			holder.circle = (ImageView) rowView.findViewById(R.id.imageView1);
			holder.isChecked = false;
			rowView.setTag(holder);
	    }
	    else{
	    	holder = (ViewHolder) rowView.getTag();
	    }
        
        //RadioButton rb = (RadioButton) rb.findViewById(R.id.)
        holder.text.setText(mItems.get(position));
	    if(holder.isChecked)
	    	holder.circle.setBackground(mContext.getResources().getDrawable(R.drawable.circlegreen));
	    else
	    	holder.circle.setBackground(mContext.getResources().getDrawable(R.drawable.circlegray));
	    return rowView;
	}

}
