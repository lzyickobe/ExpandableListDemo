package com.lzyblog.pagetransform;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

public class AlphaPageTransformer implements PageTransformer {

	//屏幕左边界position=0，右边界position=1
	public void transformPage(View view, float position) {
		if (position < -1) { // [-Infinity,-1)
			// 向左滑动，当前页面的前一页.
			ViewHelper.setAlpha(view, 0);
		} else if (position <= 0) { //[-1, 0]
			if (position < -0.6f) {
				ViewHelper.setAlpha(view, 0.4f);
			} else {
				ViewHelper.setAlpha(view, 1 + position);
			}
		} else if (position <= 1) { // (0,1]
			ViewHelper.setAlpha(view, 1 - position);
		} else { // (1,+Infinity]
			//向右滑动，当前页面的后一页
			ViewHelper.setAlpha(view, 0);
		}
	}

}
