package org.example.Tiles;

import org.example.GamePanel;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TileManager {

    public String mapFile;
    public GamePanel gP;
    public TileFactory tF = new TileFactory();

    public List<List<Tile>> map;

    public TileManager(GamePanel gP) {
        this.gP = gP;
        this.mapFile = "testMap1.txt";

        loadMap();
    }

    public TileManager(GamePanel gP, String mapFile) {
        this.gP = gP;
        this.mapFile = mapFile;

        loadMap();
    }

    public void loadMap() {
        map = new ArrayList<List<Tile>>();
        try {

            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream(mapFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gP.maxCol && row < gP.maxRow) {
                map.add(new ArrayList<>());
                String line = br.readLine();

                while(col < gP.maxCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    map.get(row).add(col, new Tile(num, row, col));
                    col++;
                }
                if(col == gP.maxCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch(IOException e) {
            e.printStackTrace();
        }
        printMap();
    }

    private void printMap(){
        for(int row=0; row<map.size(); row++){
            for(int col=0; col<map.get(0).size(); col++){
                System.out.print(map.get(row).get(col));

            }
            System.out.println();
        }

    }

    public void drawMap(Graphics2D g) {
        Rectangle2D drawTile;
        Color color;

        int col = 0;
        int row = 0;
        int x;
        int y;
        int tile;

        while (col < gP.maxCol && row < gP.maxRow) {
            x = col * gP.tileSize;
            y = row * gP.tileSize;
            tile = map.get(row).get(col).type;
            switch (tile) {
                case 1:
                    color = Color.GREEN;
                    break;
                case 2:
                    color = Color.GRAY;
                    break;
                case 3:
                    color = Color.BLUE;
                    break;
                default:
                    color = Color.RED;
            }

            drawTile = new Rectangle(x, y, gP.tileSize, gP.tileSize);
            g.draw(drawTile);
            g.setColor(color);
            g.fill(drawTile);

            col++;
            if(col== gP.maxCol){
                col = 0;
                row++;
            }
        }


    }

}