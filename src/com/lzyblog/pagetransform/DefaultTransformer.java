package com.lzyblog.pagetransform;

import com.nineoldandroids.view.ViewHelper;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

public class DefaultTransformer implements PageTransformer {

	@Override
	public void transformPage(View view, float arg1) {
		ViewHelper.setAlpha(view, 1);
		ViewHelper.setTranslationX(view, 0);
		ViewHelper.setTranslationY(view, 0);
		ViewHelper.setPivotX(view, view.getWidth() / 2);
		ViewHelper.setPivotY(view, view.getHeight() / 2);
		ViewHelper.setScaleX(view, 1);
		ViewHelper.setScaleY(view, 1);
		ViewHelper.setRotation(view, 0);
	}

}
