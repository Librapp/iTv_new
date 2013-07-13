package com.sumavision.itv.activity;

import com.sumavision.itv.R;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TabHost;

/**
 * @author yanzhidan
 * 
 * @description 主页
 * 
 * @time 2013-4-9
 */
public class MainTabActivity extends TabActivity
{

	private static MainTabActivity mInstance;

	public static MainTabActivity getInstance()
	{

		return mInstance;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		mInstance = this;
		setContentView(R.layout.activity_main_tab);

		Intent radio = new Intent(this, MainRadioActivity.class);
		Intent movie = new Intent(this, MainMovieActivity.class);
		Intent tv = new Intent(this, MainTvActivity.class);
		Intent more = new Intent(this, MainMoreActivity.class);

		TabHost tabHost = this.getTabHost();
		RadioButton view = new RadioButton(this);
		view.setButtonDrawable(R.drawable.d_null);
		view.setBackgroundResource(R.drawable.tab_radio_bg);
		tabHost.addTab(tabHost.newTabSpec("radio").setIndicator(view).setContent(radio));
		view = new RadioButton(this);
		view.setButtonDrawable(R.drawable.d_null);
		view.setBackgroundResource(R.drawable.tab_movie_bg);
		tabHost.addTab(tabHost.newTabSpec("movie").setIndicator(view).setContent(movie));
		view = new RadioButton(this);
		view.setButtonDrawable(R.drawable.d_null);
		view.setBackgroundResource(R.drawable.tab_tv_bg);
		tabHost.addTab(tabHost.newTabSpec("tv").setIndicator(view).setContent(tv));
		view = new RadioButton(this);
		view.setButtonDrawable(R.drawable.d_null);
		view.setBackgroundResource(R.drawable.tab_more_bg);
		tabHost.addTab(tabHost.newTabSpec("more").setIndicator(view).setContent(more));
	}
	
//	System.exit(0);
	
}
