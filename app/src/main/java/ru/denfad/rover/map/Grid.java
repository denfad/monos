package ru.denfad.rover.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.denfad.rover.R;

public class Grid extends AbsoluteLayout {
    private final int height =9,width = 16;
    public int cellHeight = GlobalFields.getInstance().getCellHeight(),cellWidth = GlobalFields.getInstance().getCellHeight();
    private Rover rover;
    private List<MapObject> objects = new ArrayList<>();
    private int baseRoverX,baseRoverY;


    public Grid(Context context, AttributeSet attrs){
        super(context,attrs);

    }

    public Grid(Context context){
        super(context);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(getResources().getColor(R.color.white));
        paint.setStrokeWidth(3);

        int dy = super.getHeight() / height;
        cellHeight = dy;
        GlobalFields.getInstance().setCellHeight(dy);
        int dx = super.getWidth() / width;
        cellWidth = dx;
        GlobalFields.getInstance().setCellWidth(dx);
        for(int i = 0; i<height;i++){
            canvas.drawLine(0,i*dy,super.getWidth(),i*dy,paint);
        }
        for(int i = 0; i<width;i++){
            canvas.drawLine(i*dx,0,i*dx,super.getHeight(),paint);
        }

        generateObjects(false);
        drawObjects(canvas);
    }

    public void setRover(Rover rover){
        baseRoverX = (int)rover.getX();
        baseRoverY = (int)rover.getY();
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

    public void backRover(){
        rover.setX(baseRoverX);
        rover.setY(baseRoverY);
        rover.setAngle(270);
        ((ImageView)rover).setX(baseRoverX);
        ((ImageView)rover).setY(baseRoverY);
        ((ImageView)rover).setRotation(0);
    }

    public Rover getRover(){
        return rover;
    }

    public void generateObjects(boolean isButton){

        if(GlobalFields.getInstance().getMapObjects().isEmpty() || isButton ) {
            objects.clear();
            Random random = new Random();
            for (int i = 0; i < width; i++) {
                int n = random.nextInt(100);
                if (n < 7) {
                    Bitmap b = Bitmap.createBitmap(cellWidth, cellHeight, Bitmap.Config.ARGB_8888);
                    b.eraseColor(getContext().getColor(R.color.stone));
                    int x = i * cellWidth;
                    int y  =random.nextInt(height) * cellHeight;
                    if(checkObject(x)) objects.add(new MapObject(x, y, b));

                }
                if (n >= 7 && n < 14) {
                    Bitmap b = Bitmap.createBitmap(cellWidth*2, cellHeight*2, Bitmap.Config.ARGB_8888);
                    b.eraseColor(getContext().getColor(R.color.big_stone));
                    int x = i * cellWidth;
                    int y  =random.nextInt(height) * cellHeight;
                    if(checkObject(x)) objects.add(new MapObject(x, y, b));

                }

                if (n >= 14 && n < 20) {
                    Bitmap b = Bitmap.createBitmap(cellWidth*4, cellHeight*5, Bitmap.Config.ARGB_8888);
                    b.eraseColor(getContext().getColor(R.color.plato));
                    int x = i * cellWidth;
                    int y  =random.nextInt(height) * cellHeight;
                    if(checkObject(x)) objects.add(new MapObject(x, y, b));

                }
            }
            GlobalFields.getInstance().setMapObjects(objects);
        }
        else{
            objects = GlobalFields.getInstance().getMapObjects();
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
        generateObjects(true);
        invalidate();
    }

    public boolean checkObject(int x){
        boolean b = true;
        for(MapObject ob: objects){
          if(ob.x + ob.width > x) {
              b = false;
              break;
          }
        }
        return b;
    }

}
