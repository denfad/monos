package ru.denfad.rover.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.AbsoluteLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.denfad.rover.R;

public class Grid extends AbsoluteLayout {
    private final int height =9,width = 16;
    public int roverHeight,roverWidth;
    private Rover rover;
    private List<MapObject> objects = new ArrayList<>();


    public Grid(Context context, AttributeSet attrs){
        super(context,attrs);
        roverHeight = 113;
        roverWidth = 113;
        generateObjects();

    }

    public Grid(Context context){
        super(context);
        roverHeight = 113;
        roverWidth =113;
        generateObjects();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(getResources().getColor(R.color.white));
        paint.setStrokeWidth(3);

        int dy = super.getHeight() / height;
        roverHeight = dy;
        int dx = super.getWidth() / width;
        roverWidth = dx;
        for(int i = 0; i<height;i++){
            canvas.drawLine(0,i*dy,super.getWidth(),i*dy,paint);
        }
        for(int i = 0; i<width;i++){
            canvas.drawLine(i*dx,0,i*dx,super.getHeight(),paint);
        }

        drawObjects(canvas);
    }

    public void setRover(Rover rover){
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

    public void generateObjects(){

        objects.clear();
        Random random = new Random();
        for(int i=0; i<width;i++){
            int n = random.nextInt(100);
            if(n<7){
                Bitmap b = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.stone);
                b = Bitmap.createScaledBitmap(b,roverWidth,roverHeight,false);
                objects.add(new MapObject(i*roverWidth,random.nextInt(height)*roverHeight,b));

            }
            if(n>=7 && n<14){
                Bitmap b = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.stone);
                b = Bitmap.createScaledBitmap(b,roverWidth*3,roverHeight*3,false);
                objects.add(new MapObject(i*roverWidth,random.nextInt(height)*roverHeight,b));

            }
        }
    }

    public void drawObjects(Canvas canvas){
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        for(MapObject ob: objects){
            canvas.drawBitmap(ob.bitmap,ob.x,ob.y,p);
        }

    }

    public void regenerateObjects(){
        generateObjects();
        invalidate();
    }

}
