package ru.denfad.rover.map;

import java.util.ArrayList;
import java.util.List;

public class GlobalFields {
    private static GlobalFields instance;
    private List<Command> commands;
    private GlobalFields(){
        commands = new ArrayList<>();
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
}
