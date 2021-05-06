package ru.denfad.rover.map;

import android.view.animation.Animation;

public class StartCycleCommand implements Command {
    private int count;

    public StartCycleCommand(int count){
        this.count = count;
    }
    @Override
    public Animation executeCommand(Rover rover) {
        return null;
    }

    public int getCount() {
        return count;
    }


}
