package com.lzyblog.ui.module;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzyblog.R;

public class TitleModule {

	Activity activity;

	public View container;
	public TextView btnBack;
	public TextView btnEdit;
	public TextView tvTitle;

	public RelativeLayout layout;

	public TitleModule(Activity activity) {
		this(activity, "");
	}

	public TitleModule(Activity activity, String title) {
		this(activity, true, false, title);
	}

	public TitleModule(Activity activity, int titleRes) {
		this(activity, true, false, "");
		setTitle(titleRes);
	}

	public TitleModule(Activity activity, boolean showBack, boolean showEdit, String title) {
		this.activity = activity;
		container = activity.findViewById(R.id.title);
		btnBack = (TextView) activity.findViewById(R.id.tvTitleLeft);
		btnEdit = (TextView) activity.findViewById(R.id.btnTitleEdit);
		tvTitle = (TextView) activity.findViewById(R.id.tvTitle);
		layout = (RelativeLayout) activity.findViewById(R.id.rellayout);

		showBack(showBack);
		showEdit(showEdit);
		setTitle(title);

		if (btnBack != null) {
			btnBack.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					if (v == btnBack) {
						TitleModule.this.activity.finish();
					}
				}
			});
		}
	}

	public TitleModule(Activity activity, View view) {
		this(activity, view, "");
	}

	public TitleModule(Activity activity, View view, int titleRes) {
		this(activity, view, activity.getString(titleRes));
	}

	public TitleModule(Activity activity, View view, String title) {
		this(activity, view, true, false, title);
	}

	public TitleModule(Activity activity, View view, boolean showBack, boolean showEdit, String title) {
		this.activity = activity;
		showBack(showBack);
		showEdit(showEdit);
		setTitle(title);

		if (btnBack != null) {
			btnBack.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					if (v == btnBack) {
						TitleModule.this.activity.finish();
					}
				}
			});
		}
	}

	public void setTitle(String s) {
		tvTitle.setText(s);
	}

	public void setTitle(int resId) {
		tvTitle.setText(resId);
	}

	public void setBackText(String s) {
		btnBack.setText(s);
	}

	public void setEditText(String s) {
		btnEdit.setText(s);
	}

	public void showBack(boolean show) {
		btnBack.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	public void showEdit(boolean show) {
		btnEdit.setVisibility(show ? View.VISIBLE : View.GONE);
	}

	public void clearBackListener() {
		if (btnBack != null) {
			btnBack.setOnClickListener(null);
		}
	}

	public void setBackClickListener(OnClickListener clickListener) {
		btnBack.setOnClickListener(clickListener);
	}

	public void setEditClickListener(OnClickListener clickListener) {
		btnEdit.setOnClickListener(clickListener);
	}
}