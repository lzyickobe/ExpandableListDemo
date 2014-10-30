package com.lzyblog.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

import com.lzyblog.R;
import com.lzyblog.base.BaseFragment;
import com.lzyblog.bean.ChildInfo;
import com.lzyblog.bean.GroupInfo;
import com.lzyblog.ui.adapter.MyExpandableListAdapter;
import com.lzyblog.widget.MyExpandableListView;

public class FriendListFragment extends BaseFragment {
	MyExpandableListView exList;
	MyExpandableListAdapter adapter;

	List<GroupInfo> groups = new ArrayList<GroupInfo>();
	List<List<ChildInfo>> childs = new ArrayList<List<ChildInfo>>();

	protected int setLayoutId() {
		return R.layout.fm_friend_list;
	}

	protected void init(Bundle savedInstanceState) {
		exList = (MyExpandableListView) contentView.findViewById(R.id.lv_friend_list);
		exList.setHeaderView(activity.getLayoutInflater().inflate(R.layout.item_group, exList, false));
		adapter = new MyExpandableListAdapter(context, exList, groups, childs);
		exList.setAdapter(adapter);

		exList.setOnChildClickListener(new OnChildClickListener() {
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				Toast.makeText(activity, "好友 " + groupPosition + "-" + childPosition, Toast.LENGTH_SHORT).show();
				return false;
			}
		});
	}

	public void onResume() {
		super.onResume();
		updateUI();
	}

	void updateUI() {
		String[] groupName = { "我的好友", "小学同学", "中学同学", "大学同学", "同事", "陌生人", "黑名单" };
		for (int i = 0; i < 7; i++) {
			GroupInfo groupInfo = new GroupInfo();
			groupInfo.groupName = groupName[i];
			groupInfo.groupNum = i + "/20";
			groups.add(groupInfo);

			List<ChildInfo> child = new ArrayList<ChildInfo>();
			for (int j = 0; j < (i + 5); j++) {
				ChildInfo childInfo = new ChildInfo();
				childInfo.img = R.drawable.user_friend;
				childInfo.name = groupName[i] + "-" + j;
				child.add(childInfo);
			}
			childs.add(child);
		}

		adapter.notifyDataSetChanged();
	}
}
