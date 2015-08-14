/**
 * 
 */
package com.shc.welearn.fragment;

import java.util.ArrayList;

import java.util.HashMap;

import com.shc.welearn.R;
import com.shc.welearn.activity.CommetedActivity;
import com.shc.welearn.activity.CourseSelectionActivity;
import com.shc.welearn.activity.LearnActivity;
import com.shc.welearn.activity.OnlineAnswerActivity;
import com.shc.welearn.activity.OnlineFunActivity;
import com.shc.welearn.activity.PracticeActivity;
import com.shc.welearn.activity.TeachActivity;
import com.shc.welearn.activity.TestActivity;


import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

/**
 * @author CSH 2015-7-24
 */
@SuppressLint("NewApi")
public class LearingFragment extends Fragment {

	private GridView grideView;
	private String titleString;

	/**
	 * 
	 */
	public LearingFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.learingfragment_layout,
				container, false);
		Log.i("LearingFragment", "onCreateView");
		grideView = (GridView) view.findViewById(R.id.mysql_grideview);
		initTitleBar(view);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.i("LearingFragment", "onActivityCreated");
		// 初始化各个itemview的数据
		int[] ids = new int[] { R.drawable.teach, R.drawable.learn,
				R.drawable.practice, R.drawable.test, R.drawable.commeted,
				R.drawable.courseselection, R.drawable.onlineanswer,
				R.drawable.more };
		int[] idStrings = new int[] { R.string.teach, R.string.learn,
				R.string.practice, R.string.test, R.string.commeted,
				R.string.more, R.string.title_activity_online_answer,
				R.string.title_activity_online_fun, };
		ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
		int length = ids.length;
		for (int i = 0; i < length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemImage", ids[i]);
			map.put("ItemText", getResources().getString(idStrings[i]));
			lstImageItem.add(map);
		}

		// 创建适配器
		SimpleAdapter saImageItems = new SimpleAdapter(getActivity(),
				lstImageItem, R.layout.main_item, new String[] { "ItemImage",
						"ItemText" },
				new int[] { R.id.ItemImage, R.id.ItemText });
		grideView.setAdapter(saImageItems);

		// 绑定单击监听器
		grideView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				switch (position) {
				case 0:
					intent.setClass(getActivity(), TeachActivity.class);
					startActivity(intent);
					break;
				case 1:
					intent = new Intent();
					intent.setClass(getActivity(), LearnActivity.class);
					startActivity(intent);
					break;
				case 2:
					intent = new Intent();
					intent.setClass(getActivity(), PracticeActivity.class);
					startActivity(intent);
					break;
				case 3:
					intent = new Intent();
					intent.setClass(getActivity(), TestActivity.class);
					startActivity(intent);
					break;
				case 4:
					intent = new Intent();
					intent.setClass(getActivity(), CommetedActivity.class);
					startActivity(intent);
					break;
				case 5:
					intent = new Intent();
					intent.setClass(getActivity(),
							CourseSelectionActivity.class);
					startActivity(intent);
					getActivity().finish();
					break;
				case 6:
					intent = new Intent();
					intent.setClass(getActivity(), OnlineAnswerActivity.class);
					startActivity(intent);
					break;
				case 7:
					intent = new Intent();
					intent.setClass(getActivity(), OnlineFunActivity.class);
					startActivity(intent);
					break;
				default:
					break;
				}
			}
		});
	}

	/**
	 * 初始化顶部栏
	 * 
	 * @param view
	 */
	private void initTitleBar(View view) {
		// TODO Auto-generated method stub
		ImageView title_img = (ImageView) view.findViewById(R.id.title_img);
		TextView title_text = (TextView) view.findViewById(R.id.title_text);
		title_text.setText(titleString);
	}

	public void setTitleString(String titleString) {
		this.titleString = titleString;
	}

}
