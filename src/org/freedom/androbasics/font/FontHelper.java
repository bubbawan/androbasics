package org.freedom.androbasics.font;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

public class FontHelper {

	public void applyFont(final TextView view, final String fontPath,
			final Context context) {
		Typeface tf = Typeface.createFromAsset(context.getAssets(), fontPath);
		view.setTypeface(tf);
	}

}
