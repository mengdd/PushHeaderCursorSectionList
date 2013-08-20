package com.example.sectionlist.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * A ListView that shows several sections together.
 * Each section has a header.
 * The top header is the current header and can be pushed up if the second
 * section is coming up.
 * 
 * @author Meng Dandan
 * 
 */
public class SectionListView extends ListView implements OnScrollListener,
		IHeaderScroll {

	private static final String TAG = "ListView";

	private View mTopHeaderView = null;
	private boolean isTopHeaderVisible = true;

	private int mHeaderPaddingLeft;
	private int mHeaderPaddingTop;
	private int mHeaderWidth;
	private int mHeaderHeight;

	private IHeaderAdapter mAdapter = null;
	private OnScrollListener mOnScrollListener;

	public SectionListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public SectionListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public SectionListView(Context context) {
		super(context);
		init();
	}

	private void init() {
		super.setOnScrollListener(this);

	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		Log.w(TAG, "onLayout");
		mHeaderPaddingLeft = getPaddingLeft();
		mHeaderPaddingTop = getPaddingTop();

		if (null != mTopHeaderView) {
			mTopHeaderView.layout(mHeaderPaddingLeft, mHeaderPaddingTop,
					mHeaderWidth, mHeaderHeight);

		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		Log.i(TAG, "onMeasure");
		if (mTopHeaderView != null) {
			Log.i(TAG, "onMeasure -- mHeadView != null");
			measureChild(mTopHeaderView, widthMeasureSpec, heightMeasureSpec);
			mHeaderWidth = mTopHeaderView.getMeasuredWidth();
			mHeaderHeight = mTopHeaderView.getMeasuredHeight();

		}
	}

	@Override
	public void setAdapter(ListAdapter adapter) {
		mAdapter = (IHeaderAdapter) adapter;
		super.setAdapter(adapter);
	}

	@Override
	public void setOnScrollListener(OnScrollListener listener) {
		mOnScrollListener = listener;

		super.setOnScrollListener(this);
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (null != mOnScrollListener) {
			mOnScrollListener.onScrollStateChanged(view, scrollState);
		}

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		if (null != mAdapter) {

			mAdapter.onScrolling(firstVisibleItem, mHeaderHeight, this);
		}

		if (null != mOnScrollListener) {
			mOnScrollListener.onScroll(view, firstVisibleItem,
					visibleItemCount, totalItemCount);
		}

	}

	@Override
	public void updateHeaderView(int section) {

		mTopHeaderView = mAdapter.getTopHeaderView(section, mTopHeaderView,
				this);
	}

	@Override
	public void layoutHeader(int y) {
		mTopHeaderView.layout(mHeaderPaddingLeft, mHeaderPaddingTop + y,
				mHeaderWidth, mHeaderHeight + y);
	}

	@Override
	public int getNextHeaderTop(int nextSection, int relativePosition) {
		View childView = getChildAt(relativePosition);
		int top = -1;
		if (null != childView) {
			top = childView.getTop();
		}
		return top;
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		if (isTopHeaderVisible) {
			drawChild(canvas, mTopHeaderView, getDrawingTime());
		}
	}

}
