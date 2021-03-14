package com.tahirkaplan.tetris;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.tahirkaplan.tetris.Main;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		config.useImmersiveMode = true;
		config.useAccelerometer = false;
		config.useCompass = false;
		config.useGyroscope = false;
		config.useRotationVectorSensor = false;

		initialize(new Main(), config);
	}
}
