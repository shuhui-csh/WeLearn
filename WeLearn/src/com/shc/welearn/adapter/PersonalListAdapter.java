/**
 * 
 */
package com.shc.welearn.adapter;

import java.util.ArrayList;
import java.util.List;

import com.shc.welearn.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author CSH
 * 
 */
public class PersonalListAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<String> mylist;
	private LayoutInflater mInflater;

	/**
	 * @param context
	 * @param mylist
	 */
	public PersonalListAdapter(Context context, ArrayList<String> mylist) {
		super();
		this.context = context;
		this.mylist = mylist;
		// 根据context上下文加载布局
		if (context != null) {
			this.mInflater = LayoutInflater.from(context);
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mylist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.course_switch_item,
					parent, false);
			holder = new ViewHolder();
			holder.img = (ImageView) convertView.findViewById(R.id.courseImage);
			holder.text = (TextView) convertView.findViewById(R.id.coursetext);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.img.setTag(mylist.get(position));
		holder.img.setImageDrawable(context.getResources().getDrawable(
				R.drawable.learn));
		holder.text.setTag(mylist.get(position));
		holder.text.setText(mylist.get(position));

		return convertView;
	}

	private static class ViewHolder {
		ImageView img;
		TextView text;

	}
}
