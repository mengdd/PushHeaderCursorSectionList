package com.example.sectionlist.widget;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public interface IHeaderAdapter {


	void onScrolling(int firstPosition, int headerHeight,
			IHeaderScroll scrollView);

	View getTopHeaderView(int partition, View convertView, ViewGroup parent);

}
