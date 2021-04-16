package ru.denfad.rover.map;

import android.content.Context;
import android.view.ViewGroup;
import android.view.animation.Animation;
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
    private int angle = 270;

    public Rover(int x, int y, Context context, int width, int height){
        super(context);
        this.x = x;
        super.setX(this.x);
        this.y= y;
        super.setY(this.y);
        this.setLayoutParams(new ViewGroup.LayoutParams(width,height));
    }


    public void executeCommand(Command command){
        Animation anim = command.executeCommand(this);
        anim.setFillAfter(true);
        anim.setDuration(1000);
        this.startAnimation(anim);
    }

    public void executeCommands(List<Command> commands){
        AnimationSet set = new AnimationSet(true);
        set.setFillAfter(true);
        int start = 0;
        for(Command command: commands){
            Animation animation = command.executeCommand(this);
            animation.setDuration(1000);
            animation.setStartOffset(start);
            start = start + 1000;
            animation.setFillAfter(true);
            set.addAnimation(animation);
        }

        this.startAnimation(set);

    }


    public int getXR() {
        return this.x;
    }


    public int getYR() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }
}
