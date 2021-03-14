package com.tahirkaplan.tetris.Bricks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tahirkaplan.tetris.Extras.Position;

public abstract class Brick {

    protected int i,j;
    protected int squareRowNum;
    protected int squareColumnNum;
    protected float size;

    protected Texture texture;
    protected int num;

    protected Position[] positions;
    protected Vector2 origin;

    protected float waitingTime = 0f;
    protected float waitingLimit = 1.5f;

    public boolean isStopped = false;


    public  Brick(){

    }

    public Brick(int i,int j,int squareRowNum,int squareColumnNum,float size,Vector2 origin){
        this.i = i;
        this.j = j;
        this.squareRowNum = squareRowNum;
        this.squareColumnNum = squareColumnNum;
        this.size = size;
        this.origin = origin;
    }

    public Position[] getPositions(){
        return positions;
    }

    public void setPositions(Position[] positions){
        this.positions = positions;
    }

    public void draw(SpriteBatch batch){
        for (int i=0;i<num;i++){
            batch.draw(texture,origin.x + positions[i].i*size,origin.y + positions[i].j*size,size,size);
        }
    }
    public abstract void update(float delta,boolean[][] board);

    public abstract void setIndex(int i,int j);

    public abstract void left(boolean[][] board);
    public abstract void right(boolean[][] board);
    public abstract void rotate(boolean[][] board);
    public  float getWaitingLimit(){
        return  waitingLimit;
    }
    public  void setWaitingLimit(float waitingLimit){
        if (waitingLimit > 0)
            this.waitingLimit = waitingLimit;
    }
}