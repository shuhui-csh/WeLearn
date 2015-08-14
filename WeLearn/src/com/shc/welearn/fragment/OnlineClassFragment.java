/**
 * 
 */
package com.shc.welearn.fragment;

import com.shc.welearn.R;
import com.shc.welearn.activity.OnlineAnswerActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;

import android.graphics.Bitmap;
import android.graphics.Color;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebView.FindListener;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author CSH 2015-7-24
 */
/**
 * @author CSH
 * 
 */
@SuppressLint("NewApi")
public class OnlineClassFragment extends Fragment {

	public WebView mWebView;
	private ProgressDialog progressDialog;

	/**
	 * 
	 */
	public OnlineClassFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.online_class_fragment_layout,
				container, false);
		mWebView = (WebView) view.findViewById(R.id.online_class_webview);
		initTitleBar(view);
		Log.i("OnlineClassFragment", "onCreateView");
		return view;

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		progressDialog = ProgressDialog.show(getActivity(), null, getActivity()
				.getString(R.string.loading));
		WebSettings webSettings = mWebView.getSettings();
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setUseWideViewPort(true);
		// 启用支持javascript
		webSettings.setJavaScriptEnabled(true);
		mWebView.setBackgroundColor(Color.TRANSPARENT);
		// WebView加载web资源
		mWebView.loadUrl("http://ke.qq.com/");
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

		Log.i("OnlineClassFragment", "onActivityCreated");
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
		title_text.setText(getResources().getString(R.string.online_course));
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
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (mWebView.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK) {
			// goBack()表示返回webView的上一页面
			mWebView.goBack();
			return true;
		}
		return false;
	}

}
