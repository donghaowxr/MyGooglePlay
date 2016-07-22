package com.example.mygoogleplay.fragment;

import com.example.mygoogleplay.utils.UIUtils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BaseFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		TextView view=new TextView(UIUtils.getContext());
		view.setText("你好");
		return view;
	}
}
