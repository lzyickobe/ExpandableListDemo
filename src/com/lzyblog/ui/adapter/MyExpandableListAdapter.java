package com.lzyblog.ui.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzyblog.R;
import com.lzyblog.bean.ChildInfo;
import com.lzyblog.bean.GroupInfo;
import com.lzyblog.widget.MyExpandableListView;
import com.lzyblog.widget.MyExpandableListView.HeaderAdapter;

public class MyExpandableListAdapter extends BaseExpandableListAdapter implements HeaderAdapter {
	private MyExpandableListView listView;
	private Context context;
	private List<GroupInfo> groupData;
	private List<List<ChildInfo>> childData;

	@SuppressLint("UseSparseArrays")
	private SparseArray<Integer> groupStatusMap = new SparseArray<Integer>();

	public MyExpandableListAdapter(Context context, MyExpandableListView listView, List<GroupInfo> groupData, List<List<ChildInfo>> childData) {
		this.context = context;
		this.listView = listView;
		this.groupData = groupData;
		this.childData = childData;
	}

	@Override
	public int getHeaderState(int groupPosition, int childPosition) {
		final int childCount = getChildrenCount(groupPosition);
		if (childPosition == childCount - 1) {
			return PINNED_HEADER_PUSHED_UP;
		} else if (childPosition == -1 && !listView.isGroupExpanded(groupPosition)) {
			return PINNED_HEADER_GONE;
		} else {
			return PINNED_HEADER_VISIBLE;
		}
	}

	@Override
	public void configureHeader(View header, int groupPosition, int childPosition, int alpha) {
		GroupInfo groupData = (GroupInfo) getGroup(groupPosition);
		((TextView) header.findViewById(R.id.groupto)).setText(groupData.groupName);
		((TextView) header.findViewById(R.id.groupnum)).setText(groupData.groupNum);

		if (getGroupClickStatus(groupPosition) == 1) {
			((ImageView) header.findViewById(R.id.groupIcon)).setImageResource(R.drawable.item_arrow_2);
		} else {
			((ImageView) header.findViewById(R.id.groupIcon)).setImageResource(R.drawable.item_arrow_1);
		}
	}

	@Override
	public void setGroupClickStatus(int groupPosition, int status) {
		groupStatusMap.put(groupPosition, status);
	}

	@Override
	public int getGroupClickStatus(int groupPosition) {
		if (groupStatusMap.indexOfKey(groupPosition) >= 0) {
			return groupStatusMap.get(groupPosition);
		} else {
			return 0;
		}
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.item_group, null);
		}

		ImageView iv = (ImageView) convertView.findViewById(R.id.groupIcon);
		TextView groupName = (TextView) convertView.findViewById(R.id.groupto);
		TextView groupNum = (TextView) convertView.findViewById(R.id.groupnum);

		if (isExpanded) {
			iv.setImageResource(R.drawable.item_arrow_2);
		} else {
			iv.setImageResource(R.drawable.item_arrow_1);
		}

		GroupInfo groupData = (GroupInfo) getGroup(groupPosition);

		groupName.setText(groupData.groupName);
		groupNum.setText(groupData.groupNum);

		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.item_child, null);
		}

		TextView childName = (TextView) convertView.findViewById(R.id.childto);
		ImageView childImage = (ImageView) convertView.findViewById(R.id.childIcon);

		ChildInfo childInfo = getChild(groupPosition, childPosition);
		childName.setText(childInfo.name);
		childImage.setImageResource(childInfo.img);

		return convertView;
	}

	@Override
	public int getGroupCount() {
		return groupData.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return childData.get(groupPosition).size();
	}

	@Override
	public GroupInfo getGroup(int groupPosition) {
		return groupData.get(groupPosition);
	}

	@Override
	public ChildInfo getChild(int groupPosition, int childPosition) {
		return childData.get(groupPosition).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}