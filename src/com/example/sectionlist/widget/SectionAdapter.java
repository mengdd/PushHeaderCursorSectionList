package com.example.sectionlist.widget;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SectionIndexer;

/**
 * Basic adapter class for section list.
 * 
 * @author Meng Dandan
 *
 */
public abstract class SectionAdapter extends CompositeCursorAdapter implements
		SectionIndexer, IHeaderAdapter {

	protected Context mContext = null;
	protected LayoutInflater mInflater = null;

	public SectionAdapter(Context context, int initialCapacity) {
		super(context, initialCapacity);
		init(context);
	}

	public SectionAdapter(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		mContext = context;
		mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public Partition[] getSections() {

		int count = getPartitionCount();

		Partition[] partitions = new Partition[count];

		for (int i = 0; i < count; ++i) {
			partitions[i] = getPartition(i);
		}

		return partitions;
	}

	@Override
	public int getPositionForSection(int section) {
		return getPositionForPartition(section);
	}

	@Override
	public int getSectionForPosition(int position) {
		return getPartitionForPosition(position);
	}

	int mCurrentSection = -1;
	int mNextSection = -1;
	int mNextSectionPosition = -1;

	boolean needRefreshLayout = false;

	@Override
	public void onScrolling(int firstPosition, int headerHeight,
			IHeaderScroll scrollView) {
		int currentSection = getSectionForPosition(firstPosition);

		if (mCurrentSection != currentSection) {
			onCurrentSectionChanged(currentSection, scrollView);

		}

		int nextTop = scrollView.getNextHeaderTop(mNextSection,
				mNextSectionPosition - firstPosition);

		if (nextTop <= headerHeight && nextTop >= 0) {
			Log.i("onScrolling", "Push State");
			scrollView.layoutHeader(nextTop - headerHeight);
			needRefreshLayout = true;

		}
		else {
			if (needRefreshLayout) {// the boolean field is used to avoid
									// repeated layout
				Log.i("onScrolling", "Show State");
				scrollView.layoutHeader(0);
				needRefreshLayout = false;
			}

		}

	}

	private void onCurrentSectionChanged(int currentSection,
			IHeaderScroll scrollView) {
		// Log.i("onScrolling", "onCurrentSectionChanged");
		mCurrentSection = currentSection;
		mNextSection = currentSection + 1;
		mNextSectionPosition = getPositionForSection(mNextSection);
		scrollView.updateHeaderView(mCurrentSection);

		needRefreshLayout = true;
	}

	
	@Override
	public View getTopHeaderView(int partition, View convertView,
			ViewGroup parent) {
		View view = getHeaderView(partition, null, convertView, parent);
		return view;
	}



}
