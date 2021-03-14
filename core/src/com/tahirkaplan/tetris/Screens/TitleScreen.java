package com.tahirkaplan.tetris.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.tahirkaplan.tetris.Main;

import java.util.Random;

public class TitleScreen extends Scene {

    private Game game;

    private BitmapFont font;
    private TextButton play;
    private Stage stage;

    private Random random;

    private Color color1;
    private float[] s1;
    private Color color2;
    private float[] s2;
    private Color color3;
    private float[] s3;
    private Color color4;
    private float[] s4;

    private float timeMul = 200f;

    private float rLimit = 1f;
    private float gLimit = 1f;
    private float bLimit = 1f;

    private float rBase = 0.3f;
    private float gBase = 0.3f;
    private float bBase = 0.3f;

    public TitleScreen(Game game){
        this.game = game;

        wh = 720;

        Main.fontGenerator.setSize(90);
        Main.fontGenerator.setColor(Color.WHITE);
        Main.fontGenerator.setShadowColor(new Color(0.1f,0.1f,0.1f,1f));
        Main.fontGenerator.setShadowOffset(new Vector2(5,5));
        Main.fontGenerator.setBorderColor(new Color(0.3f,0.3f,1,1));
        Main.fontGenerator.setBorderWidth(5);

        font = Main.fontGenerator.generateFont();

        Skin skin = new Skin(Gdx.files.internal("glassy/skin/glassy-ui.json"));

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = font;
        play = new TextButton("Play",skin);
        play.getLabel().setStyle(new Label.LabelStyle(font,font.getColor()));


    }

    @Override
    public void show() {
        super.show();

        play.setSize(wh/2f,wh/4f);
        play.setPosition(ww/2f - play.getWidth()/2f,wh/2f - play.getHeight()/2f);
        play.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game));
            }
        });

        stage = new Stage(viewport);
        stage.addActor(play);

        Gdx.input.setInputProcessor(stage);

        random = new Random();

        color1 = new Color(1f,0,0,1f);
        color2 = new Color(0,1f,0,1f);
        color3 = new Color(0,0,1f,1f);
        color4 = new Color(1f,1f,1f,1f);

        s1 = new float[3];
        if (color1.r == 1) s1[0] = -random.nextFloat()/timeMul;
        else s1[0] = random.nextFloat()/timeMul;
        if (color1.g == 1) s1[0] = -random.nextFloat()/timeMul;
        else s1[0] = random.nextFloat()/timeMul;
        if (color1.b == 1) s1[0] = -random.nextFloat()/timeMul;
        else s1[0] = random.nextFloat()/timeMul;

        s2 = new float[3];
        if (color2.r == 1) s2[0] = -random.nextFloat()/timeMul;
        else s2[0] = random.nextFloat()/timeMul;
        if (color2.g == 1) s2[0] = -random.nextFloat()/timeMul;
        else s2[0] = random.nextFloat()/timeMul;
        if (color2.b == 1) s2[0] = -random.nextFloat()/timeMul;
        else s2[0] = random.nextFloat()/timeMul;

        s3 = new float[3];
        if (color3.r == 1) s3[0] = -random.nextFloat()/timeMul;
        else s3[0] = random.nextFloat()/timeMul;
        if (color3.g == 1) s3[0] = -random.nextFloat()/timeMul;
        else s3[0] = random.nextFloat()/timeMul;
        if (color3.b == 1) s3[0] = -random.nextFloat()/timeMul;
        else s3[0] = random.nextFloat()/timeMul;

        s4 = new float[3];
        if (color4.r == 1) s4[0] = -random.nextFloat()/timeMul;
        else s4[0] = random.nextFloat()/timeMul;
        if (color4.g == 1) s4[0] = -random.nextFloat()/timeMul;
        else s4[0] = random.nextFloat()/timeMul;
        if (color4.b == 1) s4[0] = -random.nextFloat()/timeMul;
        else s4[0] = random.nextFloat()/timeMul;

    }

    @Override
    public void render(float delta) {
        camera.update();
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        Main.batch.setProjectionMatrix(camera.combined);
        Main.batch.begin();

        Main.batch.end();

        Main.shapeRenderer.setProjectionMatrix(camera.combined);
        Main.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);


        Main.shapeRenderer.rect(0,0,ww,wh,color1,color2,color3,color4);

        Main.shapeRenderer.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
    }

    private void update(float delta){
        /**Color1****************/
        color1.r += s1[0];
        if (color1.r <= rBase){
            color1.r = rBase;
            s1[0] = random.nextFloat()/timeMul;
        }else if (color1.r >= rLimit){
            color1.r = rLimit;
            s1[0] = -random.nextFloat()/timeMul;
        }

        color1.g += s1[1];
        if (color1.g <= gBase){
            color1.g = gBase;
            s1[1] = random.nextFloat()/timeMul;
        }else if (color1.g >= gLimit){
            color1.g = gLimit;
            s1[1] = -random.nextFloat()/timeMul;
        }

        color1.b += s1[2];
        if (color1.b <= bBase){
            color1.b = bBase;
            s1[2] = random.nextFloat()/timeMul;
        }else if (color1.b >= bLimit){
            color1.b = bLimit;
            s1[2] = -random.nextFloat()/timeMul;
        }


        /**Color2****************/
        color2.r += s2[0];
        if (color2.r <= rBase){
            color2.r = rBase;
            s2[0] = random.nextFloat()/timeMul;
        }else if (color2.r >= rLimit){
            color2.r = rLimit;
            s2[0] = -random.nextFloat()/timeMul;
        }

        color2.g += s2[1];
        if (color2.g <= gBase){
            color2.g = gBase;
            s2[1] = random.nextFloat()/timeMul;
        }else if (color2.g >= gLimit){
            color2.g = gLimit;
            s2[1] = -random.nextFloat()/timeMul;
        }

        color2.b += s2[2];
        if (color2.b <= bBase){
            color2.b = bBase;
            s2[2] = random.nextFloat()/timeMul;
        }else if (color2.b >= bLimit){
            color2.b = bLimit;
            s2[2] = -random.nextFloat()/timeMul;
        }

        /**Color3**********/
        color3.r += s3[0];
        if (color3.r <= rBase){
            color3.r = rBase;
            s3[0] = random.nextFloat()/timeMul;
        }else if (color3.r >= rLimit){
            color3.r = rLimit;
            s3[0] = -random.nextFloat()/timeMul;
        }

        color3.g += s3[1];
        if (color3.g <= gBase){
            color3.g = gBase;
            s3[1] = random.nextFloat()/timeMul;
        }else if (color3.g >= gLimit){
            color3.g = gLimit;
            s3[1] = -random.nextFloat()/timeMul;
        }

        color3.b += s3[2];
        if (color3.b <= bBase){
            color3.b = bBase;
            s3[2] = random.nextFloat()/timeMul;
        }else if (color3.b >= bLimit){
            color3.b = bLimit;
            s3[2] = -random.nextFloat()/timeMul;
        }


        /**Color4*********/
        color4.r += s4[0];
        if (color4.r <= rBase){
            color4.r = rBase;
            s4[0] = random.nextFloat()/timeMul;
        }else if (color4.r >= rLimit){
            color4.r = rLimit;
            s4[0] = -random.nextFloat()/timeMul;
        }

        color4.g += s4[1];
        if (color4.g <= gBase){
            color4.g = gBase;
            s4[1] = random.nextFloat()/timeMul;
        }else if (color4.g >= gLimit){
            color4.g = gLimit;
            s4[1] = -random.nextFloat()/timeMul;
        }

        color4.b += s4[2];
        if (color4.b <= bBase){
            color4.b = bBase;
            s4[2] = random.nextFloat()/timeMul;
        }else if (color4.b >= bLimit){
            color4.b = bLimit;
            s4[2] = -random.nextFloat()/timeMul;
        }
    }
}
