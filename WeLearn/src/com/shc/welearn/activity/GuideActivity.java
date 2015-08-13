package com.shc.welearn.activity;

import java.util.ArrayList;

import com.shc.welearn.R;
import com.shc.welearn.adapter.GuidePagerAdapter;

import android.R.integer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GuideActivity extends Activity {

	/**
	 * 装载引导界面的viewpager
	 */
	private ViewPager viewpager;
	/**
	 * 当前页面的标号
	 */
	private int currentIndex;
	/**
	 * 小点数组
	 */
	private ImageView[] points;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置全屏，无标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide);
		intitView();
	}

	/**
	 * 初始化控件
	 */
	private void intitView() {
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(this);

		View guide_view3 = inflater.inflate(R.layout.guide_page3, null);
		View guide_view4 = inflater.inflate(R.layout.guide_page4, null);
		// 通过guide_view4找到其上的开始按钮
		TextView start_bn = (TextView) guide_view4.findViewById(R.id.ok);
		viewpager = (ViewPager) findViewById(R.id.guidepager);

		ArrayList<View> viewList = new ArrayList<View>();

		viewList.add(guide_view3);
		viewList.add(guide_view4);

		GuidePagerAdapter guidePagerAdapter = new GuidePagerAdapter(viewList);
		viewpager.setAdapter(guidePagerAdapter);
		viewpager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				// 设置为当前页面的点
				setCurrntPoint(arg0);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		// 为开始按钮设置点击事件跳转到home主界面
		start_bn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(GuideActivity.this,
						CourseSelectionActivity.class);
				startActivity(intent);
				GuideActivity.this.finish();
			}
		});

		// 初始化底部小点
		initPoint(viewList.size());
	}

	/**
	 * 初始化底部小点
	 * 
	 * @param size
	 */
	private void initPoint(int size) {
		// TODO Auto-generated method stub
		LinearLayout pointLayout = (LinearLayout) findViewById(R.id.point);
		points = new ImageView[size];
		// 循环取得小点图片
		for (int i = 0; i < size; i++) {
			// 得到一个LinearLayout下面的每一个子元素
			points[i] = (ImageView) pointLayout.getChildAt(i);
			// 默认都设为灰色
			points[i].setEnabled(true);
			// 给每个小点设置监听
			points[i].setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					int position = (Integer) v.getTag();
					setCurrentView(position);
				}

			});
			// 设置位置tag，方便取出与当前位置对应
			points[i].setTag(i);
		}
		// 设置当面默认的位置
		currentIndex = 0;
		// 设置为白色，即选中状态
		points[currentIndex].setEnabled(false);
	}

	/**
	 * 设置当前的点
	 * 
	 * @param arg0
	 */
	private void setCurrntPoint(int positon) {
		if (positon < 0 || positon > 3 || currentIndex == positon) {
			return;
		}
		points[positon].setEnabled(false);
		points[currentIndex].setEnabled(true);

		currentIndex = positon;
	}

	/**
	 * 设置当前的view
	 * 
	 * @param position
	 */
	private void setCurrentView(int position) {
		// TODO Auto-generated method stub
		if (position < 0 || position > 3) {
			return;
		}
		viewpager.setCurrentItem(position);
	}
}
