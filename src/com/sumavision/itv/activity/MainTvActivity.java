package com.sumavision.itv.activity;

import com.sumavision.itv.R;
import com.sumavision.itv.adapter.MainTvAdapter;
import com.sumavision.itv.controller.MainTvController;
import com.sumavision.itv.data.DataSet;
import com.sumavision.itv.data.main.iChannel;
import com.sumavision.itv.listener.OnMainTvEventListener;
import com.sumavision.itv.listener.OnTvGestureListener;
import com.sumavision.itv.widget.ScaleListView;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;

/**
 * @author yanzhidan
 * 
 * @description 电视
 * 
 * @time 2013-4-9
 */
public class MainTvActivity extends BaseActivity implements OnTouchListener, OnMainTvEventListener, OnClickListener
{

	private ListView tvList;
	private TextView dummyLabel;
	private MainTvAdapter adapter;
	private RelativeLayout title;
	private GestureDetector gestureDetector;

	@Override
	public void doInitFindView()
	{

		title = (RelativeLayout) findViewById(R.id.title);
		ScaleListView view = (ScaleListView) findViewById(R.id.tv_listview);
		tvList = view.getScaleableView();
		dummyLabel = (TextView) findViewById(R.id.dummy_label);
		
		tvList.setOnTouchListener(this);
	}

	@Override
	public void doInit(Bundle bundle)
	{
		adapter = new MainTvAdapter(this, DataSet.currentTvChannels);
		tvList.setAdapter(adapter);
		
		initDatas();
		MainTvController.setOnMainTvEventListener(this);
		gestureDetector = new GestureDetector(this, new OnTvGestureListener(title, MainTabActivity.getInstance().getTabWidget()));
	}

	@Override
	protected int getContentView()
	{

		return R.layout.activity_main_tv;
	}

	private void initDatas()
	{

		showDialog(R.id.dialog_progress_small);
		MainTvController.getAllLiveProgram();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event)
	{

		switch (v.getId())
		{
			case android.R.id.list:
			{
				if (gestureDetector.onTouchEvent(event))
					return true;
			}

			default:
				return false;
		}
	}

	@Override
	public void eventAllLiveProgramGet(boolean isOk)
	{

		removeDialog(R.id.dialog_progress_small);
		if (isOk)
		{
			adapter.notifyDataSetChanged();
			tvList.setOnScrollListener(new OnTvScrollListener());
		}

	}


	@Override
	public void onClick(View v)
	{

		switch (v.getId())
		{
			case R.id.channel_logo:
				int position = (Integer) v.getTag();
				switchViews(R.id.activity_channel_detail, adapter.getItem(position), null);
				break;

			default:
				break;
		}
	}
	
	class OnTvScrollListener implements OnScrollListener
	{

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState)
		{

		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
		{

			if (firstVisibleItem < 0)
				return;
			if (firstVisibleItem == 0 && dummyLabel.getVisibility() == View.VISIBLE)
			{
				dummyLabel.setVisibility(View.GONE);
			}
			else if (firstVisibleItem != 0 && dummyLabel.getVisibility() != View.VISIBLE)
			{
				dummyLabel.setVisibility(View.VISIBLE);
			}

			try
			{
				iChannel data = adapter.getItem(firstVisibleItem);
				if (!data.isLabel)
				{
					dummyLabel.setText(data.categoryName);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

	}
	
	@Override
	public void onItemThrowed(int selection)
	{
		switchViews(R.id.activity_program_detail, adapter.getItem(selection).channelProgram, null);
	}

}
