package com.tahirkaplan.tetris;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.tahirkaplan.tetris.Screens.TitleScreen;
import com.tahirkaplan.tetris.Tools.AssetLoader;
import com.tahirkaplan.tetris.Tools.FontGenerator;

public class Main extends Game {

	public static SpriteBatch batch;
	public static ShapeRenderer shapeRenderer;

	public static AssetManager manager;
	public static FontGenerator fontGenerator;

	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true);

		manager = new AssetManager();
		AssetLoader.load();
		manager.finishLoading();

		fontGenerator = new FontGenerator();

		this.setScreen(new TitleScreen(this));
	}


}
