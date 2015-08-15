package com.shc.welearn.activity;

import java.util.ArrayList;

import java.util.HashMap;

import com.shc.welearn.R;
import com.shc.welearn.R.drawable;
import com.shc.welearn.R.id;
import com.shc.welearn.R.layout;
import com.shc.welearn.R.string;
import com.shc.welearn.util.PreferenceUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class PracticeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置全屏，无标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_practice);
		initTitleBar();
		GridView gridview = (GridView) findViewById(R.id.practiceview);
		int[] idStrings = new int[] { R.string.first, R.string.second,
				R.string.third, R.string.fourth, R.string.fifth,
				R.string.sixth, R.string.seventh, R.string.eighth,
				R.string.ninth, R.string.tenth, R.string.eleventh,
				R.string.twelfth, R.string.thirteen, R.string.fourteen,
				R.string.fifteen, R.string.sixteen };
		ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 16; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemImagep", R.drawable.item_blue);
			map.put("ItemTextp", getResources().getString(idStrings[i]));
			map.put("numberp", i + 1);
			lstImageItem.add(map);
		}
		SimpleAdapter saImageItems = new SimpleAdapter(this, lstImageItem,
				R.layout.practice_item, new String[] { "ItemImagep",
						"ItemTextp", "numberp" }, new int[] { R.id.ItemImagep,
						R.id.ItemTextp, R.id.numberp });
		gridview.setAdapter(saImageItems);
		gridview.setOnItemClickListener(new ItemClickListener());

		findMaxErrorRatio();
	}

	void findMaxErrorRatio() {
		float[] ratio = new float[16];
		float max = 0;
		int index = 1;
		for (int i = 0; i < ratio.length; i++) {
			ratio[i] = PreferenceUtil.getErrorRatio(this, "choices" + (i + 1));
			if (max < ratio[i]) {
				max = ratio[i];
				index = i + 1;
			}
			// Log.d("------", "i=" + ratio[i]);
		}

		show(index);
		// Log.d("------", "max=" + max);
	}

	void show(final int max) {
		AlertDialog.Builder builder = new Builder(this);
		builder.setMessage("第" + max + "章做错率最高，是否重新学习第" + max + "章？");

		builder.setTitle("提示");

		builder.setPositiveButton("确认", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Intent intent = new Intent();
				intent.setClass(PracticeActivity.this, ChoiceActivity.class);
				intent.putExtra("json_file_name", "choices/choices" + max
						+ ".json");
				intent.putExtra("prefers_file_name", "choices" + max);
				startActivity(intent);
			}
		});

		builder.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		builder.create().show();
	}

	class ItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Intent intent = new Intent();
			intent.setClass(PracticeActivity.this, ChoiceActivity.class);
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
		title_text.setText(getResources().getString(R.string.session_test));
		title_img.setImageDrawable(getResources().getDrawable(
				R.drawable.practice));
	}
}
