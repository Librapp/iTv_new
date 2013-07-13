package com.sumavision.itv.adapter;

import java.util.List;

import com.sumavision.itv.activity.BaseActivity;
import com.sumavision.itv.utils.ImageLoaderHelper;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class IBaseAdapter<T> extends ArrayAdapter<T>
{

	public Display display;
	public String TAG;
	public int width;
	public boolean DEBUG_MODE = false;
	public final String i = "log_i";
	public final String e = "log_e";
	public final String d = "log_d";
	public LayoutInflater inflater;
	private ImageLoaderHelper imageLoaderHelper;

	public IBaseAdapter(Context context, T[] objects)
	{

		super(context, 0, objects);
		initAdapter(context);
	}

	public IBaseAdapter(Context context, List<T> objects)
	{

		super(context, 0, objects);
		initAdapter(context);
	}

	private void initAdapter(Context context)
	{

		TAG = getClass().getName().replace(context.getPackageName() + ".adapter.", " ");
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		DEBUG_MODE = BaseActivity.DEBUG_MODE;
		display = ((Activity) context).getWindowManager().getDefaultDisplay();
		imageLoaderHelper = new ImageLoaderHelper();
		width = display.getWidth();
	}

	protected void loadImage(final ImageView imageView, String url, int defaultPic)
	{

		imageLoaderHelper.loadImage(imageView, url, defaultPic);
	}

	private IBaseAdapter(Context context, int textViewResourceId)
	{

		super(context, textViewResourceId);
	}

	private IBaseAdapter(Context context, int resource, int textViewResourceId)
	{

		super(context, resource, textViewResourceId);
	}

	private IBaseAdapter(Context context, int resource, int textViewResourceId, List<T> objects)
	{

		super(context, resource, textViewResourceId, objects);
	}

	private IBaseAdapter(Context context, int resource, int textViewResourceId, T[] objects)
	{

		super(context, resource, textViewResourceId, objects);
	}

	public void log(String content)
	{

		log(i, content);
	}

	public void log(int content)
	{

		log(i, "" + content);
	}

	public void log(float content)
	{

		log(i, "" + content);
	}

	public void log(String tag, int content)
	{

		log(tag, "" + content);
	}

	public void log(String tag, String content)
	{

		if (!DEBUG_MODE)
			return;

		if (tag.equals(i))
		{
			Log.i(TAG, content);
		}
		else if (tag.equals(e))
		{
			Log.e(TAG, content);
		}
		else if (tag.equals(d))
		{
			Log.d(TAG, content);
		}
	}

	public boolean isEmpty(String str)
	{

		if (str == null || (str != null) && str.trim().length() == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
