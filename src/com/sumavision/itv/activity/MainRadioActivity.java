package com.sumavision.itv.activity;

import com.sumavision.itv.R;

import android.os.Bundle;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author yanzhidan
 * 
 * @description 我的电台
 * 
 * @time 2013-4-9
 */
public class MainRadioActivity extends BaseActivity implements OnClickListener
{

	private TextView channelText, favoriText, pointText;
	private ImageView animBtn;
	private int currentPosition, duration;

	@Override
	public void doInitFindView()
	{

		channelText = (TextView) findViewById(R.id.channel);
		favoriText = (TextView) findViewById(R.id.favorite);
		pointText = (TextView) findViewById(R.id.point);
		animBtn = (ImageView) findViewById(R.id.anima_btn);

		channelText.setOnClickListener(this);
		favoriText.setOnClickListener(this);
		pointText.setOnClickListener(this);

	}

	@Override
	public void doInit(Bundle bundle)
	{
		duration = getWindowManager().getDefaultDisplay().getWidth() / 3;
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(duration, LayoutParams.WRAP_CONTENT);
		animBtn.setLayoutParams(params);
 	}

	@Override
	protected int getContentView()
	{

		// TODO Auto-generated method stub
		return R.layout.activity_main_radio;
	}

	public void executeAnim(int position)
	{

		Animation animation = null;
		switch (position)
		{
			case 0:
				if (currentPosition == 1)
					animation = new TranslateAnimation(duration, 0, 0, 0);
				else if (currentPosition == 2)
					animation = new TranslateAnimation(2 * duration, 0, 0, 0);
				break;
			case 1:
				if (currentPosition == 0)
					animation = new TranslateAnimation(0, duration, 0, 0);
				else if (currentPosition == 2)
					animation = new TranslateAnimation(2 * duration, duration, 0, 0);
				break;
			case 2:
				if (currentPosition == 0)
					animation = new TranslateAnimation(0, 2 * duration, 0, 0);
				else if (currentPosition == 1)
					animation = new TranslateAnimation(duration, 2 * duration, 0, 0);
				break;
			default:
				break;
		}
		if (animation != null)
		{
			currentPosition = position;
			animation.setDuration(200);
			animation.setFillAfter(true);
			animBtn.startAnimation(animation);
		}
	}

	@Override
	public void onClick(View v)
	{

		switch (v.getId())
		{
			case R.id.channel:
				executeAnim(0);
				break;
			case R.id.favorite:
				executeAnim(1);
				break;
			case R.id.point:
				executeAnim(2);
				break;

			default:
				break;
		}

	}

}
