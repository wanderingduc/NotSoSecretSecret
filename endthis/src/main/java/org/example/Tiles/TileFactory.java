package org.example.Tiles;

public class TileFactory {

    public TileFactory(){};

    public Tile getTile(int type){
        return new Tile(type);
    }

}
