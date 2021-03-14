package com.tahirkaplan.tetris.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class Scene implements Screen {

    protected int ww, wh = 360;//world width and height;
    protected float aspectRatio;
    protected int Sw,Sh;//Screen width and height;

    protected Camera camera;
    protected Viewport viewport;


    @Override
    public void show() {

        Sw = Gdx.graphics.getWidth();
        Sh = Gdx.graphics.getHeight();

        aspectRatio = (float)Sw/Sh;

        ww = (int)(wh*aspectRatio);

        camera = new OrthographicCamera();
        viewport = new StretchViewport(ww,wh,camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth/2f,camera.viewportHeight/2f,0f);


    }

    @Override
    public void render(float delta) {
        camera.update();
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


    }

    @Override
    public void resize(int width, int height) {
        Sw = width;
        Sh = height;

        viewport.update(width,height);
        camera.position.set(camera.viewportWidth/2f,camera.viewportHeight/2f,0f);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
    }
}
