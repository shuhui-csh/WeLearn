package com.shc.welearn.activity;

import org.litepal.crud.DataSupport;

import com.shc.welearn.R;
import com.shc.welearn.R.id;
import com.shc.welearn.R.layout;
import com.shc.welearn.model.CourseSourse;
import com.shc.welearn.model.PracticeSource;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LearnhtmlActivity extends Activity {
	WebView mWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置全屏，无标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_learnhtml);
		initTitleBar();
		mWebView = (WebView) findViewById(R.id.learnhtml);
		Intent intent = getIntent();
		int practice_id = intent.getIntExtra("htmlId", 1);
		// String path = intent.getStringExtra("htmlId");
		// readHtmlFormAssets(path);
		String course_path = "file:///android_asset/learn/" + "learn"
				+ practice_id + "/" + "learn" + practice_id + ".html";
		readHtmlFormAssets(course_path);
		// 往数据库里添加一条记录
		PracticeSource practice = DataSupport.find(PracticeSource.class,
				practice_id);
		if (practice != null) {
			// 修改点击次数
			practice.setPointNum(practice.getPointNum() + 1);
			practice.update(practice_id);
			// ContentValues values = new ContentValues();
			// values.put("pointNum", practice.getPointNum() + 1);
			// DataSupport.update(CourseSourse.class, values, practice_id);
			// Toast.makeText(
			// LearnhtmlActivity.this,
			// "第" + practice_id + "章 章节练习" + "存储次数："
			// + practice.getPointNum(), Toast.LENGTH_SHORT)
			// .show();
		} else {
			practice = new PracticeSource();
			practice.setId(practice_id);
			practice.setPracticeName("第" + practice_id + "章 章节练习");
			practice.setPracticePath(course_path);
			practice.setPointNum(1);
			practice.save();
			// if (practice.save()) {
			// Toast.makeText(LearnhtmlActivity.this,
			// "第" + practice_id + "章 章节练习" + "存储成功",
			// Toast.LENGTH_SHORT).show();
			// } else {
			// Toast.makeText(LearnhtmlActivity.this,
			// "第" + practice_id + "章 章节练习" + "存储失败",
			// Toast.LENGTH_SHORT).show();
			// }
		}

	}

	private void readHtmlFormAssets(String path) {
		WebSettings webSettings = mWebView.getSettings();
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setUseWideViewPort(true);

		mWebView.setBackgroundColor(Color.TRANSPARENT);
		mWebView.loadUrl(path);
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
