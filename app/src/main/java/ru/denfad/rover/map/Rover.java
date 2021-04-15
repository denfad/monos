package ru.denfad.rover.map;

import android.content.Context;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Rover extends androidx.appcompat.widget.AppCompatImageView {
    private int x;
    private int y;

    public Rover(int x, int y, Context context, int width, int height){
        super(context);
        this.x = x;
        super.setX(this.x);
        this.y= y;
        super.setY(this.y);
        this.setLayoutParams(new ViewGroup.LayoutParams(width,height));
    }

    public void move(int angle,int distance){
        if(angle%90 == 0){
            int dx = x + (int) (distance * Math.cos(Math.toRadians((double)angle)));
            int dy = y + (int) (distance * Math.sin(Math.toRadians((double)angle)));
            TranslateAnimation animation = new TranslateAnimation(x, dx, y, dy);
            animation.setDuration(TimeUnit.SECONDS.toMillis(2L));
            animation.setFillAfter(true);
            this.startAnimation(animation);
            x = dx;
            y = dy;
        }

    }

    public void executeCommand(Command command){
        move(command.getAngle(), command.getDistance());
    }

    public void executeCommands(List<Command> commands){
        AnimationSet set = new AnimationSet(true);
        set.setFillAfter(true);
        int start = 0;
        for(Command command: commands){
            int dx = x + (int) (command.getDistance() * Math.cos(Math.toRadians((double)command.getAngle())));
            int dy = y + (int) (command.getDistance() * Math.sin(Math.toRadians((double)command.getAngle())));
            TranslateAnimation animation = new TranslateAnimation(x, dx, y, dy);
            animation.setDuration(1000);
            animation.setStartOffset(start);
            start = start + 1000;
            animation.setFillAfter(true);
            set.addAnimation(animation);
            x = dx;
            y = dy;
        }

        this.startAnimation(set);

    }


}
