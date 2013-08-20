package com.example.testdemo;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sectionlist.widget.SectionAdapter;
import com.example.testsample3.R;

public class MyAdapter extends SectionAdapter {

	private String[] mHeaders;
	private int mHeaderCount = 0;

	public void setHeaders(String[] headers) {
		this.mHeaders = headers;
	}

	public void setHeaderCount(int count) {
		mHeaderCount = count;
	}

	public int getHeaderCount() {
		return mHeaderCount;
	}

	public MyAdapter(Context context, int initialCapacity) {
		super(context, initialCapacity);
	}

	public MyAdapter(Context context) {
		super(context);
	}

	@Override
	protected View newHeaderView(Context context, int partition, Cursor cursor,
			ViewGroup parent) {
		View view = mInflater.inflate(R.layout.header_item, parent, false);

		ViewHolder1 viewHolder = new ViewHolder1();
		viewHolder.header = (TextView) view.findViewById(R.id.header);
		viewHolder.subHeader = (TextView) view.findViewById(R.id.sub_header);

		view.setTag(viewHolder);

		return view;

	}

	@Override
	protected void bindHeaderView(View view, int partition, Cursor cursor) {

		ViewHolder1 viewHolder = (ViewHolder1) view.getTag();
		viewHolder.header.setText("main " + mHeaders[partition]);
		viewHolder.subHeader.setText("subheader: " + mHeaders[partition]);
	}

	@Override
	protected View newView(Context context, int partition, Cursor cursor,
			int position, ViewGroup parent) {
		View view = mInflater.inflate(R.layout.content_item, parent, false);

		ViewHolder2 viewHolder = new ViewHolder2();
		viewHolder.text1 = (TextView) view.findViewById(R.id.text1);
		viewHolder.text2 = (TextView) view.findViewById(R.id.text2);
		view.setTag(viewHolder);

		return view;

	}

	@Override
	protected void bindView(View v, int partition, Cursor cursor, int position) {

		ViewHolder2 viewHolder = (ViewHolder2) v.getTag();
		viewHolder.text1.setText(cursor.getString(1));
		viewHolder.text2.setText(cursor.getString(1) + " text2");
	}

	@Override
	public View getTopHeaderView(int partition, View convertView,
			ViewGroup parent) {
		View view = getHeaderView(partition, null, convertView, parent);
		return view;
	}

	private static class ViewHolder1 {
		TextView header;
		TextView subHeader;

	}

	private static class ViewHolder2 {
		TextView text1;
		TextView text2;

	}

}
