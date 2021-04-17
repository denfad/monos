package ru.denfad.rover.map;

import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class RotateCommand implements Command {
    private int dangle;

    public RotateCommand(int dangle){
        this.dangle = dangle;
    }

    @Override
    public Animation executeCommand(Rover rover) {
        Animation animation = new RotateAnimation(0,dangle,rover.getX()+rover.getWidth()/2,rover.getY()+rover.getHeight()/2);
        rover.setAngle(rover.getAngle()+dangle);
        return animation;
    }

    public int getDangle() {
        return dangle;
    }
}
