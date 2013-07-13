package com.sumavision.itv.adapter;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * @author yanzhidan
 * @createTime 2013-4-22
 *
 */
public class ViewPagerAdapter extends PagerAdapter
{
	private ArrayList<View> viewList;
	public ViewPagerAdapter(ArrayList<View> list)
	{
		viewList = list;
	}

	@Override
	public int getCount()
	{

		return viewList.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1)
	{

		return arg0 == arg1;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object)
	{
		container.removeView(viewList.get(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position)
	{	
		View view = viewList.get(position);
		container.addView(view);
		
		return view;
	}
	
}
