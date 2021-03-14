package com.tahirkaplan.tetris.Bricks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tahirkaplan.tetris.Extras.Position;
import com.tahirkaplan.tetris.Main;

public class Stick extends Brick {

    private enum Rotation{
        Vertical,Horizontal
    }

    private Rotation rotation;

    public Stick(int i, int j, int squareRowNum, int squareColumnNum, float size, Vector2 origin) {
        super(i, j, squareRowNum, squareColumnNum, size, origin);

        num = 4;

        texture = Main.manager.get("yellow.png",Texture.class);
        positions = new Position[num];

        for (int a=0;a<positions.length;a++){
            positions[a] = new Position();
        }

        positions[0].i = i;
        positions[0].j = j;
        positions[0].isEmpty = false;

        positions[1].i = i;
        positions[1].j = j+1;
        positions[1].isEmpty = false;

        positions[2].i = i;
        positions[2].j = j+2;
        positions[2].isEmpty = false;

        positions[3].i = i;
        positions[3].j = j+3;
        positions[3].isEmpty = false;

        rotation = Rotation.Vertical;

    }

    @Override
    public void update(float delta, boolean[][] board) {
        waitingTime += delta;
        if (waitingTime >= waitingLimit && !isStopped){
            waitingTime %= waitingLimit;
            for (int i=0;i<positions.length;i++){
                if (positions[i].j == 0)
                    isStopped = true;
                else if (board[positions[i].i][positions[i].j -1])
                    isStopped = true;
            }

            if (!isStopped){
                j = j-1;
                setIndex(i,j);
            }

            for (int i=0;i<positions.length;i++){
                if (positions[i].j == 0){
                    waitingLimit *= 0.8f;
                }else if (board[positions[i].i][positions[i].j -1]){
                    waitingLimit *= 0.8f;
                }
            }
        }
    }

    @Override
    public void setIndex(int i, int j) {
        this.i = i;
        this.j = j;

        if (rotation == Rotation.Vertical){
            positions[0].i = i;
            positions[0].j = j;

            positions[1].i = i;
            positions[1].j = j+1;

            positions[2].i = i;
            positions[2].j = j+2;

            positions[3].i = i;
            positions[3].j = j+3;
        }else if (rotation == Rotation.Horizontal){
            positions[0].i = i;
            positions[0].j = j;

            positions[1].i = i+1;
            positions[1].j = j;

            positions[2].i = i+2;
            positions[2].j = j;

            positions[3].i = i+3;
            positions[3].j = j;
        }

    }

    public void left(boolean[][] board){
        boolean empty = true;
        for (Position position : positions) {
            if (position.i == 0) {
                empty = false;
                break;
            }
            if (board[position.i - 1][position.j]){
                empty = false;
                break;
            }
        }

        if (empty){
            i = i-1;
            setIndex(i,j);
        }
    }

    public void right(boolean[][] board){
        boolean empty = true;
        for (Position position : positions) {
            if (position.i == squareColumnNum-1) {
                empty = false;
                break;
            }
            if (board[position.i + 1][position.j]){
                empty = false;
                break;
            }
        }

        if (empty){
            i = i+1;
            setIndex(i,j);
        }
    }

    @Override
    public void rotate(boolean[][] board){
        if (rotation == Rotation.Vertical){
            if (i<squareColumnNum-3 && !board[i+1][j] && !board[i+2][j] && !board[i+3][j]){
                rotation = Rotation.Horizontal;
                setIndex(i,j);
            }
        }else if (rotation == Rotation.Horizontal){
            if (!board[i][j + 1] && !board[i+2][j] && !board[i+3][j]) {
                rotation = Rotation.Vertical;
                setIndex(i,j);
            }
        }
    }
}
