/**
 * 
 */
package com.shc.welearn.fragment;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import com.shc.welearn.R;
import com.shc.welearn.activity.ChoiceActivity;
import com.shc.welearn.activity.CourseSelectionActivity;
import com.shc.welearn.activity.PracticeActivity;
import com.shc.welearn.activity.RecommendActivity;
import com.shc.welearn.activity.ViewPagerFragmentActivity;
import com.shc.welearn.adapter.PersonalListAdapter;
import com.shc.welearn.model.CourseSourse;
import com.shc.welearn.model.PracticeSource;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * @author CSH 2015-7-24
 */
@SuppressLint("NewApi")
public class PersonalFragment extends Fragment {

	private ListView mlistView;
	private ArrayList<String> path_list;

	/**
	 * 
	 */
	public PersonalFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.i("PersonalFragment", "onCreateView");
		View view = inflater.inflate(R.layout.personalfragment_layout,
				container, false);
		mlistView = (ListView) view.findViewById(R.id.personal_listview);
		initTitleBar(view);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		loadData();
		mlistView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				intent.putExtra("path", path_list.get(position));
				intent.setClass(getActivity(), RecommendActivity.class);
				startActivity(intent);
			}
		});
	}

	private void loadData() {
		// TODO Auto-generated method stub
		List<CourseSourse> course_list = DataSupport
				.findAll(CourseSourse.class);
		List<PracticeSource> practicelist = DataSupport
				.findAll(PracticeSource.class);
		path_list = new ArrayList<String>();
		ArrayList<String> name_list = new ArrayList<String>();
		int length = course_list.size();
		for (int i = 0; i < length; i++) {
			path_list.add(course_list.get(i).getCoursePath());
			name_list.add(course_list.get(i).getCourseName());
		}
		length = practicelist.size();
		for (int i = 0; i < length; i++) {
			path_list.add(practicelist.get(i).getPracticePath());
			name_list.add(practicelist.get(i).getPracticeName());
		}
		PersonalListAdapter adapter = new PersonalListAdapter(getActivity(),
				name_list);
		mlistView.setAdapter(adapter);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		loadData();
		super.onResume();
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
		title_text.setText(getResources().getString(R.string.personal_advice));
	}
}
