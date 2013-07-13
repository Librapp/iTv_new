package com.sumavision.itv.listener;

import com.sumavision.itv.R;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class OnItemBodyClickListener implements OnClickListener
{

	private static View currentExpandview;
	private static View currentBtnView;
	private static boolean isExpanded = false;
	private static int expandedSelection = -1;
	private int selection;
	private onExpandListener listener;

	public OnItemBodyClickListener(int position, onExpandListener listener)
	{

		this.selection = position;
		this.listener = listener;
	}

	@Override
	public void onClick(View v)
	{

		if (!(v instanceof ImageView))
			return;

		View layout = (View) (v.getTag(R.id.tag_holder_click));
		if (selection == expandedSelection)
			isExpanded = false;
		if (isExpanded)
		{
			if (currentExpandview != null && currentBtnView != null)
			{
				isExpanded = false;
				currentExpandview.setVisibility(View.GONE);
				currentBtnView.startAnimation((AnimationSet) AnimationUtils.loadAnimation(v.getContext(), R.anim.img_to_up));
				if (listener != null)
					listener.isExpanded(expandedSelection, false);
				expandedSelection = -1;
				currentExpandview = null;
				currentBtnView = null;
			}
		}
		else
		{
			if (layout.getVisibility() == View.VISIBLE)
			{
				isExpanded = false;
				layout.setVisibility(View.GONE);
				v.startAnimation((AnimationSet) AnimationUtils.loadAnimation(v.getContext(), R.anim.img_to_up));
				if (listener != null)
					listener.isExpanded(selection, false);
			}
			else
			{
				isExpanded = true;
				currentExpandview = layout;
				expandedSelection = selection;
				currentBtnView = v;
				layout.setVisibility(View.VISIBLE);
				v.startAnimation((AnimationSet) AnimationUtils.loadAnimation(v.getContext(), R.anim.img_to_down));
				if (listener != null)
					listener.isExpanded(selection, true);
			}
		}

	}

	public interface onExpandListener
	{

		public void isExpanded(int selection, boolean isExpanded);
	}

}
