package com.shc.welearn.activity;

import org.litepal.crud.DataSupport;

import com.shc.welearn.R;
import com.shc.welearn.R.id;
import com.shc.welearn.R.layout;
import com.shc.welearn.model.CourseSourse;

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

public class TeachhtmlActivity extends Activity {
	WebView mWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置全屏，无标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_teachhtml);
		initTitleBar();
		mWebView = (WebView) findViewById(R.id.teachhtml);
		Intent intent = getIntent();
		int course_id = intent.getIntExtra("htmlId", 1);
		// String path = intent.getStringExtra("htmlId");
		// readHtmlFormAssets(path);
		String course_path = "file:///android_asset/teach/" + "teach"
				+ course_id + ".html";
		readHtmlFormAssets(course_path);
		// 往数据库里添加一条记录
		CourseSourse course = DataSupport.find(CourseSourse.class, course_id);
		if (course != null) {
			// 修改点击次数
			course.setPointNum(course.getPointNum() + 1);
			course.update(course_id);
			// ContentValues values = new ContentValues();
			// values.put("pointNum", course.getPointNum() + 1);
			// DataSupport.update(CourseSourse.class, values, course_id);
			// Toast.makeText(
			// TeachhtmlActivity.this,
			// "第" + course_id + "章 章节学习" + "存储次数：" + course.getPointNum(),
			// Toast.LENGTH_SHORT).show();
		} else {
			course = new CourseSourse();
			course.setId(course_id);
			course.setCourseName("第" + course_id + "章 章节学习");
			course.setCoursePath(course_path);
			course.setPointNum(1);
			course.save();
			// if (course.save()) {
			// Toast.makeText(TeachhtmlActivity.this,
			// "第" + course_id + "章 章节学习" + "存储成功", Toast.LENGTH_SHORT)
			// .show();
			// } else {
			// Toast.makeText(TeachhtmlActivity.this,
			// "第" + course_id + "章 章节学习" + "存储失败", Toast.LENGTH_SHORT)
			// .show();
			// }
		}

	}

	private void readHtmlFormAssets(String path) {
		WebSettings webSettings = mWebView.getSettings();

		webSettings.setLoadWithOverviewMode(true);
		webSettings.setUseWideViewPort(true);

		mWebView.setBackgroundColor(Color.TRANSPARENT);
		// mWebView.loadUrl("file:///android_asset/teach/" + path);
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
		title_text.setText(getResources().getString(R.string.session_learn));
		title_img
				.setImageDrawable(getResources().getDrawable(R.drawable.teach));
	}
}
