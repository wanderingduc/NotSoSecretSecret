package org.example.Entities;

import org.example.GamePanel;
import org.example.Utils.CollisionChecker;
import org.example.Utils.InputHandler;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Player {

    public int x;
    public int y;
    GamePanel gP;
    public InputHandler iH;
    public char dir;
    public int speed = 2;
    public CollisionChecker cC;
    public int frameC;
    public boolean spriteStatus;

    public Player(GamePanel gP, InputHandler iH){
        this.gP = gP;
        this.iH = iH;
        this.cC = new CollisionChecker(gP, this, gP.tM);
        setDefault();
    }

    public void setDefault(){
        x = (gP.screenWidth/2) - (gP.tileSize/2);
        y = (gP.screenHeight/2) - (gP.tileSize/2);
        frameC = 0;
    }

    public void drawPlayer(Graphics2D g){
        Color pColor;
        if(spriteStatus){
            pColor = Color.MAGENTA;
        }else{
            pColor = Color.PINK;
        }
        Rectangle2D player = new Rectangle(x, y, gP.tileSize, gP.tileSize);
        g.draw(player);
        g.setColor(pColor);
        g.fill(player);
    }

    public void update(){
        if(iH.up==true || iH.down==true || iH.left==true || iH.right){
            frameC++;
            if(frameC%12==0){
                frameC = 0;
                spriteStatus = !spriteStatus;
            }
            if(iH.up){
                dir = 'u';
            }
            if(iH.down){
                dir = 'd';
            }
            if(iH.left){
                dir = 'l';
            }
            if(iH.right){
                dir = 'r';
            }
            if(cC.checkTileCollision()) {
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
            if(cC.checkTileDoor()){
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
                gP.tM.setMap(gP.tM.doors.get(String.valueOf(x/gP.tileSize) + "," + String.valueOf(y/gP.tileSize)));
                // REMOVE AFTER TESTING
                System.out.println("This is door");
            }
        }
    }

}
