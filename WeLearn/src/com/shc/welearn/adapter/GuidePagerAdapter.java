/**
 * 
 */
package com.shc.welearn.adapter;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author CSH 2015-7-27
 * 
 */
public class GuidePagerAdapter extends PagerAdapter {

	/**
	 * 引导界面列表
	 */
	private ArrayList<View> viewsList;

	public GuidePagerAdapter(ArrayList<View> viewsList) {
		// TODO Auto-generated constructor stub
		this.viewsList = viewsList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return viewsList.size();
	}

	/**
	 * 初始化position位置的界面
	 */
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		((ViewPager) container).addView(viewsList.get(position));
		return viewsList.get(position);
	}

	/**
	 * 销毁position位置的界面
	 */
	@Override
	public void destroyItem(View container, int position, Object object) {
		// TODO Auto-generated method stub
		((ViewPager) container).removeView(viewsList.get(position));
	}

	/**
	 * 判断是否由对象生成界面
	 */
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

}
