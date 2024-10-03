package org.example.Entities;

import org.example.GamePanel;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityManager {

    public List<Entity> entities;
    public Map<String, Integer> entityMap;
    public String entityFile;
    public GamePanel gP;

    public EntityManager(GamePanel gP){
        this.gP = gP;
        this.entityFile = "testEntity1.txt";// Format of entityfile {x,y;type;script1~script2~script3~...}

        loadEntities();
    }

    public void setEntityFile(String entityPath){
        entityFile = entityPath;
        loadEntities();
    }

    public void loadEntities(){
        entities = new ArrayList<>();
        entityMap = new HashMap<>();
        try{
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream iS = classLoader.getResourceAsStream(entityFile);
            BufferedReader bR = new BufferedReader(new InputStreamReader(iS));
            int i = 0;

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
                if(data[2]=="e"){
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
                entityMap.put(String.valueOf(x)+String.valueOf(y), i);
            }
            bR.close();

//            classLoader = Thread.currentThread().getContextClassLoader();
//            iS = classLoader.getResourceAsStream(activePath);
//            bR = new BufferedReader(new InputStreamReader(iS));
//
//            while(true){
//                String line = bR.readLine();
//                String data[] = line.split(";");
//                String x[] = data[0].split(",");
//                String y[] = data[1].split(",");
//            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void drawEntities(Graphics2D g){
        for(Entity e : entities){
            Rectangle2D ent = new Rectangle(e.x*gP.tileSize, e.y*gP.tileSize, gP.tileSize, gP.tileSize);
            g.setColor(e.color);
            g.draw(ent);
            g.fill(ent);
        }
    }
}
