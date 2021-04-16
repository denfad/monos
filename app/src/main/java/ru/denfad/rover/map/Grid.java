package ru.denfad.rover.map;

import java.util.List;

public class Grid {
    private final int height =70,width = 130;
    private Rover rover;

    public Grid(Rover rover){
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
