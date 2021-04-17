package ru.denfad.rover.map;

import android.content.Context;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Rover extends androidx.appcompat.widget.AppCompatImageView {
    private int x;
    private int y;
    private int angle = 270;

    public Rover(int x, int y, Context context,int width,int height){
        super(context);
        this.x = x;
        super.setX(this.x);
        this.y= y;
        super.setY(this.y);
        this.setLayoutParams(new ViewGroup.LayoutParams(width,height));
    }


    public void executeCommand(Command command){
        Animation anim = command.executeCommand(this);
        anim.setDuration(1000);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Rover.super.setX(x);
                Rover.super.setY(y);
                Rover.super.setRotation(angle - 270);
                Rover.this.setAnimation(null);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        this.startAnimation(anim);
    }

    public void executeCommands(List<Command> commands){
        animate(commands,0);
    }

    private void animate(List<Command> commands, int pos){
        Animation animation = commands.get(pos).executeCommand(this);
        animation.setDuration(1000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Rover.super.setX(x);
                Rover.super.setY(y);
                Rover.super.setRotation(angle - 270);
                if(pos+1<commands.size()){
                    animate(commands,pos+1);
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        this.startAnimation(animation);
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
