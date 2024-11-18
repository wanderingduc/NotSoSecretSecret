package org.example.Tiles;

import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TileManager {

    public String mapFile;
    public String doorFile;
    public GamePanel gP;

    public List<List<Tile>> map;
    public Map<String, String[]> doors;
    public BufferedImage grass, rock, water;

    public TileManager(GamePanel gP) {
        this.gP = gP;
        this.mapFile = "testMap1.txt";
        this.doorFile = "testDoor1.txt";

        loadMap();
        loadDoors();
        loadResc();
    }

    public TileManager(GamePanel gP, String mapFile, String doorFile) {
        this.gP = gP;
        this.mapFile = mapFile;
        this.doorFile = doorFile;

        loadMap();
        loadResc();
    }

    public void changeMap(String newMap){
        mapFile = newMap;
        loadMap();
    }

    public void loadResc() {
        try {
            water = ImageIO.read(getClass().getResourceAsStream("/sprite/water.png"));
            rock = ImageIO.read(getClass().getResourceAsStream("/sprite/rock.png"));
            grass = ImageIO.read(getClass().getResourceAsStream("/sprite/grass.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
            loadDoors();


        }catch(IOException e) {
            e.printStackTrace();
        }
        printMap();
    }

    public void setMap(String mapName, String doorName){
        mapFile = mapName;
        doorFile = doorName;
        loadMap();
        gP.p.setDoorMap(doors);
    }

    public void loadDoors(){
        doors = new HashMap<>();

        try{
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream iS = classLoader.getResourceAsStream(doorFile);
            BufferedReader bF = new BufferedReader(new InputStreamReader(iS));
            while(true){
                String line = bF.readLine();
                if(line==null){
                    break;
                }
                String data[] = line.split(";");
                doors.put(data[0], new String[]{data[1], data[2], data[3]});
            }
            printDoors();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void printMap(){
        for(int row=0; row<map.size(); row++){
            for(int col=0; col<map.get(0).size(); col++){
                System.out.print(map.get(row).get(col));

            }
            System.out.println();
        }

    }

    private void printDoors(){
        System.out.println("a");
        System.out.println(doors);
        for(String e : doors.keySet()){
            System.out.print(doors.get(e)[2]);
        }
    }

    public void drawMap(Graphics2D g) {
        Rectangle2D drawTile;
        Color color;
        BufferedImage tileType = null;


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
                    tileType = grass;
                    break;
                case 2:
                    color = Color.GRAY;
                    tileType = rock;
                    break;
                case 3:
                    color = Color.BLUE;
                    tileType = water;
                    break;
                case 4:
                    color = Color.DARK_GRAY;
                    break;
                default:
                    color = Color.RED;
            }
            if(tileType == null) {
                drawTile = new Rectangle(x, y, gP.tileSize, gP.tileSize);
                g.draw(drawTile);
                g.setColor(color);
                g.fill(drawTile);
            }else{
                g.drawImage(tileType, x, y, gP.tileSize, gP.tileSize, null);
                tileType = null;
            }

            col++;
            if(col== gP.maxCol){
                col = 0;
                row++;
            }
        }


    }

}