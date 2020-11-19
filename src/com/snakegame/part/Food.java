package com.snakegame.part;

import java.awt.*;

public class Food {
    private int xco, yco,width,height;
    public Food(int xco, int yco, int tile){
        this.xco = xco;
        this.yco = yco;
        width = tile;
        height = tile;
    }

    public void tick(){

    }

    public void draw(Graphics gfx){
        gfx.setColor(Color.RED);
        gfx.fillRect(xco*width,yco*height,width,height);
    }

    public int getXco() {
        return xco;
    }

    public void setXco(int xco) {
        this.xco = xco;
    }

    public int getYco() {
        return yco;
    }

    public void setYco(int yco) {
        this.yco = yco;
    }
}
