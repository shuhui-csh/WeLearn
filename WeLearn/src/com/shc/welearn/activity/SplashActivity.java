package com.shc.welearn.activity;

import com.shc.welearn.R;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @author CSH 2015-7-26 3秒的欢迎界面
 */
public class SplashActivity extends Activity {

	/**
	 * 延迟三秒跳转
	 */
	private static final long SPLASH_DELAY_MILLIS = 3000;
	/**
	 * shareprefenrence引导界面的存储字段
	 */
	private static final String SHAREPREFERENCE_NAME = "Guide";
	/**
	 * 是否第一次进入
	 */
	private boolean isFirstIn = false;
	/**
	 * 跳转到主界面home的标记号
	 */
	private static final int GO_TO_HOME = 1000;
	/**
	 * 跳转到引导界面GuideActivity的标记号
	 */
	private static final int GO_TO_GUIDE = 2000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置全屏，无标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		// 去掉window背景前后的具体性能会大不一样，过度绘制
		getWindow().setBackgroundDrawable(null);
		inint();

	}

	/**
	 * 初始化
	 */
	private void inint() {
		// TODO Auto-generated method stub
		// 读取SharedPreferences中需要的数据
		// 使用SharedPreferences来记录程序的使用次数
		SharedPreferences preference = getSharedPreferences(
				SHAREPREFERENCE_NAME, MODE_PRIVATE);
		// 取得相应的值，如果没有该值，说明还未写入，用true作为默认值
		isFirstIn = preference.getBoolean("isFirstIn", true);
		// 判断程序与第几次运行，如果是第一次运行则跳转到引导界面，否则跳转到主界面
		if (!isFirstIn) {
			// 使用Handler的postDelayed方法，3秒后执行跳转到主界面HomeActivity
			myhandler.sendEmptyMessageDelayed(GO_TO_HOME, SPLASH_DELAY_MILLIS);
		} else {
			myhandler.sendEmptyMessageDelayed(GO_TO_GUIDE, SPLASH_DELAY_MILLIS);
			// 写入数据已经引导过了，下次启动就不会再走引导界面了
			Editor edit = preference.edit();
			edit.putBoolean("isFirstIn", false);
			edit.commit();
		}
	}

	private Handler myhandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case GO_TO_HOME:
				gotoHome();
				break;
			case GO_TO_GUIDE:
				gotoGuide();
				break;
			}
		}

		/**
		 * 跳转到引导界面
		 */
		private void gotoGuide() {
			// TODO Auto-generated method stub
			Intent inttent = new Intent(SplashActivity.this,
					GuideActivity.class);
			startActivity(inttent);
			SplashActivity.this.finish();
		}

		/**
		 * 跳转到主界面home
		 */
		private void gotoHome() {
			// TODO Auto-generated method stub
			Intent intent = new Intent(SplashActivity.this,
					CourseSelectionActivity.class);
			startActivity(intent);
			SplashActivity.this.finish();
		}

	};

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		finish();
		super.onStop();

	}

}
