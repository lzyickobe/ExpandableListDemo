package com.lzyblog.pagetransform;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

public class DepthPageTransformer implements PageTransformer {
	private static float MIN_SCALE = 0.75f;

	@Override
	public void transformPage(View view, float position) {
		if (position < -1) { // [-Infinity,-1)
			ViewHelper.setAlpha(view, 0);
		} else if (position <= 0) { // [-1,0]
			ViewHelper.setAlpha(view, 1);
			ViewHelper.setTranslationX(view, 0);
			ViewHelper.setScaleX(view, 1);
			ViewHelper.setScaleY(view, 1);
		} else if (position <= 1) { // (0,1]
			float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
			ViewHelper.setAlpha(view, 1 - position);
			ViewHelper.setTranslationX(view, 0);
			ViewHelper.setScaleX(view, scaleFactor);
			ViewHelper.setScaleY(view, scaleFactor);
		} else { // (1,+Infinity]
			ViewHelper.setAlpha(view, 0);
		}
	}

}
