package com.sumavision.itv.listener;

import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;

public class OnTvGestureListener implements OnGestureListener
{

	private View title;
	private View bar;
	private float distanceCacheY = 0;
	private final int RECOVERY = 30;//降噪值

	public OnTvGestureListener(View title, View bar)
	{

		this.title = title;
		this.bar = bar;
	}

	@Override
	public boolean onDown(MotionEvent e)
	{

		return false;
	}

	@Override
	public void onShowPress(MotionEvent e)
	{

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e)
	{

		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
	{
		if(distanceY < -250 || distanceY > 250)//真二
		{
			return false;
		}
		
		if (Math.abs(distanceY - distanceCacheY) > RECOVERY)
		{
			distanceCacheY = distanceY;
			return false;
		}
		
		if (distanceY > 0)
		{
			if (bar != null && bar.getVisibility() == View.VISIBLE)
			{
				bar.setVisibility(View.GONE);
			}

			if (title != null && title.getVisibility() == View.VISIBLE)
			{
				title.setVisibility(View.GONE);
			}
		}
		else
		{
			if (bar != null && bar.getVisibility() == View.GONE)
			{
				bar.setVisibility(View.VISIBLE);
			}

			if (title != null && title.getVisibility() == View.GONE)
			{
				title.setVisibility(View.VISIBLE);
			}
		}
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e)
	{

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
	{

		return false;
	}

}
