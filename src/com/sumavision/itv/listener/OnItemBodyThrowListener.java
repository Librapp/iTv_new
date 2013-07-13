package com.sumavision.itv.listener;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;


public class OnItemBodyThrowListener implements OnPageChangeListener
{
	private ViewPager pager;
	private int position;
	private final int MSG_DELAYED = 2;
	private onItemThrowedListener listener;
	
	public OnItemBodyThrowListener(ViewPager pager, int position, onItemThrowedListener listener)
	{
		this.pager = pager;
		this.position = position;
		this.listener = listener;
	}

	@Override
	public void onPageScrollStateChanged(int arg0)
	{
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2)
	{
		
	}

	@Override
	public void onPageSelected(int arg0)
	{
		if(arg0 == 0)
		{
			Message msg = handler.obtainMessage();
			msg.what = MSG_DELAYED;
			handler.sendMessageDelayed(msg, 1000);
			if(listener != null)
				listener.onItemThrowed(position);
		}
		else
		{
			handler.removeMessages(MSG_DELAYED);
		}
	}
	
	private Handler handler = new Handler()
	{

		@Override
		public void handleMessage(Message msg)
		{
			if (msg.what == MSG_DELAYED)
			{
				pager.setCurrentItem(1, true);
			}
		}
		
	};
	
	public interface onItemThrowedListener
	{
		public void onItemThrowed(int selection);
	}

}
