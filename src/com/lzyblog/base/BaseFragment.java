package com.lzyblog.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
	protected Activity activity;
	protected Context context;
	protected View contentView;
	protected BaseFragment baseFragment;

	/**
	 * 设置Layout
	 * 
	 * @return
	 */
	protected abstract int setLayoutId();

	/**
	 * 初始化变量
	 * 
	 * @param savedInstanceState
	 */
	protected abstract void init(Bundle savedInstanceState);

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		activity = getActivity();
		context = activity;
		baseFragment = this;

		if (contentView != null && contentView.getParent() != null) {
			ViewGroup vGroup = (ViewGroup) contentView.getParent();
			vGroup.removeView(contentView);
		} else {
			contentView = View.inflate(context, setLayoutId(), null);
			init(savedInstanceState);
		}

		return contentView;
	}
}
