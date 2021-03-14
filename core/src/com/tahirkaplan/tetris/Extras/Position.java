package com.tahirkaplan.tetris.Extras;

public class Position {
    public int i,j;
    public boolean isEmpty;

    public Position(){
    }

    public Position(int i,int j,boolean isEmpty){
        this.i = i;
        this.j = j;
        this.isEmpty = isEmpty;
    }
}
