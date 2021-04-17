package ru.denfad.rover.map;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import java.util.concurrent.TimeUnit;

public class MoveCommand implements Command {

    private int distance;

    public MoveCommand(int distance){
        this.distance = distance;
    }

    @Override
    public Animation executeCommand(Rover rover) {
        Animation animation = null;
        if(rover.getAngle()%90 == 0){
            int x = (int)rover.getX();
            int y = (int)rover.getY();
            int dx = (int) (distance * Math.cos(Math.toRadians((double)rover.getAngle())));
            int dy = (int) (distance * Math.sin(Math.toRadians((double)rover.getAngle())));
            animation = new TranslateAnimation(0,dx, 0, dy);
            rover.setX(x + dx);
            rover.setY(y + dy);
        }
        return animation;
    }

    public String getAngleS(int angle) {
        switch (angle){
            case 0:
                return "Право";
            case 90:
                return "Вниз";
            case 180:
                return "Влево";
            case 270:
                return "Вверх";
            default:
                return "Вправо";
        }

    }

    public int getDistance() {
        return distance;
    }
}
