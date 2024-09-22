package org.example.Entities;

import org.example.GamePanel;
import org.example.Utils.CollisionChecker;
import org.example.Utils.InputHandler;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.InputStream;

public class Player {

    public int x;
    public int y;
    GamePanel gP;
    public InputHandler iH;
    public char dir;
    public int speed = 2;
    public CollisionChecker cC;

    public Player(GamePanel gP, InputHandler iH){
        this.gP = gP;
        this.iH = iH;
        this.cC = new CollisionChecker(gP, this, gP.tM);
        setDefaultPos();
    }

    public void setDefaultPos(){
        x = (gP.screenWidth/2) - (gP.tileSize/2);
        y = (gP.screenHeight/2) - (gP.tileSize/2);
    }

    public void drawPlayer(Graphics2D g){
        Rectangle2D player = new Rectangle(x, y, gP.tileSize, gP.tileSize);
        g.draw(player);
        g.setColor(Color.MAGENTA);
        g.fill(player);
    }

    public void updatePos(){
        if(iH.up==true || iH.down==true || iH.left==true || iH.right){
            if(iH.up==true){
                dir = 'u';
            }
            if(iH.down==true){
                dir = 'd';
            }
            if(iH.left==true){
                dir = 'l';
            }
            if(iH.right==true){
                dir = 'r';
            }
            if(cC.checkTile()) {
                switch (dir) {
                    case 'u':
                        y -= speed;
                        break;
                    case 'd':
                        y += speed;
                        break;
                    case 'l':
                        x -= speed;
                        break;
                    case 'r':
                        x += speed;
                        break;
                }
            }
        }
    }

}
