package org.example.Tiles;

public class Tile {

    int type;
    boolean collisionStatus;

    public Tile(int type){
        this.type = type;
    }

    public void setCollision(int type){
        if(type!=1){
            collisionStatus=true;
        }else{
            collisionStatus=false;
        }
    }

}
