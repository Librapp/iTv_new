package com.sumavision.itv.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.widget.ListView;


public class ScaleListView extends ScaleAdapterViewBase {

	class InternalListView extends ListView implements ViewMethodAccessor {

		public InternalListView(Context context, AttributeSet attrs) {
			super(context, attrs);
		}

		@Override
		public void setEmptyView(View emptyView) {
			ScaleListView.this.setEmptyView(emptyView);
		}
		
		@Override
		public void setEmptyViewInternal(View emptyView) {
			super.setEmptyView(emptyView);
		}

		public ContextMenuInfo getContextMenuInfo() {
			return super.getContextMenuInfo();
		}
	}

	public ScaleListView(Context context) {
		super(context);
		this.setDisableScrollingWhileScaleing(false);
	}
	
	public ScaleListView(Context context, int mode) {
		super(context, mode);
		this.setDisableScrollingWhileScaleing(false);
	}

	public ScaleListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setDisableScrollingWhileScaleing(false);
	}

	@Override
	public ContextMenuInfo getContextMenuInfo() {
		return ((InternalListView) getScaleableView()).getContextMenuInfo();
	}

	@Override
	protected final ListView createScaleableView(Context context, AttributeSet attrs) {
		ListView lv = new InternalListView(context, attrs);
		lv.setId(android.R.id.list);
		return lv;
	}


}
