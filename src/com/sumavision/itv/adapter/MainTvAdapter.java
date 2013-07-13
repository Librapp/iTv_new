package com.sumavision.itv.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sumavision.itv.R;
import com.sumavision.itv.data.main.iChannel;
import com.sumavision.itv.listener.OnItemBodyClickListener;
import com.sumavision.itv.listener.OnItemBodyClickListener.onExpandListener;
import com.sumavision.itv.listener.OnItemBodyThrowListener;
import com.sumavision.itv.listener.OnItemBodyThrowListener.onItemThrowedListener;

public class MainTvAdapter extends IBaseAdapter<iChannel> implements onExpandListener
{

	private final int TYPE_CONTENT = 0;
	private final int TYPE_LABEL = 1;
	private final int VIEW_TYPE = 2;

	public MainTvAdapter(Context context, List<iChannel> objects)
	{

		super(context, objects);
	}

	@Override
	public int getViewTypeCount()
	{

		return VIEW_TYPE;
	}

	@Override
	public int getItemViewType(int position)
	{

		if (getItem(position).isLabel)
		{
			return TYPE_LABEL;
		}
		else
		{
			return TYPE_CONTENT;
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{

		iChannel data = getItem(position);
		ViewHolder1 holder1 = null;
		ViewHolder2 holder2 = null;
		int type = getItemViewType(position);
		if (convertView == null)
		{
			switch (type)
			{
				case TYPE_CONTENT:
				{
					holder1 = new ViewHolder1();
					convertView = inflater.inflate(R.layout.item_tv_list_content, null);
					holder1.pager = (ViewPager) convertView.findViewById(R.id.item_pager);
					ArrayList<View> views = getViews();
					holder1.pager.setAdapter(new ViewPagerAdapter(views));
					holder1.hiddenLayout = (RelativeLayout) convertView.findViewById(R.id.hideaway);
					holder1.logo = (ImageView) views.get(1).findViewById(R.id.channel_logo);
					holder1.name = (TextView) views.get(1).findViewById(R.id.name);
					holder1.btnExpand = (ImageView) views.get(1).findViewById(R.id.btn_expand);
					holder1.btnExpand.setTag(R.id.tag_holder_click, holder1.hiddenLayout);
					convertView.setTag(R.id.tag_holder, holder1);
				}
					break;

				case TYPE_LABEL:
				{
					holder2 = new ViewHolder2();
					convertView = inflater.inflate(R.layout.item_tv_list_label, null);
					holder2.label = (TextView) convertView.findViewById(R.id.label);
					convertView.setTag(R.id.tag_holder, holder2);
				}
					break;

				default:
					break;
			}
		}
		else
		{

			if (type == TYPE_CONTENT)
			{
				holder1 = (ViewHolder1) convertView.getTag(R.id.tag_holder);
				data.isExpanded = false;
			}
			else if (type == TYPE_LABEL)
			{
				holder2 = (ViewHolder2) convertView.getTag(R.id.tag_holder);
			}
		}

		switch (type)
		{
			case TYPE_CONTENT:
			{
				if (holder1 == null)
					break;
				holder1.btnExpand.setTag(R.id.tag_holder_position, position);
				holder1.btnExpand.setOnClickListener(new OnItemBodyClickListener(position, this));
				holder1.pager.setCurrentItem(1, true);
				if (getContext() instanceof onItemThrowedListener)
				{
					holder1.pager.setOnPageChangeListener(new OnItemBodyThrowListener(holder1.pager, position, (onItemThrowedListener) getContext()));
				}
				if (holder1.hiddenLayout.getVisibility() == View.VISIBLE)
				{
					holder1.hiddenLayout.setVisibility(View.GONE);
					holder1.btnExpand.startAnimation((AnimationSet) AnimationUtils.loadAnimation(getContext(), R.anim.img_to_up));
				}

				holder1.name.setText(data.channelName);
				holder1.logo.setOnClickListener((OnClickListener) getContext());
				holder1.logo.setTag(position);
			}
				break;

			case TYPE_LABEL:
			{
				if (holder2 == null)
					break;

				if (holder2.label != null)
				{
					holder2.label.setText(data.categoryName);
				}
			}
				break;

			default:
				break;
		}
		return convertView;
	}

	private ArrayList<View> getViews()
	{

		ArrayList<View> views = new ArrayList<View>();
		RelativeLayout layout0 = (RelativeLayout) inflater.inflate(R.layout.include_tv_item0, null);
		RelativeLayout layout1 = (RelativeLayout) inflater.inflate(R.layout.include_tv_item1, null);
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
		ImageView logo, btnExpand;
	}

	class ViewHolder2
	{

		TextView label;
	}

	@Override
	public void isExpanded(int selection, boolean isExpanded)
	{

		getItem(selection).isExpanded = isExpanded;
	}

}
