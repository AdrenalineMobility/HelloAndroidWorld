package io.adrenaline.android.helloworld;

import io.adrenaline.AdrenalineIo;
import android.app.Application;

public class HelloWorldApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		AdrenalineIo.init(this);
	}

}
