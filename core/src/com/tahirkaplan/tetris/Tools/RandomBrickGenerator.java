package com.tahirkaplan.tetris.Tools;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.tahirkaplan.tetris.Bricks.Brick;
import com.tahirkaplan.tetris.Bricks.LBrick;
import com.tahirkaplan.tetris.Bricks.SquareBrick;
import com.tahirkaplan.tetris.Bricks.Stick;
import com.tahirkaplan.tetris.Bricks.TBrick;
import com.tahirkaplan.tetris.Bricks.ZBrick;
import com.tahirkaplan.tetris.Screens.GameScreen;

import java.util.Random;

public class RandomBrickGenerator {

    private int squareRowNum;
    private int squareColumnNum;
    private float size;
    private Vector2 origin;

    private Random random;

    public RandomBrickGenerator(int squareRowNum,int squareColumnNum,float size,Vector2 origin){
        this.squareRowNum = squareRowNum;
        this.squareColumnNum = squareColumnNum;
        this.size = size;
        this.origin = origin;

        random = new Random();
    }

    public Brick generate(){
        int r = random.nextInt(5);
        if (r==0){
            return new SquareBrick(random.nextInt(squareColumnNum-2), GameScreen.verticalSquareNum,squareRowNum,squareColumnNum,size,origin);
        }else if (r==1){
            return new LBrick(random.nextInt(squareColumnNum-2),16,GameScreen.verticalSquareNum,squareColumnNum,size,origin);
        }else if (r==2){
            return new Stick(random.nextInt(squareColumnNum-1),16,GameScreen.verticalSquareNum,squareColumnNum,size,origin);
        }else if (r== 3){
            return new TBrick(1 + random.nextInt(squareColumnNum-3), GameScreen.verticalSquareNum,squareRowNum,squareColumnNum,size,origin);
        }else {
            return new ZBrick(random.nextInt(squareColumnNum-3),16,GameScreen.verticalSquareNum,squareColumnNum,size,origin);
        }
    }

}
