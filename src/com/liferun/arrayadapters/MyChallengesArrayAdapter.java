package com.liferun.arrayadapters;

import java.util.ArrayList;

import com.liferun.arrayadapters.InterestsArrayAdapter.ViewHolder;
import com.liferun.liferun.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyChallengesArrayAdapter extends ArrayAdapter<ArrayList<String>>{
	
	public static class ViewHolder {
		public TextView text;
	}
	
	Context mContext;
	ArrayList<String> mItems;
	public MyChallengesArrayAdapter(Context context,int res, ArrayList<String> objects) {
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
	        rowView = vi.inflate(R.layout.challenges_array_item, null);
	        
	        holder = new ViewHolder();
			holder.text = (TextView) rowView.findViewById(R.id.textView1);
			rowView.setTag(holder);
	    }
	    else{
	    	holder = (ViewHolder) rowView.getTag();
	    }
        
        //RadioButton rb = (RadioButton) rb.findViewById(R.id.)
        holder.text.setText(mItems.get(position));
	    return rowView;
	}
}
