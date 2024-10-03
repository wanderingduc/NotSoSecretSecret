package org.example.Utils;

import org.example.Entities.EntityManager;
import org.example.Entities.Player;
import org.example.GamePanel;
import org.example.Tiles.TileManager;

public class CollisionChecker {

    public GamePanel gP;
    public Player p;
    public TileManager tM;
    public EntityManager eM;

    public CollisionChecker(GamePanel gP, Player p, TileManager tM, EntityManager eM){
        this.tM = tM;
        this.p = p;
        this.gP = gP;
        this.eM = eM;
    }

    public boolean checkTileCollision(){
        int playerX = p.x;
        int playerY = p.y;
        int nextTileCol;
        int nextTileColE;
        int nextTileRow;
        int nextTileRowE;
        if(p.dir=='u'){
            nextTileRow = (playerY - p.speed)/gP.tileSize;
            nextTileRowE = (playerY - p.speed + gP.tileSize)/gP.tileSize;
            nextTileCol = playerX/gP.tileSize;
            nextTileColE = (playerX + gP.tileSize)/gP.tileSize;

            if(playerY-p.speed < 0 || tM.map.get(nextTileRow).get(nextTileCol).collisionStatus ||
                    tM.map.get(nextTileRowE).get(nextTileColE).collisionStatus ||
                    tM.map.get(nextTileRow).get(nextTileColE).collisionStatus ||
                    tM.map.get(nextTileRowE).get(nextTileCol).collisionStatus){
                return false;
            }
        }
        if(p.dir=='d'){
            nextTileRow = (playerY + p.speed)/gP.tileSize;
            nextTileRowE = (playerY + p.speed + gP.tileSize)/gP.tileSize;
            nextTileCol = playerX/gP.tileSize;
            nextTileColE = (playerX + gP.tileSize)/gP.tileSize;
            if(playerY+p.speed > tM.map.size()*gP.tileSize || tM.map.get(nextTileRow).get(nextTileCol).collisionStatus ||
                    tM.map.get(nextTileRowE).get(nextTileColE).collisionStatus ||
                    tM.map.get(nextTileRow).get(nextTileColE).collisionStatus ||
                    tM.map.get(nextTileRowE).get(nextTileCol).collisionStatus){
                return false;
            }
        }
        if(p.dir=='l'){
            nextTileRow = playerY/gP.tileSize;
            nextTileRowE = (playerY + gP.tileSize)/gP.tileSize;
            nextTileCol = (playerX - p.speed)/ gP.tileSize;
            nextTileColE = (playerX - p.speed + gP.tileSize)/gP.tileSize;
            if(nextTileCol <= 0 || tM.map.get(nextTileRow).get(nextTileCol).collisionStatus ||
                    tM.map.get(nextTileRowE).get(nextTileColE).collisionStatus ||
                    tM.map.get(nextTileRow).get(nextTileColE).collisionStatus ||
                    tM.map.get(nextTileRowE).get(nextTileCol).collisionStatus){
                return false;
            }
        }
        if(p.dir=='r'){
            nextTileRow = (playerY)/ gP.tileSize;
            nextTileRowE = (playerY + gP.tileSize)/gP.tileSize;
            nextTileCol = (playerX + p.speed)/ gP.tileSize;
            nextTileColE = (playerX + p.speed + gP.tileSize)/gP.tileSize;
            if(nextTileCol >= tM.map.get(0).size() || tM.map.get(nextTileRow).get(nextTileCol).collisionStatus ||
            tM.map.get(nextTileRowE).get(nextTileColE).collisionStatus ||
                    tM.map.get(nextTileRow).get(nextTileColE).collisionStatus ||
                    tM.map.get(nextTileRowE).get(nextTileCol).collisionStatus){
                return false;
            }
        }
        return true;
    }

    public boolean checkTileDoor(){
        int playerX = p.x;
        int playerY = p.y;
        int nextTileCol;
        int nextTileColE;
        int nextTileRow;
        int nextTileRowE;
        if(p.dir=='u'){
            nextTileRow = (playerY - p.speed)/gP.tileSize;
            nextTileRowE = (playerY - p.speed + gP.tileSize)/gP.tileSize;
            nextTileCol = playerX/gP.tileSize;
            nextTileColE = (playerX + gP.tileSize)/gP.tileSize;

            if(!(tM.map.get(nextTileRow).get(nextTileCol).doorStatus &&
                    tM.map.get(nextTileRow).get(nextTileColE).doorStatus)){
                return false;
            }
        }
        if(p.dir=='d'){
            nextTileRow = (playerY + p.speed)/gP.tileSize;
            nextTileRowE = (playerY + p.speed + gP.tileSize)/gP.tileSize;
            nextTileCol = playerX/gP.tileSize;
            nextTileColE = (playerX + gP.tileSize)/gP.tileSize;
            if(!(tM.map.get(nextTileRowE).get(nextTileCol).doorStatus &&
                    tM.map.get(nextTileRowE).get(nextTileColE).doorStatus)){
                return false;
            }
        }
        if(p.dir=='l'){
            nextTileRow = playerY/gP.tileSize;
            nextTileRowE = (playerY + gP.tileSize)/gP.tileSize;
            nextTileCol = (playerX - p.speed)/ gP.tileSize;
            nextTileColE = (playerX - p.speed + gP.tileSize)/gP.tileSize;
            if(!(tM.map.get(nextTileRow).get(nextTileCol).doorStatus &&
                    tM.map.get(nextTileRowE).get(nextTileCol).doorStatus)){
                return false;
            }
        }
        if(p.dir=='r'){
            nextTileRow = (playerY)/ gP.tileSize;
            nextTileRowE = (playerY + gP.tileSize)/gP.tileSize;
            nextTileCol = (playerX + p.speed)/ gP.tileSize;
            nextTileColE = (playerX + p.speed + gP.tileSize)/gP.tileSize;
            if(!(tM.map.get(nextTileRow).get(nextTileColE).doorStatus &&
                    tM.map.get(nextTileRowE).get(nextTileColE).doorStatus)){
                return false;
            }
        }
        return true;
    }

    public boolean checkTileNPC(){
        int playerX = p.x;
        int playerY = p.y;
        int nextTileCol;
        int nextTileColE;
        int nextTileRow;
        int nextTileRowE;
        if(p.dir=='u'){
            nextTileRow = (playerY - p.speed)/gP.tileSize;
            nextTileRowE = (playerY - p.speed + gP.tileSize)/gP.tileSize;
            nextTileCol = playerX/gP.tileSize;
            nextTileColE = (playerX + gP.tileSize)/gP.tileSize;

            if((eM.entityMap.get(String.valueOf(nextTileCol)+String.valueOf(nextTileRow))==null && eM.entityMap.get(String.valueOf(nextTileColE)+String.valueOf(nextTileRow))==null)){
                return false;
            }
        }
        if(p.dir=='d'){
            nextTileRow = (playerY + p.speed)/gP.tileSize;
            nextTileRowE = (playerY + p.speed + gP.tileSize)/gP.tileSize;
            nextTileCol = playerX/gP.tileSize;
            nextTileColE = (playerX + gP.tileSize)/gP.tileSize;
            if((eM.entityMap.get(String.valueOf(nextTileCol)+String.valueOf(nextTileRowE))==null & eM.entityMap.get(String.valueOf(nextTileColE)+String.valueOf(nextTileRowE))==null)){
                return false;
            }
        }
        if(p.dir=='l'){
            nextTileRow = playerY/gP.tileSize;
            nextTileRowE = (playerY + gP.tileSize)/gP.tileSize;
            nextTileCol = (playerX - p.speed)/ gP.tileSize;
            nextTileColE = (playerX - p.speed + gP.tileSize)/gP.tileSize;
            if((eM.entityMap.get(String.valueOf(nextTileCol)+String.valueOf(nextTileRow))==null && eM.entityMap.get(String.valueOf(nextTileCol)+String.valueOf(nextTileRowE))==null)){
                return false;
            }
        }
        if(p.dir=='r'){
            nextTileRow = (playerY)/ gP.tileSize;
            nextTileRowE = (playerY + gP.tileSize)/gP.tileSize;
            nextTileCol = (playerX + p.speed)/ gP.tileSize;
            nextTileColE = (playerX + p.speed + gP.tileSize)/gP.tileSize;
            if((eM.entityMap.get(String.valueOf(nextTileColE)+String.valueOf(nextTileRow))==null && eM.entityMap.get(String.valueOf(nextTileColE)+String.valueOf(nextTileRowE))==null)){
                return false;
            }
        }
        return true;
    }



}
