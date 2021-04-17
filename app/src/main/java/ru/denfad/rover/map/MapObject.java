package ru.denfad.rover.map;

import android.graphics.Bitmap;

public class MapObject {
    int x,y;
    Bitmap bitmap;
    int width,height;

    public MapObject(int x, int y, Bitmap bitmap){
        this.x = x;
        this.y = y;
        this.bitmap = bitmap;
        width = bitmap.getWidth();
        height = bitmap.getHeight();
    }
}
