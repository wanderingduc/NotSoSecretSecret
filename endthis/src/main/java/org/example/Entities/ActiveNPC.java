package org.example.Entities;

import java.util.List;

public class ActiveNPC extends NonPlayCharacter{

    int frameC=0;
    int pathPointer;
    boolean pathDir;
    int xPath[];
    int yPath[];


    public ActiveNPC(int x, int y, List<String> script) {
        super(x, y, script);

        setDefaults();
    }


    public void setDefaults(){
        this.pathDir = true;
        this.pathPointer = 0;
    }

    public void update(){

        frameC++;
        x = xPath[pathPointer];
        y = yPath[pathPointer];
        if(frameC%12==0){
            if(pathDir){
                pathPointer++;
            }else{
                pathPointer--;
            }
            if(pathPointer>=xPath.length-1 && pathDir){
                pathPointer--;
                pathDir = false;
            }
            if(pathPointer<=0 && !pathDir){
                pathPointer++;
                pathDir = true;
            }
            frameC=0;
        }

    }
}
