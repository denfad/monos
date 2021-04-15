package ru.denfad.rover.map;

import java.util.List;

public class Grid {
    private int height,width;
    private Rover rover;

    public Grid(int width, int height, Rover rover){
        this.height = height;
        this.width =  width;
        this.rover = rover;
    }

    public Rover executeCommand(Command command){
        rover.executeCommand(command);
        return rover;
    }

    public Rover executeCommands(List<Command> commands){
        rover.executeCommands(commands);
        return rover;
    }

    public Rover getRover(){
        return rover;
    }
}
