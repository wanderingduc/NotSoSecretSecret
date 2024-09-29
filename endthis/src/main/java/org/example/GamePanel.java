package org.example;

import org.example.Entities.EntityManager;
import org.example.Entities.Player;
import org.example.Tiles.TileManager;
import org.example.Utils.InputHandler;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    public final int maxCol = 24;
    public final int maxRow = 16;
    public final int defTile = 16;
    public final int tileMult = 3;
    public final int tileSize = defTile*tileMult;
    public final int screenHeight = maxRow*tileSize;
    public final int screenWidth = maxCol*tileSize;
    public TileManager tM = new TileManager(this);
    public EntityManager eM = new EntityManager(this);
    public Player p;
    public InputHandler iH = new InputHandler();


    public int FPS = 60;
    public double drawDelta = 1000000000/FPS;
    public double delta=0;
    public long currTime;
    public long prevTime = System.nanoTime();
    public Thread gameThread;


    public GamePanel(){
        this.p = new Player(this, iH);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setOpaque(true);
        this.setDoubleBuffered(true);
        this.addKeyListener(iH);
        this.setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        tM.drawMap(g2);
        p.drawPlayer(g2);
        eM.drawEntities(g2);
    }

    public void startGame(){
        System.out.println("Starting game");
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        p.update();
    }

    @Override
    public void run() {

        while(gameThread!=null) {
            currTime = System.nanoTime();

            delta += (currTime - prevTime) / drawDelta;
            prevTime = currTime;

            if (delta > 1) {

                update();
                repaint();
                delta--;
            }
        }
    }
}
