package org.example.Entities;

import java.awt.*;
import java.util.List;

public class NonPlayCharacter extends Entity{

    public List<String> script;

    public NonPlayCharacter(int x, int y, List<String> script){
        this.x = x;
        this.y = y;
        this.script = script;
        this.color = Color.CYAN;//see Entity abstract class
    }
}
