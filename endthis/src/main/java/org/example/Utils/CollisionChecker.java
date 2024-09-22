package org.example.Utils;

import org.example.Entities.Player;
import org.example.GamePanel;
import org.example.Tiles.Tile;
import org.example.Tiles.TileManager;

public class CollisionChecker {

    public GamePanel gP;
    public Player p;
    public TileManager tM;

    public CollisionChecker(GamePanel gP, Player p, TileManager tM){
        this.tM = tM;
        this.p = p;
        this.gP = gP;
    }

    public boolean checkTile(){
        int nextTileColLeft;
        int nextTileRowUp;
        int nextTileColRight;
        int nextTileRowDown;
        if(p.dir=='u') {
            nextTileRowUp = (p.y - p.speed) / gP.tileSize;
            nextTileRowDown = (nextTileRowUp + gP.tileSize) / gP.tileSize;
            if(!tM.map.get(nextTileRowUp).get(p.x).collisionStatus){
                return false;
            }
        }
        if(p.dir=='d'){
            nextTileRowUp = (p.y + p.speed) / gP.tileSize;
            nextTileRowDown = (nextTileRowUp + gP.tileSize) / gP.tileSize;
            if(!tM.map.get(nextTileRowDown).get(p.x).collisionStatus){
                return false;
            }
        }
        if(p.dir=='l'){
            nextTileColLeft = (p.x - p.speed) / gP.tileSize;
            nextTileColRight = (nextTileColLeft + gP.tileSize) / gP.tileSize;
            if(!tM.map.get(p.y).get(nextTileColLeft).collisionStatus){
                return false;
            }
        }
        if(p.dir=='r'){
            nextTileColLeft = (p.x + p.speed) / gP.tileSize;
            nextTileColRight = (nextTileColLeft + gP.tileSize) / gP.tileSize;
            if(!tM.map.get(p.x).get(nextTileColRight).collisionStatus){
                return false;
            }
        }
        return true;
    }



}
