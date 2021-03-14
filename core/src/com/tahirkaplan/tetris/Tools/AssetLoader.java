package com.tahirkaplan.tetris.Tools;

import com.badlogic.gdx.graphics.Texture;
import com.tahirkaplan.tetris.Main;

public class AssetLoader {

    public static void load(){
        Main.manager.load("red.png", Texture.class);
        Main.manager.load("green.png", Texture.class);
        Main.manager.load("blue.png", Texture.class);
        Main.manager.load("yellow.png", Texture.class);
        Main.manager.load("white.png", Texture.class);

        Main.manager.load("exit/1.png",Texture.class);
        Main.manager.load("exit/2.png",Texture.class);
        Main.manager.load("exit/3.png",Texture.class);
        Main.manager.load("exit/4.png",Texture.class);
    }

}
