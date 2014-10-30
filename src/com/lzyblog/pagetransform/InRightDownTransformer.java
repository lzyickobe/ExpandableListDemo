package com.lzyblog.pagetransform;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

public class InRightDownTransformer implements PageTransformer {

	@Override
	public void transformPage(View view, float position) {
		int pageHeight = view.getHeight();
		if (position < -1) {
			ViewHelper.setTranslationY(view, 0);
			ViewHelper.setAlpha(view, 1);
		} else if (position <= 0) {
			ViewHelper.setTranslationY(view, pageHeight * -position);
			ViewHelper.setAlpha(view, 1 + position);
		} else if (position <= 1) {

			ViewHelper.setTranslationY(view, pageHeight * -position);
			ViewHelper.setAlpha(view, 1 - position);
		} else {
			ViewHelper.setAlpha(view, 1);
		}
	}

}
