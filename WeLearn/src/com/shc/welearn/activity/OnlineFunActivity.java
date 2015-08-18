package com.shc.welearn.activity;

import com.shc.welearn.R;
import com.shc.welearn.R.layout;
import com.shc.welearn.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class OnlineFunActivity extends Activity {

	private WebView mWebView;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置全屏，无标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_online_fun);
		mWebView = (WebView) findViewById(R.id.online_fun_webview);
		initTitleBar();
		progressDialog = ProgressDialog.show(OnlineFunActivity.this, null,
				getString(R.string.loading));
		loadWebViewContent();
	}

	private void loadWebViewContent() {
		// TODO Auto-generated method stub
		WebSettings webSettings = mWebView.getSettings();
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setUseWideViewPort(true);
		// 启用支持javascript
		webSettings.setJavaScriptEnabled(true);
		mWebView.setBackgroundColor(Color.TRANSPARENT);
		// WebView加载web资源
		mWebView.loadUrl("http://douban.fm/");
		// 覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
		mWebView.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				// 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
				view.loadUrl(url);
				return true;
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				if (progressDialog != null) {
					progressDialog.dismiss();
					progressDialog = null;
					
				}
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				if (progressDialog != null) {
					progressDialog.show();
				}
			}
		});
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
		title_text.setText(getResources().getString(
				R.string.title_activity_online_fun));
		title_img
				.setImageDrawable(getResources().getDrawable(R.drawable.learn));
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (mWebView != null) {
			mWebView.destroy();
		}
	}

	/**
	 * 返回事件，如果webview可以返回上一次界面
	 * 
	 * @param keyCoder
	 * @param event
	 * @return
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (mWebView.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK) {
			// goBack()表示返回webView的上一页面
			mWebView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
