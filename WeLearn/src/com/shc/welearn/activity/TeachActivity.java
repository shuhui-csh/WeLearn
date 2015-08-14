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
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class TeachActivity extends Activity {

	int teachId;

	@Override
	protected void onStop() {
		super.onStop();
		SharedPreferences mPerferences = getSharedPreferences("teachId", 0);
		Editor mEditor = mPerferences.edit();
		mEditor.putInt("teachId", teachId - 1).commit();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置全屏，无标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_teach);
		GridView gridview = (GridView) findViewById(R.id.teachview);
		initTitleBar();
		int[] idStrings = new int[] { R.string.first, R.string.second,
				R.string.third, R.string.fourth, R.string.fifth,
				R.string.sixth, R.string.seventh, R.string.eighth,
				R.string.ninth, R.string.tenth, R.string.eleventh,
				R.string.twelfth, R.string.thirteen, R.string.fourteen,
				R.string.fifteen, R.string.sixteen };
		ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 16; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemImaget", R.drawable.item_blue);
			map.put("ItemTextt", getResources().getString(idStrings[i]));
			map.put("numbert", i + 1);
			lstImageItem.add(map);
		}
		SimpleAdapter saImageItems = new SimpleAdapter(this, lstImageItem,
				R.layout.teach_item, new String[] { "ItemImaget", "ItemTextt",
						"numbert" }, new int[] { R.id.ItemImaget,
						R.id.ItemTextt, R.id.numbert });
		gridview.setAdapter(saImageItems);
		gridview.setOnItemClickListener(new ItemClickListener());

		SharedPreferences mPerferences = getSharedPreferences("teachId", 0);
		teachId = mPerferences.getInt("teachId", 0);

		show();
	}

	void show() {
		AlertDialog.Builder builder = new Builder(this);
		builder.setMessage("已学到第" + ++teachId + "章，是否继续学习？");

		builder.setTitle("学习提示");
		builder.setPositiveButton("确认", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Intent intent = new Intent();
				intent.putExtra("htmlId", teachId );
				intent.setClass(TeachActivity.this, TeachhtmlActivity.class);
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
			// intent.putExtra("htmlId", "teach" + ++position + ".html");
			intent.putExtra("htmlId", ++position);
			teachId = position;
			intent.setClass(TeachActivity.this, TeachhtmlActivity.class);
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
		title_text.setText(getResources().getString(R.string.session_learn));
		title_img
				.setImageDrawable(getResources().getDrawable(R.drawable.teach));
	}
}
