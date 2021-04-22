package ru.denfad.rover.map;

import java.util.ArrayList;
import java.util.List;

public class GlobalFields {
    private static GlobalFields instance;
    private int cellHeight=113, cellWidth = 113;
    private List<Command> commands;
    private List<MapObject> mapObjects;
    private GlobalFields(){
        commands = new ArrayList<>();
        mapObjects = new ArrayList<>();
    }

    public static GlobalFields getInstance(){
        if(instance == null){
            instance = new GlobalFields();
        }
        return instance;
    }

    public static void setInstance(GlobalFields instance) {
        GlobalFields.instance = instance;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    public List<MapObject> getMapObjects() {
        return mapObjects;
    }

    public void setMapObjects(List<MapObject> mapObjects) {
        this.mapObjects = mapObjects;
    }

    public int getCellHeight() {
        return cellHeight;
    }

    public void setCellHeight(int cellHeight) {
        this.cellHeight = cellHeight;
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public void setCellWidth(int cellWidth) {
        this.cellWidth = cellWidth;
    }

    public void clearCommands(){
        if(commands != null){
            commands.clear();
        }

    }
}
