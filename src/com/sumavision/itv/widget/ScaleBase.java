package com.sumavision.itv.widget;

import com.sumavision.itv.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

public abstract class ScaleBase extends LinearLayout
{

	final class SmoothScrollRunnable implements Runnable
	{

		static final int ANIMATION_DURATION_MS = 190;
		static final int ANIMATION_FPS = 1000 / 60;

		private final Interpolator interpolator;
		private final int scrollToY;
		private final int scrollFromY;
		private final Handler handler;

		private boolean continueRunning = true;
		private long startTime = -1;
		private int currentY = -1;

		public SmoothScrollRunnable(Handler handler, int fromY, int toY)
		{

			this.handler = handler;
			this.scrollFromY = fromY;
			this.scrollToY = toY;
			this.interpolator = new AccelerateDecelerateInterpolator();
		}

		@Override
		public void run()
		{

			if (startTime == -1)
			{
				startTime = System.currentTimeMillis();
			}
			else
			{

				long normalizedTime = (1000 * (System.currentTimeMillis() - startTime)) / ANIMATION_DURATION_MS;
				normalizedTime = Math.max(Math.min(normalizedTime, 1000), 0);

				final int deltaY = Math.round((scrollFromY - scrollToY) * interpolator.getInterpolation(normalizedTime / 1000f));
				this.currentY = scrollFromY - deltaY;
				setHeaderScroll(currentY);
			}

			if (continueRunning && scrollToY != currentY)
			{
				handler.postDelayed(this, ANIMATION_FPS);
			}
		}

		public void stop()
		{

			this.continueRunning = false;
			this.handler.removeCallbacks(this);
		}
	};

	static final float FRICTION = 2.0f;

	static final int PULL_TO_Scale = 0x0;
	static final int RELEASE_TO_Scale = 0x1;
	static final int ScaleING = 0x2;
	static final int MANUAL_ScaleING = 0x3;

	public static final int MODE_PULL_DOWN_TO_Scale = 0x1;
	public static final int MODE_PULL_UP_TO_Scale = 0x2;
	public static final int MODE_BOTH = 0x3;
	private static boolean isCanPull = true;

	private int touchSlop;

	private float initialMotionY;
	private float lastMotionX;
	private float lastMotionY;
	private boolean isBeingDragged = false;

	private int state = PULL_TO_Scale;
	private int mode = MODE_PULL_DOWN_TO_Scale;
	private int currentMode;

	private boolean disableScrollingWhileScaleing = true;

	ListView scaleableView;
	private boolean isPullToScaleEnabled = true;

	private FrameLayout headerLayout;
	private FrameLayout footerLayout;
	private int headerHeight;

	private final Handler handler = new Handler();

	private OnScaleListener onScaleListener;

	private SmoothScrollRunnable currentSmoothScrollRunnable;

	public ScaleBase(Context context)
	{

		super(context);
		init(context, null);
	}

	public ScaleBase(Context context, int mode)
	{

		super(context);
		this.mode = mode;
		init(context, null);
	}

	public ScaleBase(Context context, AttributeSet attrs)
	{

		super(context, attrs);
		init(context, attrs);
	}

	public final ListView getScaleableView()
	{

		return scaleableView;
	}

	public final boolean isPullToScaleEnabled()
	{

		return isPullToScaleEnabled;
	}

	public final boolean isDisableScrollingWhileScaleing()
	{

		return disableScrollingWhileScaleing;
	}

	public final boolean isScaleing()
	{

		return state == ScaleING || state == MANUAL_ScaleING;
	}

	public final void setDisableScrollingWhileScaleing(boolean disableScrollingWhileScaleing)
	{

		this.disableScrollingWhileScaleing = disableScrollingWhileScaleing;
	}

	public final void onScaleComplete()
	{

		if (state != PULL_TO_Scale)
		{
			resetHeader();
		}
	}

	public final void setOnScaleListener(OnScaleListener listener)
	{

		onScaleListener = listener;
	}

	public final void setPullToScaleEnabled(boolean enable)
	{

		this.isPullToScaleEnabled = enable;
	}

	public final void setScaleing()
	{

		this.setScaleing(true);
	}

	public final void setScaleing(boolean doScroll)
	{

		if (!isScaleing())
		{
			setScaleingInternal(doScroll);
			state = MANUAL_ScaleING;
		}
	}

	public final boolean hasPullFromTop()
	{

		return currentMode != MODE_PULL_UP_TO_Scale;
	}

	@Override
	public final boolean onTouchEvent(MotionEvent event)
	{

		if (!isPullToScaleEnabled)
		{
			return false;
		}

		if (isScaleing() && disableScrollingWhileScaleing)
		{
			return true;
		}

		if (event.getAction() == MotionEvent.ACTION_DOWN && event.getEdgeFlags() != 0)
		{
			return false;
		}

		switch (event.getAction())
		{

			case MotionEvent.ACTION_MOVE:
			{
				if (isBeingDragged && isCanPull)
				{
					lastMotionY = event.getY();
					this.pullEvent();
					return true;
				}
				break;
			}

			case MotionEvent.ACTION_DOWN:
			{
				if (isReadyForPull())
				{
					lastMotionY = initialMotionY = event.getY();
					return true;
				}
				break;
			}

			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_UP:
			{
				if (isBeingDragged)
				{
					isBeingDragged = false;

					if (state == RELEASE_TO_Scale && null != onScaleListener)
					{
						setScaleingInternal(true);
						onScaleListener.onScale();
					}
					else
					{
						smoothScrollTo(0);
					}
					return true;
				}
				break;
			}
		}

		return false;
	}

	@Override
	public final boolean onInterceptTouchEvent(MotionEvent event)
	{

		if (!isPullToScaleEnabled)
		{
			return false;
		}

		if (isScaleing() && disableScrollingWhileScaleing)
		{
			return true;
		}

		final int action = event.getAction();

		if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP)
		{
			isBeingDragged = false;
			return false;
		}

		if (action != MotionEvent.ACTION_DOWN && isBeingDragged)
		{
			return true;
		}

		switch (action)
		{
			case MotionEvent.ACTION_MOVE:
			{
				if (isReadyForPull())
				{

					final float y = event.getY();
					final float dy = y - lastMotionY;
					final float yDiff = Math.abs(dy);
					final float xDiff = Math.abs(event.getX() - lastMotionX);

					if (yDiff > touchSlop && yDiff > xDiff)
					{
						if ((mode == MODE_PULL_DOWN_TO_Scale || mode == MODE_BOTH) && dy >= 0.0001f && isReadyForPullDown())
						{
							lastMotionY = y;
							isBeingDragged = true;
							if (mode == MODE_BOTH)
							{
								currentMode = MODE_PULL_DOWN_TO_Scale;
							}
						}
						else if ((mode == MODE_PULL_UP_TO_Scale || mode == MODE_BOTH) && dy <= 0.0001f && isReadyForPullUp())
						{
							lastMotionY = y;
							isBeingDragged = true;
							if (mode == MODE_BOTH)
							{
								currentMode = MODE_PULL_UP_TO_Scale;
							}
						}
					}
				}
				break;
			}
			case MotionEvent.ACTION_DOWN:
			{
				if (isReadyForPull())
				{
					lastMotionY = initialMotionY = event.getY();
					lastMotionX = event.getX();
					isBeingDragged = false;
				}
				break;
			}
		}

		return isBeingDragged;
	}

	protected void addscaleableView(Context context, ListView scaleableView)
	{

		addView(scaleableView, new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, 0, 1.0f));
	}

	protected abstract ListView createScaleableView(Context context, AttributeSet attrs);

	protected final int getCurrentMode()
	{

		return currentMode;
	}

	protected final int getHeaderHeight()
	{

		return headerHeight;
	}

	protected final int getMode()
	{

		return mode;
	}

	protected abstract boolean isReadyForPullDown();

	protected abstract boolean isReadyForPullUp();

	protected void resetHeader()
	{

		state = PULL_TO_Scale;
		isBeingDragged = false;

		smoothScrollTo(0);
	}

	protected void setScaleingInternal(boolean doScroll)
	{

		state = ScaleING;

		if (doScroll)
		{
			smoothScrollTo(currentMode == MODE_PULL_DOWN_TO_Scale ? -headerHeight : headerHeight);
		}
	}

	protected final void setHeaderScroll(int y)
	{

		scrollTo(0, y);
	}

	protected final void smoothScrollTo(int y)
	{

		if (null != currentSmoothScrollRunnable)
		{
			currentSmoothScrollRunnable.stop();
		}

		if (this.getScrollY() != y)
		{
			this.currentSmoothScrollRunnable = new SmoothScrollRunnable(handler, getScrollY(), y);
			handler.post(currentSmoothScrollRunnable);
		}
	}

	private void init(Context context, AttributeSet attrs)
	{

		setOrientation(LinearLayout.VERTICAL);

		touchSlop = ViewConfiguration.getTouchSlop() - 5;

		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PullToScale);
		if (a.hasValue(R.styleable.PullToScale_mode))
		{
			mode = a.getInteger(R.styleable.PullToScale_mode, MODE_PULL_DOWN_TO_Scale);
		}

		scaleableView = this.createScaleableView(context, attrs);
		this.addscaleableView(context, scaleableView);

		if (mode == MODE_PULL_DOWN_TO_Scale || mode == MODE_BOTH)
		{
			headerLayout = new FrameLayout(context);
			addView(headerLayout, 0, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			measureView(headerLayout);
			headerHeight = headerLayout.getMeasuredHeight();
		}
		if (mode == MODE_PULL_UP_TO_Scale || mode == MODE_BOTH)
		{
			footerLayout = new FrameLayout(context);
			addView(footerLayout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			measureView(footerLayout);
			headerHeight = footerLayout.getMeasuredHeight();
		}

		a.recycle();

		switch (mode)
		{
			case MODE_BOTH:
				setPadding(0, -headerHeight, 0, -headerHeight);
				break;
			case MODE_PULL_UP_TO_Scale:
				setPadding(0, 0, 0, -headerHeight);
				break;
			case MODE_PULL_DOWN_TO_Scale:
			default:
				setPadding(0, -headerHeight, 0, 0);
				break;
		}

		if (mode != MODE_BOTH)
		{
			currentMode = mode;
		}
	}

	private void measureView(View child)
	{

		ViewGroup.LayoutParams p = child.getLayoutParams();
		if (p == null)
		{
			p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		}

		int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
		int lpHeight = p.height;
		int childHeightSpec;
		if (lpHeight > 0)
		{
			childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.EXACTLY);
		}
		else
		{
			childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		}
		child.measure(childWidthSpec, childHeightSpec);
	}

	private boolean pullEvent()
	{

		final int newHeight;
		final int oldHeight = this.getScrollY();

		switch (currentMode)
		{
			case MODE_PULL_UP_TO_Scale:
				newHeight = Math.round(Math.max(initialMotionY - lastMotionY, 0) / FRICTION);
				break;
			case MODE_PULL_DOWN_TO_Scale:
			default:
				newHeight = Math.round(Math.min(initialMotionY - lastMotionY, 0) / FRICTION);
				break;
		}

		setHeaderScroll(newHeight);

		if (newHeight != 0)
		{
			if (state == PULL_TO_Scale && headerHeight < Math.abs(newHeight))
			{
				state = RELEASE_TO_Scale;
				return true;

			}
			else if (state == RELEASE_TO_Scale && headerHeight >= Math.abs(newHeight))
			{
				state = PULL_TO_Scale;
				return true;
			}
		}

		return oldHeight != newHeight;
	}

	private boolean isReadyForPull()
	{

		switch (mode)
		{
			case MODE_PULL_DOWN_TO_Scale:
				return isReadyForPullDown();
			case MODE_PULL_UP_TO_Scale:
				return isReadyForPullUp();
			case MODE_BOTH:
				return isReadyForPullUp() || isReadyForPullDown();
		}
		return false;
	}

	public static interface OnScaleListener
	{

		public void onScale();

	}

	public static interface OnLastItemVisibleListener
	{

		public void onLastItemVisible();

	}

	@Override
	public void setLongClickable(boolean longClickable)
	{

		getScaleableView().setLongClickable(longClickable);
	}

	public void setScaleMode(boolean mode)
	{

		isCanPull = mode;
	}

	public boolean isCanScale()
	{

		return isCanPull;
	}

}
