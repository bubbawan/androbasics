package org.freedom.androbasics;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public abstract class TemplateActivity extends Activity {

	private final class FooterAnimator implements Runnable {
		private final View footer;

		private FooterAnimator(View footer) {
			this.footer = footer;
		}

		@Override
		public void run() {
			AlphaAnimation animation = new AlphaAnimation(0.2f, 1.0f);
			animation.setDuration(1000);
			animation.setAnimationListener(new AnimationListener() {

				@Override
				public void onAnimationStart(final Animation animation) {
				}

				@Override
				public void onAnimationRepeat(final Animation animation) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationEnd(final Animation animation) {

				}
			});
			footer.startAnimation(animation);
		}
	}

	abstract protected int getContentLayoutId();

	abstract protected int getFooterLayoutId();

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_template);
		fillContent();
	}

	private void fillContent() {
		ViewStub stub = (ViewStub) findViewById(R.id.tmpl_stub_content);
		stub.setLayoutResource(getContentLayoutId());
		stub.inflate();

		stub = (ViewStub) findViewById(R.id.tmpl_stub_footer);
		stub.setLayoutResource(getFooterLayoutId());
		final View footer = stub.inflate();

		if (footer.getVisibility() == View.INVISIBLE) {
			return;
		}
		runOnUiThread(new FooterAnimator(footer));
	}

}