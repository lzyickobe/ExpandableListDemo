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

public class GroupListFrament extends BaseFragment {

	List<GroupInfo> groups = new ArrayList<GroupInfo>();
	List<List<ChildInfo>> childs = new ArrayList<List<ChildInfo>>();

	MyExpandableListView exList;
	MyExpandableListAdapter adapter;

	protected int setLayoutId() {
		return R.layout.fm_group_list;
	}

	protected void init(Bundle savedInstanceState) {
		exList = (MyExpandableListView) contentView.findViewById(R.id.lv_group_list);
		exList.setHeaderView(activity.getLayoutInflater().inflate(R.layout.item_group, exList, false));

		adapter = new MyExpandableListAdapter(context, exList, groups, childs);
		exList.setAdapter(adapter);

		exList.setOnChildClickListener(new OnChildClickListener() {
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				Toast.makeText(activity, "群 " + groupPosition + "-" + childPosition, Toast.LENGTH_SHORT).show();
				return false;
			}
		});

	}

	public void onResume() {
		super.onResume();
		updateUI();
	}

	void updateUI() {
		String[] groupName = { "游戏群", "工作群", "兴趣群", "未分组" };
		for (int i = 0; i < 4; i++) {
			GroupInfo groupInfo = new GroupInfo();
			groupInfo.groupName = groupName[i];
			groupInfo.groupNum = i + 2 + "";
			groups.add(groupInfo);

			List<ChildInfo> child = new ArrayList<ChildInfo>();
			for (int j = 0; j < (i + 2); j++) {
				ChildInfo childInfo = new ChildInfo();
				childInfo.img = R.drawable.user_group;
				childInfo.name = groupName[i] + "-" + j;
				child.add(childInfo);
			}
			childs.add(child);
		}

		adapter.notifyDataSetChanged();
	}

}
