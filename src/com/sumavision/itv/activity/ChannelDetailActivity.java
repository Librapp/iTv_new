package com.sumavision.itv.activity;

import com.sumavision.itv.R;
import com.sumavision.itv.adapter.ChannelProgramAdapter;
import com.sumavision.itv.controller.ChannelDetailController;
import com.sumavision.itv.data.C;
import com.sumavision.itv.data.DataSet;
import com.sumavision.itv.listener.OnChannelDetailListener;
import com.sumavision.itv.widget.ScaleListView;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class ChannelDetailActivity extends BaseActivity implements OnClickListener, OnChannelDetailListener
{
	private int channelId;
	private TextView titleTextView;
	private ListView listView;
	private ChannelProgramAdapter adapter;

	@Override
	public void doInitFindView()
	{

		titleTextView = (TextView) findViewById(R.id.title_text);
		ScaleListView view = (ScaleListView) findViewById(R.id.channel_list);
		listView = view.getScaleableView();
		findViewById(R.id.btn_back).setOnClickListener(this);
	}

	@Override
	public void doInit(Bundle bundle)
	{
		adapter = new ChannelProgramAdapter(this, DataSet.currentChannelPrograms);
		listView.setAdapter(adapter);
		
		String name = null;
		bundle = getIntent().getExtras();
		if (bundle != null)
		{
			channelId = bundle.getInt(C.str.channelId, -1);
			name = bundle.getString(C.str.channelName);
		}

		titleTextView.setText("" + name);
		
		ChannelDetailController.setOnChannelDetailListener(this);
		if(channelId != -1)
		{
			showDialog(R.id.dialog_progress_small);
			ChannelDetailController.getChannleProgram(channelId);
		}

	}

	@Override
	protected int getContentView()
	{

		return R.layout.activity_channel_detail;
	}

	@Override
	public void eventGetChannelProgram(boolean isOk)
	{
	
		removeDialog(R.id.dialog_progress_small);
		if(isOk)
		{
			adapter.notifyDataSetChanged();
		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.btn_back:
				finish();
				break;
			default:
				break;
		}
		
	}
	
	@Override
	public void onItemThrowed(int selection)
	{
		switchViews(R.id.activity_program_detail, adapter.getItem(selection), null);
	}

}
