package org.freedom.androbasics;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewStub;

public abstract class TemplateActivity extends Activity {

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
		stub.inflate();
	}

}