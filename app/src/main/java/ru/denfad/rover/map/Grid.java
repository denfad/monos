package ru.denfad.rover.map;

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

    public Rover getRover(){
        return rover;
    }
}
