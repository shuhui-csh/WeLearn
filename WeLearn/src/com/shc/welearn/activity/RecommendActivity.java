package com.shc.welearn.activity;

import com.shc.welearn.R;
import com.shc.welearn.R.layout;
import com.shc.welearn.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class RecommendActivity extends Activity {

	private WebView mWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置全屏，无标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_recommend);
		initTitleBar();
		mWebView = (WebView) findViewById(R.id.recommendhtml);
		Intent intent = getIntent();
		String path = intent.getStringExtra("path");
		readHtmlFormAssets(path);
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
		title_text.setText(getResources().getString(R.string.personal_advice));
		// title_img
		// .setImageDrawable(getResources().getDrawable(R.drawable.learn));
	}

	private void readHtmlFormAssets(String path) {
		WebSettings webSettings = mWebView.getSettings();
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setUseWideViewPort(true);

		mWebView.setBackgroundColor(Color.TRANSPARENT);
		mWebView.loadUrl(path);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (mWebView != null) {
			mWebView.destroy();
		}
	}

}
