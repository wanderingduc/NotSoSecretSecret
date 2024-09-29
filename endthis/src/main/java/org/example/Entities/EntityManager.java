package org.example.Entities;

import org.example.GamePanel;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class EntityManager {

    public List<Entity> entities;
    public String entityFile;
    public GamePanel gP;

    public EntityManager(GamePanel gP, String entityFile){
        this.gP = gP;
        this.entityFile = entityFile;
    }

    public void loadEntities(){
        try{
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream iS = classLoader.getResourceAsStream(entityFile);
            BufferedReader bR = new BufferedReader(new InputStreamReader(iS));

            while(true){
                String line = bR.readLine();
                if(line==null){
                    break;
                }
                String data[] = line.split(";");
                String pos[] = data[0].split(",");
                int x = Integer.parseInt(pos[0]);
                int y = Integer.parseInt(pos[1]);
                List<String> scripts = new ArrayList<>();
                if(data[2]!="e"){
                    String script[] = data[2].split("~");
                    for(String s : script){
                        scripts.add(s);
                    }
                }
                switch(data[1]){
                    case "npc":
                        entities.add(new NonPlayCharacter(x, y, scripts));
                        break;
                    default:
                        break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void drawEntities(Graphics2D g){
        for(Entity e : entities){
            Rectangle2D ent = new Rectangle(e.x, e.y, gP.tileSize, gP.tileSize)

        }
    }
}
