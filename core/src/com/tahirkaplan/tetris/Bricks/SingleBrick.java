package com.tahirkaplan.tetris.Bricks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tahirkaplan.tetris.Extras.Position;

public class SingleBrick extends Brick {

    public SingleBrick(int i, int j, int squareRowNum, int squareColumnNum, float size, Vector2 origin) {
        super(i, j, squareRowNum, squareColumnNum, size, origin);

        num = 1;

        texture = new Texture("blue.png");
        positions = new Position[num];
        positions[0] = new Position(i,j,false);

    }


    @Override
    public void update(float delta,boolean[][] board) {

    }

    @Override
    public void setIndex(int i, int j) {
        this.i = i;
        this.j = j;
        positions[0].i = i;
        positions[0].j = j;
    }

    @Override
    public void left(boolean[][] board) {

    }

    @Override
    public void right(boolean[][] board) {

    }

    @Override
    public void rotate(boolean[][] board) {

    }


}
