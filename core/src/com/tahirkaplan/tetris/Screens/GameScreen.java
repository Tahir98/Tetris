package com.tahirkaplan.tetris.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.tahirkaplan.tetris.Bricks.Brick;
import com.tahirkaplan.tetris.Bricks.SingleBrick;
import com.tahirkaplan.tetris.Extras.Position;
import com.tahirkaplan.tetris.Main;
import com.tahirkaplan.tetris.Tools.RandomBrickGenerator;

public class GameScreen extends Scene {

    private Game game;

    private ImageButton exitButton;
    private Stage stage;

    private Vector2 origin;
    private float[] grid;

    public static int verticalSquareNum =  16;
    private float size = wh/(float)verticalSquareNum;
    private int rowNum = verticalSquareNum + 5;
    private int columnNum = 13;


    private Color gridColor;
    private Color backgroundColor;

    private boolean[][] board;
    private Brick brick;

    private SingleBrick singleBrick;

    private RandomBrickGenerator generator;

    private BitmapFont font;
    private Label gameOverLabel;
    private boolean gameOver = false;

    private float waitingLimit = 0.9f;
    private boolean isLine = false;

    private Label scoreLabel;
    private int score=0;

    public GameScreen(Game game){
        this.game = game;
    }

    @Override
    public void show() {
        super.show();

        stage = new Stage();

        exitButton = new ImageButton(new TextureRegionDrawable(new Texture("exit/1.png")),
                new TextureRegionDrawable(new Texture("exit/3.png")));
        exitButton.getStyle().imageOver = new TextureRegionDrawable(new Texture("exit/2.png"));
        exitButton.setSize(Sh/8f,Sh/8f);
        exitButton.setPosition(Sw - exitButton.getWidth() - Sh/20f,Sh - exitButton.getHeight() - Sh/20f);
        exitButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new TitleScreen(game));
            }
        });

        stage.addActor(exitButton);
        origin = new Vector2();

        initGrid();

        gridColor = new Color(1f,1,1f,1f);
        backgroundColor = new Color(0.7f,0.7f,0.7f,1);
        //backgroundColor = new Color(Color.BLUE);

        board = new boolean[columnNum-1][rowNum-1];
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[0].length;j++){
                board[i][j] = false;
            }
        }

        generator = new RandomBrickGenerator(rowNum-1,columnNum-1,size,origin);
        brick = generator.generate();
        singleBrick = new SingleBrick(0,0,rowNum-1,columnNum-1,size,origin);
        brick.setWaitingLimit(waitingLimit);

        Main.fontGenerator.setColor(Color.WHITE);
        Main.fontGenerator.setBorderColor(Color.BLACK);
        Main.fontGenerator.setBorderWidth(Sh/45f);
        Main.fontGenerator.setSize((int)(Sh/4.5f));
        Main.fontGenerator.setShadowColor(Color.WHITE);

        font = Main.fontGenerator.generateFont();
        gameOverLabel = new Label("GAME OVER",new Label.LabelStyle(font,font.getColor()));

        gameOverLabel.setSize(Sw/2f,Sh/4f);
        gameOverLabel.setPosition(Sw/2f - gameOverLabel.getWidth()/2f,Sh/2f - gameOverLabel.getHeight()/2f);
        gameOverLabel.setVisible(false);
        gameOverLabel.setAlignment(Align.center);
        stage.addActor(gameOverLabel);

        scoreLabel = new Label("Score "+score,new Skin(Gdx.files.internal("glassy/skin/glassy-ui.json")));
        scoreLabel.setSize(origin.x*Sw/ww,Sh/10f);
        scoreLabel.setPosition(0,Sh-scoreLabel.getHeight());
        scoreLabel.setAlignment(Align.center);
        scoreLabel.setFontScale(Sh/400f);

        stage.addActor(scoreLabel);
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        inputControl(delta);
        if (!gameOver){
            update(delta);
        }else{
            if (Gdx.input.justTouched()){
                gameOver = false;
                gameOverLabel.setVisible(false);
                brick = generator.generate();
                for (int i=0;i<board.length;i++){
                    for (int j=0;j<board[0].length;j++){
                        board[i][j] = false;
                    }
                }
                waitingLimit = 0.9f;
                score = 0;
                scoreLabel.setText("Score "+score);
            }

        }
        draw();
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

    public void inputControl(float delta){

        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            brick.left(board);
        }else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            brick.right(board);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            brick.setWaitingLimit(0.01f);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.C)){
            brick.rotate(board);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new TitleScreen(game));
        }
    }

    public void initGrid(){
        origin.x = (ww - (columnNum-1)*size)/2f;
        origin.y = 0;

        grid = new float[4*columnNum + 4*rowNum];

        for (int i=0;i<4*rowNum;i=i+4){
            grid[i] = origin.x;
            grid[i+1] = origin.y + size*(i/4f);

            grid[i+2] = origin.x + (columnNum-1)*size;
            grid[i+3] = origin.y + size*(i/4f);
        }

        for (int i=0;i<4*columnNum;i=i+4){
            grid[4*rowNum + i] = origin.x + size*(i/4f);
            grid[4*rowNum + i+1] = origin.y;
            grid[4*rowNum + i+2] = origin.x + size*(i/4f);
            grid[4*rowNum+ i+3] = origin.y + (rowNum-1f)*size;
        }
    }


    public void update(float delta) {
        brick.update(delta,board);

        if (brick.isStopped){
            Position[] positions = brick.getPositions();
            for (int i=0;i<positions.length;i++){
                board[positions[i].i][positions[i].j] = true;
            }
            brick = generator.generate();
            brick.setWaitingLimit(waitingLimit);
        }

        for (int i=0;i<board.length;i++){
            if (board[i][16]){
                gameOver = true;
                gameOverLabel.setVisible(true);
            }
        }

        for (int j=0;j<board[0].length;){
            isLine = true;
            for (int i=0;i<board.length;i++){
                if (!board[i][j]) isLine = false;
            }

            if (isLine){
                deleteLine(j);
                continue;
            }
            j++;
        }
    }

    public void draw() {

        Main.shapeRenderer.setProjectionMatrix(camera.combined);
        Main.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        Main.shapeRenderer.setColor(backgroundColor);
        Main.shapeRenderer.rect(origin.x, origin.y, size * (columnNum - 1), size * (rowNum - 1));

        Main.shapeRenderer.setColor(gridColor);
        for (int i = 0; i < grid.length; i = i + 4) {
            Main.shapeRenderer.rectLine(grid[i], grid[i + 1], grid[i + 2], grid[i + 3], 1);
        }

        Main.shapeRenderer.end();

        Main.batch.setProjectionMatrix(camera.combined);
        Main.batch.begin();

        brick.draw(Main.batch);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == true){
                    singleBrick.setIndex(i, j);
                    singleBrick.draw(Main.batch);
                }
            }
        }

        Main.batch.end();

        stage.act();
        stage.draw();
    }

    private void deleteLine(int j){
        for (int i=0;i<board.length;i++){
            for (int a = j;a<board[i].length-1;a++){
                board[i][a] = board[i][a+1];
            }

        }
        score += 100;
        scoreLabel.setText("Score "+score);
        waitingLimit -= Math.pow(waitingLimit,4f)/4f;
    }
}

