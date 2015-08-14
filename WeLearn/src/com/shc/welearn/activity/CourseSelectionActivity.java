package com.shc.welearn.activity;

import java.util.ArrayList;
import java.util.HashMap;

import com.shc.welearn.R;
import com.shc.welearn.R.layout;
import com.shc.welearn.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class CourseSelectionActivity extends Activity {

	private ListView course_sel_listview;
	private ImageView title_img;
	private TextView title_text;
	private long exitTime = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置全屏，无标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_course_selection);
		course_sel_listview = (ListView) findViewById(R.id.course_sle_gridView);
		initTitleBar();
		initDate();
	}

	/**
	 * 初始化顶部栏
	 */
	private void initTitleBar() {
		// TODO Auto-generated method stub
		title_img = (ImageView) findViewById(R.id.title_img);
		title_text = (TextView) findViewById(R.id.title_text);
		title_text.setText(getResources().getString(
				R.string.course_seletion_title_text));
	}

	private void initDate() {
		// TODO Auto-generated method stub
		int[] itemImage = new int[] { R.drawable.mysql, R.drawable.sqlserver };
		int[] itemText = new int[] { R.string.MySql, R.string.SqlServer };
		ArrayList<HashMap<String, Object>> itemList = new ArrayList<HashMap<String, Object>>();
		int size = itemImage.length;
		for (int i = 0; i < size; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemImage", itemImage[i]);
			map.put("ItemText", getResources().getString(itemText[i]));
			itemList.add(map);

			// 创建适配器
			SimpleAdapter saImageItems = new SimpleAdapter(this, itemList,
					R.layout.course_switch_item, new String[] { "ItemImage",
							"ItemText" }, new int[] { R.id.courseImage,
							R.id.coursetext });

			course_sel_listview.setAdapter(saImageItems);
			// 绑定监听器
			course_sel_listview
					.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							Intent intent = new Intent();
							Bundle bundle = new Bundle();
							switch (position) {
							case 0:
								bundle.putString("Course", getResources()
										.getString(R.string.mysql_title_text));
								intent.putExtras(bundle);
								intent.setClass(CourseSelectionActivity.this,
										ViewPagerFragmentActivity.class);
								startActivity(intent);
								break;
							case 1:
								bundle.putString(
										"Course",
										getResources().getString(
												R.string.sqlserver_title_text));
								intent.putExtras(bundle);
								intent.setClass(CourseSelectionActivity.this,
										ViewPagerFragmentActivity.class);
								startActivity(intent);
								break;
							default:
								break;
							}
						}
					});
		}
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		finish();
	}

	/*
	 * 再按一次退出程序
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "再按一次退出程序",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				finish();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
