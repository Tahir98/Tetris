package com.tahirkaplan.tetris.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;


public class FontGenerator {

    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    public FontGenerator(){
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    }

    public BitmapFont generateFont(){
        if(generator == null){
            generator = new FreeTypeFontGenerator(Gdx.files.internal("truetypefont/Amble-Light.ttf"));
        }

        return  generator.generateFont(parameter);
    }

    public void setSize(int size){
        if(size > 0)
            parameter.size = size;
        else
            System.out.println("Size must be greater than 0");
    }

    public int getSize(){
        return parameter.size;
    }

    public void setBorderWidth(float width){
        if (width >= 0)
            parameter.borderWidth = width;
        else
            System.out.println("Border width must be greater than or equal to 0");
    }

    public float getBorderWidth(){
        return parameter.borderWidth;
    }

    public void setBorderColor(Color color){
        parameter.borderColor = color;
    }

    public Color getBorderColor(){
        return parameter.borderColor;
    }

    public void setColor(Color color){
        parameter.color = color;
    }

    public Color getColor(){
        return parameter.color;
    }



    public void setShadowOffset(Vector2 offset){
        parameter.shadowOffsetX = (int)offset.x;
        parameter.shadowOffsetY = (int)offset.y;
    }

    public Vector2 getShadowOffset(){
        return new Vector2(parameter.shadowOffsetX,parameter.shadowOffsetY);
    }

    public void setShadowColor(Color color){
        parameter.shadowColor = color;
    }

    public Color getShadowColor(){
        return parameter.shadowColor;
    }

    public void setSpace(Vector2 vec){
        parameter.spaceX = (int)vec.x;
        parameter.spaceY = (int)vec.y;
    }
    public Vector2 getSpace(){
        return new Vector2(parameter.spaceX,parameter.spaceY);
    }
}