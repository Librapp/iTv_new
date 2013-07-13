package com.sumavision.itv.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sumavision.itv.R;
import com.sumavision.itv.data.main.iProgram;
import com.sumavision.itv.listener.OnItemBodyClickListener;
import com.sumavision.itv.listener.OnItemBodyClickListener.onExpandListener;
import com.sumavision.itv.listener.OnItemBodyThrowListener;
import com.sumavision.itv.listener.OnItemBodyThrowListener.onItemThrowedListener;

public class ChannelProgramAdapter extends IBaseAdapter<iProgram> implements onExpandListener
{

	public ChannelProgramAdapter(Context context, List<iProgram> objects)
	{

		super(context, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{

		iProgram data = getItem(position);
		ViewHolder1 holder1 = null;
		if (convertView == null)
		{
			holder1 = new ViewHolder1();
			convertView = inflater.inflate(R.layout.item_channeldetail_list_content, null);
			holder1.pager = (ViewPager) convertView.findViewById(R.id.item_pager);
			ArrayList<View> views = getViews();
			holder1.pager.setTag(R.id.tag_holder_click, views.get(1));
			holder1.pager.setAdapter(new ViewPagerAdapter(views));
			holder1.hiddenLayout = (RelativeLayout) convertView.findViewById(R.id.hideaway);
			holder1.logo = (ImageView) views.get(1).findViewById(R.id.channel_logo);
			holder1.name = (TextView) views.get(1).findViewById(R.id.name);
			convertView.setTag(R.id.tag_holder, holder1);
		}
		else
		{
			holder1 = (ViewHolder1) convertView.getTag(R.id.tag_holder);
			data.isExpanded = false;
		}

		((View) holder1.pager.getTag(R.id.tag_holder_click)).setOnClickListener(new OnItemBodyClickListener(position, this));
		holder1.pager.setCurrentItem(1, true);
		if (getContext() instanceof onItemThrowedListener)
		{
			holder1.pager.setOnPageChangeListener(new OnItemBodyThrowListener(holder1.pager, position, (onItemThrowedListener) getContext()));
		}
		if (data.isExpanded)
		{
			holder1.hiddenLayout.setVisibility(View.VISIBLE);
		}
		else
		{
			holder1.hiddenLayout.setVisibility(View.GONE);
		}

		switch (data.dayFlag)
		{
			case -2:
			case -1:
				holder1.logo.setImageResource(R.drawable.channel_icon_played);
				break;

			case 0:
				holder1.logo.setImageResource(R.drawable.channel_icon_plaing);
				break;
			case 1:
				holder1.logo.setImageResource(R.drawable.channel_icon_willplay);
				break;

			default:
				break;
		}

		holder1.name.setText(data.programName);
		holder1.logo.setOnClickListener((OnClickListener) getContext());
		holder1.logo.setTag(position);

		return convertView;
	}

	private ArrayList<View> getViews()
	{

		ArrayList<View> views = new ArrayList<View>();
		RelativeLayout layout0 = (RelativeLayout) inflater.inflate(R.layout.include_tv_item0, null);
		RelativeLayout layout1 = (RelativeLayout) inflater.inflate(R.layout.include_channeldetail_item1, null);
		views.add(layout0);
		views.add(layout1);
		return views;
	}

	class ViewHolder1
	{

		RelativeLayout bodyLayout;
		RelativeLayout hiddenLayout;
		ViewPager pager;
		TextView name, label;
		ImageView logo;
	}

	@Override
	public void isExpanded(int selection, boolean isExpanded)
	{

		getItem(selection).isExpanded = isExpanded;

	}

}
