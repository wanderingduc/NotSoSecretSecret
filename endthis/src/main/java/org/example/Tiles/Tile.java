package org.example.Tiles;

public class Tile {

    public int type;
    public boolean collisionStatus;
    public boolean doorStatus = false;
    public int row;
    public int col;

    public Tile(int type, int row, int col){
        this.type = type;
        this.row = row;
        this.col = col;

        setCollision(type);
    }

    public void setCollision(int type){
        if(type==1){
            collisionStatus=false;
        }else if(type==4){
            collisionStatus=true;
            doorStatus=true;
        }else{
            collisionStatus=true;
        }
    }

}
