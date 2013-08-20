package com.example.testdemo;

import com.example.sectionlist.widget.SectionListView;
import com.example.testsample3.R;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.MatrixCursor;

public class TestActivity extends Activity {

	private SectionListView mListView = null;
	private MyAdapter mAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_sample3);

		mListView = (SectionListView) findViewById(R.id.listView);
		mAdapter = new MyAdapter(this);

		String[] headerStrings = new String[] { "header1", "header2",
				"header3", "header4","header5"};
		mAdapter.setHeaders(headerStrings);
		mAdapter.setHeaderCount(headerStrings.length);

		int[] counts = new int[] { 14, 12, 13, 14, 10 };
		for (int i = 0; i < headerStrings.length; ++i) {
			
			mAdapter.addPartition(true, null != headerStrings[i]);
			final int sectionId = i;
			final Cursor cursor = makeCursor(headerStrings[i], counts[i]);
		
			mAdapter.changeCursor(sectionId, cursor);
		}

		mListView.setAdapter(mAdapter);
	}

	private Cursor makeCursor(String name, int count) {
		MatrixCursor cursor = new MatrixCursor(new String[] { "_id", name });
		for (int i = 0; i < count; i++) {
			cursor.addRow(new Object[] { i, name + "[" + i + "]" });
		}
		return cursor;
	}

}
