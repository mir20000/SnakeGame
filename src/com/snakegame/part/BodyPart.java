package com.snakegame.part;

import java.awt.*;

public class BodyPart {
    private int xco, yco, width, height;
    public BodyPart(int xco, int yco, int tile){
        this.xco = xco;
        this.yco = yco;
        width = tile;
        height = tile;
    }

    public void tick(){

    }

    public void draw(Graphics gfx){
        gfx.setColor(Color.BLACK);
        gfx.fillRect(xco*width,yco*height,width,height);
        gfx.setColor(new Color(10,50,0));
        gfx.fillRect(xco*width+1,yco*height+1,width-1,height-1);
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
