package com.bdlions.test;

import java.util.ArrayList;
import java.util.Arrays;

import com.bdlions.components.EndlessScroller;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;

public class EndlessScrollerTest extends ListActivity {

	private Context context;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_endless_scroller);
		context = this;
		MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(context, getValues(1));
		getListView().setAdapter(adapter);
		
		getListView().setOnScrollListener(new EndlessScroller(10) {
			
			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				((MySimpleArrayAdapter)getListView().getAdapter()).addAll(getValues(page));
				((MySimpleArrayAdapter)getListView().getAdapter()).notifyDataSetChanged();
			}
		});

	}

	public ArrayList<String> getValues(int page){
		String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
				"Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
				"Linux", "OS/2","Android2", "iPhone", "WindowsMobile",
				"Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
				"Linux", "OS/2" ,"Android3", "iPhone", "WindowsMobile",
				"Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
				"Linux", "OS/2"  };
		if(page * 10 <= values.length)
			return new ArrayList<String>(Arrays.asList( Arrays.copyOfRange(values, 0, page  * 10 - 1)));
		return new ArrayList<String>();
	}
	
	@Override
	public void onDestroy() {
		getListView().setAdapter(null);
		super.onDestroy();
	}

}