package com.shc.welearn.activity;

import java.util.ArrayList;

import java.util.HashMap;

import com.shc.welearn.R;
import com.shc.welearn.R.drawable;
import com.shc.welearn.R.id;
import com.shc.welearn.R.layout;
import com.shc.welearn.R.string;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class LearnActivity extends Activity {

	int learnId;

	@Override
	protected void onStop() {
		super.onStop();
		SharedPreferences mPerferences = getSharedPreferences("learnId", 0);
		Editor mEditor = mPerferences.edit();
		mEditor.putInt("learnedId", learnId - 1).commit();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置全屏，无标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_learn);
		initTitleBar();
		GridView gridview = (GridView) findViewById(R.id.learnview);
		int[] idStrings = new int[] { R.string.first, R.string.second,
				R.string.third, R.string.fourth, R.string.fifth,
				R.string.sixth, R.string.seventh, R.string.eighth,
				R.string.ninth, R.string.tenth, R.string.eleventh,
				R.string.twelfth, R.string.thirteen, R.string.fourteen,
				R.string.fifteen, R.string.sixteen };
		ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 16; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemImagel", R.drawable.item_yellow);// ���ͼ����Դ��ID
			map.put("ItemTextl", getResources().getString(idStrings[i]));
			map.put("numberl", i + 1);
			lstImageItem.add(map);
		}
		SimpleAdapter saImageItems = new SimpleAdapter(this, lstImageItem,
				R.layout.learn_item, new String[] { "ItemImagel", "ItemTextl",
						"numberl" }, new int[] { R.id.ItemImagel,
						R.id.ItemTextl, R.id.numberl });
		gridview.setAdapter(saImageItems);
		gridview.setOnItemClickListener(new ItemClickListener());

		SharedPreferences mPerferences = getSharedPreferences("learnId", 0);
		learnId = mPerferences.getInt("learnedId", 0);

		show();
	}

	void show() {
		AlertDialog.Builder builder = new Builder(this);
		builder.setMessage("已学到第" + ++learnId + "章，是否继续学习？");

		builder.setTitle("学习提示");

		builder.setPositiveButton("确认", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Intent intent = new Intent();
				intent.putExtra("htmlId", "learn" + learnId + "/" + "learn"
						+ learnId + ".html");

				intent.setClass(LearnActivity.this, LearnhtmlActivity.class);
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
			// intent.putExtra("htmlId", "learn" + ++position + "/" + "learn"
			// + position + ".html");
			intent.putExtra("htmlId", ++position);
			learnId = position;
			intent.setClass(LearnActivity.this, LearnhtmlActivity.class);
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
		title_text.setText(getResources().getString(R.string.session_pratice));
		title_img
				.setImageDrawable(getResources().getDrawable(R.drawable.learn));
	}
}
