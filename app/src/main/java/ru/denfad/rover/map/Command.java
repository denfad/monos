package ru.denfad.rover.map;

public class Command {
    private int distance;
    private int angle;

    public Command(int distance, int angle){
        this.distance = distance;
        this.angle = angle;
    }

    public int getDistance() {
        return distance;
    }

    public int getAngle() {
        return angle;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }
}
