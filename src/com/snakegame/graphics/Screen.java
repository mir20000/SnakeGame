package com.snakegame.graphics;

import com.snakegame.part.BodyPart;
import com.snakegame.part.Food;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Screen extends JPanel implements Runnable {
    public static final int WIDTH =800, HEIGHT =800;
    private Thread thread;
    private boolean running = false;

    private BodyPart b;
    private ArrayList <BodyPart> snake;

    private Food food;
    private ArrayList <Food> foods;

    private Random rand;

    private int xco = 10,yco = 10;
    private int size = 5;

    private boolean right= true,left=false,up=false,down=false;
    private int ticks=0;
    private Key key;
    public Screen(){
        setFocusable(true);
        key = new Key();
        addKeyListener(key);

        setPreferredSize(new Dimension(WIDTH,HEIGHT));

        snake = new ArrayList<BodyPart>();
        foods = new ArrayList<Food>();
        rand = new Random();

        start();
    }
    public void start(){
        running =true;
        thread =new Thread(this, " Snake Game");
        thread.start();
    }
    public void run(){
        while (running ){
            tick();
            repaint();
        }
    }

    public void tick(){
        if(snake.size()==0){
            b = new BodyPart(xco, yco,10);
            snake.add(b);
        }

        if (foods.size()==0){
            int xco =rand.nextInt(79);
            int yco =rand.nextInt(79);
            food =new Food(xco,yco,10);
            foods.add(food);
        }

        for(int i = 0; i < foods.size();i++){
            if (xco == foods.get(i).getXco() && yco == foods.get(i).getYco()){
            size++;
            foods.remove(i);
            i--;}
        }

        for(int i = 0; i < snake.size();i++){
            if (xco == snake.get(i).getXco() && yco == snake.get(i).getYco()){
                if (i != snake.size()-1){
                    stop();
                }
            }
        }

        if( xco<0 || xco>79 || yco<0 || yco>79 ){
            stop();
        }

        ticks++;

        if(ticks>25000){
            if(right)xco++;
            if (left)xco--;
            if (up)yco--;
            if(down)yco++;

            ticks =0;
            try {
                Thread.sleep(100);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            b = new BodyPart(xco,yco,10);
            snake.add(b);

            if(snake.size() > size){
                snake.remove(0);
            }
        }
    }

    private class Key implements KeyListener{
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key==KeyEvent.VK_RIGHT && !left){
                up = false;
                down = false;
                right = true;
            }
            if (key==KeyEvent.VK_LEFT && !right){
                up = false;
                down = false;
                left = true;
            }
            if (key==KeyEvent.VK_UP && !down){
                left = false;
                right = false;
                up = true;
            }
            if (key==KeyEvent.VK_DOWN && !up){
                left = false;
                right = false;
                down = true;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    public void paint(Graphics gfx){
        gfx.clearRect(0,0,WIDTH,HEIGHT);

        gfx.setColor(Color.GREEN);
        gfx.fillRect(0,0,WIDTH,HEIGHT);

        gfx.setColor(Color.WHITE);
        for(int i = 0; i<WIDTH/10;i++){
            gfx.drawLine(i*10,0,i*10,HEIGHT);
        }
        for(int i = 0; i<HEIGHT/10;i++){
            gfx.drawLine(0,i*10,WIDTH,i*10);
        }
        for (int i= 0 ; i < snake.size(); i++){
            snake.get(i).draw(gfx);
        }
        for (int i= 0 ; i < foods.size(); i++){
            foods.get(i).draw(gfx);
        }
    }
    public void stop(){
        running=false;
        System.out.println("Your Score");
        System.out.println(size-5);
        System.exit(0);

    }
}
