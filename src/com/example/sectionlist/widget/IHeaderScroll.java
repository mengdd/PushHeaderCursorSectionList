package com.example.sectionlist.widget;



public interface IHeaderScroll {

	void updateHeaderView(int section);
	
	void layoutHeader(int y);
	
	int getNextHeaderTop(int nextSection, int relativePosition);

}
