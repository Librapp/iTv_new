package com.sumavision.itv.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

public abstract class ScaleAdapterViewBase extends ScaleBase implements OnScrollListener
{

	private int lastSavedFirstVisibleItem = -1;
	private OnScrollListener onScrollListener;
	private OnLastItemVisibleListener onLastItemVisibleListener;
	private View emptyView;
	private FrameLayout scaleableViewHolder;
	@SuppressWarnings("unused")
	private ImageView mTopImageView;

	public ScaleAdapterViewBase(Context context)
	{

		super(context);
		scaleableView.setOnScrollListener(this);
	}

	public ScaleAdapterViewBase(Context context, int mode)
	{

		super(context, mode);
		scaleableView.setOnScrollListener(this);
	}

	public ScaleAdapterViewBase(Context context, AttributeSet attrs)
	{

		super(context, attrs);
		scaleableView.setOnScrollListener(this);
	}

	@Override
	abstract public ContextMenuInfo getContextMenuInfo();

	@Override
	public final void onScroll(final AbsListView view, final int firstVisibleItem, final int visibleItemCount, final int totalItemCount)
	{

		if (null != onLastItemVisibleListener)
		{
			if (visibleItemCount > 0 && (firstVisibleItem + visibleItemCount == totalItemCount))
			{
				if (firstVisibleItem != lastSavedFirstVisibleItem)
				{
					lastSavedFirstVisibleItem = firstVisibleItem;
					onLastItemVisibleListener.onLastItemVisible();
				}
			}
		}

		if (null != onScrollListener)
		{
			onScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
		}
	}

	@Override
	public final void onScrollStateChanged(final AbsListView view, final int scrollState)
	{

		if (null != onScrollListener)
		{
			onScrollListener.onScrollStateChanged(view, scrollState);
		}
	}

	public void setBackToTopView(ImageView mTopImageView)
	{

		this.mTopImageView = mTopImageView;
		mTopImageView.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{

				if (scaleableView instanceof ListView)
				{
					scaleableView.setSelection(0);
				}
			}
		});
	}

	public final void setEmptyView(View newEmptyView)
	{

		if (null != emptyView)
		{
			scaleableViewHolder.removeView(emptyView);
		}

		if (null != newEmptyView)
		{
			ViewParent newEmptyViewParent = newEmptyView.getParent();
			if (null != newEmptyViewParent && newEmptyViewParent instanceof ViewGroup)
			{
				((ViewGroup) newEmptyViewParent).removeView(newEmptyView);
			}

			this.scaleableViewHolder.addView(newEmptyView, ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
		}

		if (scaleableView instanceof ViewMethodAccessor)
		{
			((ViewMethodAccessor) scaleableView).setEmptyViewInternal(newEmptyView);
		}
		else
		{
			this.scaleableView.setEmptyView(newEmptyView);
		}
	}

	public final void setOnLastItemVisibleListener(OnLastItemVisibleListener listener)
	{

		onLastItemVisibleListener = listener;
	}

	public final void setOnScrollListener(OnScrollListener listener)
	{

		onScrollListener = listener;
	}

	@Override
	protected void addscaleableView(Context context, ListView scaleableView)
	{

		scaleableViewHolder = new FrameLayout(context);
		scaleableViewHolder.addView(scaleableView, ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
		addView(scaleableViewHolder, new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, 0, 1.0f));
	}

	@Override
	protected boolean isReadyForPullDown()
	{

		return isFirstItemVisible();
	}

	@Override
	protected boolean isReadyForPullUp()
	{

		return isLastItemVisible();
	}

	private boolean isFirstItemVisible()
	{

		if (this.scaleableView.getCount() == 0)
		{
			return true;
		}
		else if (scaleableView.getFirstVisiblePosition() == 0)
		{

			final View firstVisibleChild = scaleableView.getChildAt(0);

			if (firstVisibleChild != null)
			{
				return firstVisibleChild.getTop() >= scaleableView.getTop();
			}
		}

		return false;
	}

	private boolean isLastItemVisible()
	{

		final int count = this.scaleableView.getCount();
		final int lastVisiblePosition = scaleableView.getLastVisiblePosition();

		if (count == 0)
		{
			return true;
		}
		else if (lastVisiblePosition == count - 1)
		{

			final int childIndex = lastVisiblePosition - scaleableView.getFirstVisiblePosition();
			final View lastVisibleChild = scaleableView.getChildAt(childIndex);

			if (lastVisibleChild != null)
			{
				return lastVisibleChild.getBottom() <= scaleableView.getBottom();
			}
		}
		return false;
	}
}
