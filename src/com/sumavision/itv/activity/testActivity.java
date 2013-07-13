package com.sumavision.itv.activity;

import java.util.ArrayList;

import com.sumavision.itv.R;
import com.sumavision.itv.adapter.ViewPagerAdapter;
import com.sumavision.itv.net.AllLiveProgramParser;
import com.sumavision.itv.net.AllLiveProgramRequest;
import com.sumavision.itv.net.ChannelProgramRequest;
import com.sumavision.itv.utils.NetUtil;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


public class testActivity extends Activity
{
	ViewPager pager;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		pager = (ViewPager) findViewById(R.id.pager);
		
		ArrayList<View> list = new ArrayList<View>();
		ImageView imageView1 = new ImageView(this);
		imageView1.setBackgroundResource(R.drawable.alertview_button_grey);
		list.add(imageView1);
		ImageView imageView2 = new ImageView(this);
		imageView2.setBackgroundResource(R.drawable.alertview_button_blue);
		list.add(imageView2);
		
		ViewPagerAdapter adapter = new ViewPagerAdapter(list);
		pager.setAdapter(adapter);
		pager.setCurrentItem(1);
		pager.setOnPageChangeListener(new OnPageChangeListener()
		{
			
			@Override
			public void onPageSelected(int arg0)
			{
			
				String request = new ChannelProgramRequest(533).make();
				Log.e("View", "onPageScrollStateChanged: " + request);
				String string =  NetUtil.execute(testActivity.this, request, null);
				Log.e("View", "String: " + string);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2)
			{
			
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0)
			{
				
			}
		});
		
	}
}
