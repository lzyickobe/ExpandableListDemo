package com.lzyblog.pagetransform;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

/**
 * 
 * @author way
 * 
 */
public class ZoomOutPageTransformer implements PageTransformer {
	private static float MIN_SCALE = 0.85f;

	private static float MIN_ALPHA = 0.5f;

	@Override
	public void transformPage(View view, float position) {
		int pageWidth = view.getWidth();
		int pageHeight = view.getHeight();

		if (position < -1) { // [-Infinity,-1)
			ViewHelper.setAlpha(view, 0);
		} else if (position <= 1) { // [-1,1]
			float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
			float vertMargin = pageHeight * (1 - scaleFactor) / 2;
			float horzMargin = pageWidth * (1 - scaleFactor) / 2;
			if (position < 0) {
				ViewHelper.setTranslationX(view, horzMargin - vertMargin / 2);
			} else {
				ViewHelper.setTranslationX(view, -horzMargin + vertMargin / 2);
			}
			ViewHelper.setAlpha(view, MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
			ViewHelper.setTranslationX(view, 0);
			ViewHelper.setScaleX(view, scaleFactor);
			ViewHelper.setScaleY(view, scaleFactor);
		} else { // (1,+Infinity]
			ViewHelper.setAlpha(view, 0);
		}
	}
}
