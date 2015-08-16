package com.shc.welearn.activity;

import java.util.ArrayList;

import java.util.HashMap;

import com.shc.welearn.R;
import com.shc.welearn.R.drawable;
import com.shc.welearn.R.id;
import com.shc.welearn.R.layout;
import com.shc.welearn.R.string;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class CommetedActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置全屏，无标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_commeted);
		initTitleBar();
		GridView gridview = (GridView) findViewById(R.id.commetedview);
		int[] idStrings = new int[] { R.string.first, R.string.second,
				R.string.third, R.string.fourth, R.string.fifth,
				R.string.sixth, R.string.seventh, R.string.eighth,
				R.string.ninth, R.string.tenth, R.string.eleventh,
				R.string.twelfth, R.string.thirteen, R.string.fourteen,
				R.string.fifteen, R.string.sixteen };
		ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 16; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemImagec", R.drawable.item_blue);// ���ͼ����Դ��ID
			map.put("ItemTextc", getResources().getString(idStrings[i]));
			map.put("numberc", i + 1);
			lstImageItem.add(map);
		}
		SimpleAdapter saImageItems = new SimpleAdapter(this, lstImageItem,
				R.layout.commeted_item, new String[] { "ItemImagec",
						"ItemTextc", "numberc" }, new int[] { R.id.ItemImagec,
						R.id.ItemTextc, R.id.numberc });
		gridview.setAdapter(saImageItems);
		gridview.setOnItemClickListener(new ItemClickListener());
	}

	class ItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Intent intent = new Intent();
			intent.setClass(CommetedActivity.this, EasyErrorActivity.class);
			intent.putExtra("json_file_name", "choices/choices" + ++position
					+ ".json");
			intent.putExtra("prefers_file_name", "choices" + position);
			startActivity(intent);
		}
	}

	/**
	 * 初始化顶部栏
	 * 
	 * @param view
	 */
	private void initTitleBar() {
		// TODO Auto-generated method stub
		ImageView title_img = (ImageView) findViewById(R.id.title_img);
		TextView title_text = (TextView) findViewById(R.id.title_text);
		title_text.setText(getResources().getString(R.string.commeted));
		title_img.setImageDrawable(getResources().getDrawable(
				R.drawable.commeted));
	}
}
