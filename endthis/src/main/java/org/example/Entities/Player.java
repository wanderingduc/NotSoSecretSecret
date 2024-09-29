package org.example.Entities;

import org.example.GamePanel;
import org.example.Utils.CollisionChecker;
import org.example.Utils.InputHandler;
import org.w3c.dom.ls.LSOutput;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Map;

public class Player extends Entity{

    GamePanel gP;
    public InputHandler iH;
    public char dir;
    public int speed = 2;
    public CollisionChecker cC;
    public int frameC;
    public int lockFrame;
    public boolean movLock = false;
    public boolean spriteStatus;
    public Map<String, String[]> doorMap;

    public Player(GamePanel gP, InputHandler iH){
        this.gP = gP;
        this.iH = iH;
        this.cC = new CollisionChecker(gP, this, gP.tM);
        setDefault();
        setDoorMap(gP.tM.doors);
    }

    public void setDefault(){
        x = (gP.screenWidth/2) - (gP.tileSize/2);
        y = (gP.screenHeight/2) - (gP.tileSize/2);
        frameC = 0;
        lockFrame = 0;
    }

    public void setDoorMap(Map<String, String[]> map){
        this.doorMap = map;
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
        if(movLock){
            lockFrame++;
            if(lockFrame>=30){
                lockFrame=0;
                movLock = false;
            }
        }
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
            if(cC.checkTileCollision() && !movLock) {
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
                int newPos;
                switch (dir) {
                    case 'u':
                        y -= speed;
                        newPos = Integer.parseInt(doorMap.get(String.valueOf(x/gP.tileSize) + "," + String.valueOf(y/gP.tileSize))[2])* gP.tileSize;
                        gP.tM.setMap(gP.tM.doors.get(String.valueOf(x/gP.tileSize) + "," + String.valueOf(y/gP.tileSize))[0], gP.tM.doors.get(String.valueOf(x/gP.tileSize) + "," + String.valueOf(y/gP.tileSize))[1]);
                        y = newPos - 1;// - Integer.parseInt(gP.tM.doors.get(String.valueOf(x/gP.tileSize) + "," + String.valueOf(y/gP.tileSize))[2]);
                        break;
                    case 'd':
                        y += speed;
                        newPos = Integer.parseInt(doorMap.get(String.valueOf(x/gP.tileSize) + "," + String.valueOf((y+ gP.tileSize)/gP.tileSize))[2])* gP.tileSize;
                        gP.tM.setMap(gP.tM.doors.get(String.valueOf(x/gP.tileSize) + "," + String.valueOf((y+ gP.tileSize)/gP.tileSize))[0], gP.tM.doors.get(String.valueOf(x/gP.tileSize) + "," + String.valueOf((y+ gP.tileSize)/gP.tileSize))[1]);
                        y = newPos + 1;// + Integer.parseInt(gP.tM.doors.get(String.valueOf(x/gP.tileSize) + "," + String.valueOf((y+ gP.tileSize)/gP.tileSize))[2]);
                        break;
                    case 'l':
                        x -= speed;
                        System.out.println(String.valueOf(x/gP.tileSize) + "," + String.valueOf(y/gP.tileSize));
                        newPos = Integer.parseInt(doorMap.get(String.valueOf(x/gP.tileSize) + "," + String.valueOf(y/gP.tileSize))[2])*gP.tileSize;
                        gP.tM.setMap(gP.tM.doors.get(String.valueOf(x/gP.tileSize) + "," + String.valueOf(y/gP.tileSize))[0], gP.tM.doors.get(String.valueOf(x/gP.tileSize) + "," + String.valueOf(y/gP.tileSize))[1]);
                        x = newPos - 1;// - Integer.parseInt(gP.tM.doors.get(String.valueOf(x/gP.tileSize) + "," + String.valueOf(y/gP.tileSize))[2]);
                        break;
                    case 'r':
                        x += speed;
                        newPos = Integer.parseInt(doorMap.get(String.valueOf((x+ gP.tileSize)/gP.tileSize) + "," + String.valueOf(y/gP.tileSize))[2])* gP.tileSize;
                        gP.tM.setMap(gP.tM.doors.get(String.valueOf((x+ gP.tileSize)/gP.tileSize) + "," + String.valueOf(y/gP.tileSize))[0], gP.tM.doors.get(String.valueOf((x+ gP.tileSize)/gP.tileSize) + "," + String.valueOf(y/gP.tileSize))[1]);
                        x = newPos + 1;// + Integer.parseInt(gP.tM.doors.get(String.valueOf((x+ gP.tileSize)/gP.tileSize) + "," + String.valueOf(y/gP.tileSize))[2]);
                        break;
                }
                movLock = true;
                // REMOVE AFTER TESTING
                System.out.println("This is door");
            }
        }
    }



}
