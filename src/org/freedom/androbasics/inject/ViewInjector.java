package org.freedom.androbasics.inject;

import java.lang.reflect.Field;

import org.freedom.androbasics.BuildConfig;
import org.freedom.androbasics.Constants;

import android.app.Activity;
import android.util.Log;
import android.view.View;

public class ViewInjector {

	public void injectViews(final Activity activity) {
		injectViews(activity, activity.getWindow().getDecorView().getRootView());
	}

	public void injectViews(final Object target, final View view) {
		Class<?> currentClazz = target.getClass();

		while (currentClazz != null) {
			injectViews(target, currentClazz, view);
			currentClazz = currentClazz.getSuperclass();
		}
	}

	private void injectViews(final Object target, final Class<?> targetClazz,
			final View view) {
		Field[] declaredFields = targetClazz.getDeclaredFields();
		for (Field field : declaredFields) {
			if (field.isAnnotationPresent(InjectView.class)) {
				InjectView annotation = field.getAnnotation(InjectView.class);
				int id = annotation.id();
				View subView = view.findViewById(id);

				if (subView == null) {
					if (BuildConfig.DEBUG) {
						Log.e(Constants.LOG_TAG,
								"injection error: no subview with id " + id
										+ " in view " + view);
					}
					continue;
				}

				boolean accessible = field.isAccessible();
				if (!accessible) {
					field.setAccessible(true);
				}

				try {
					field.set(target, subView);
				} catch (Exception e) {
					if (BuildConfig.DEBUG) {
						Log.e(Constants.LOG_TAG, "injecting view " + subView
								+ " for field with id " + id + " failed.");
					}
				}
				if (!accessible) {
					field.setAccessible(false);
				}
			}
		}
	}

}
