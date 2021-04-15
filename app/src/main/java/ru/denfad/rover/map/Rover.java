package ru.denfad.rover.map;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

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
            x = x + (int) (distance * Math.cos(Math.toRadians((double)angle)));
            super.setX(this.x);
            y = y + (int) (distance * Math.sin(Math.toRadians((double)angle)));
            super.setY(this.y);
        }

    }


    public void executeCommand(Command command){
        move(command.getAngle(), command.getDistance());
    }


}
