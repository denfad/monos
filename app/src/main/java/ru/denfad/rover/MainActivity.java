package ru.denfad.rover;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;

import ru.denfad.rover.map.Command;
import ru.denfad.rover.map.GlobalFields;
import ru.denfad.rover.map.Grid;
import ru.denfad.rover.map.MoveCommand;
import ru.denfad.rover.map.RotateCommand;
import ru.denfad.rover.map.Rover;

public class MainActivity extends AppCompatActivity {

    private AbsoluteLayout map;
    private ImageView roverImage;
    private Grid grid;
    private int width,height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //настройка карты
        map = findViewById(R.id.map);
        map.post(new Runnable() {
            @Override
            public void run() {
                width = map.getMeasuredWidth();
                height = map.getMeasuredHeight();
            }
        });

        //создание сущности ровера
        roverImage = new Rover(width/2,height/2,getApplicationContext(),200,200);
        roverImage.setImageResource(R.drawable.rover_image);

        map.addView(roverImage);
        this.grid = new Grid((Rover)roverImage);

        //элементы упавления
        Button left = findViewById(R.id.left);
        Button right = findViewById(R.id.right);
        Button up = findViewById(R.id.up);
        Button bottom = findViewById(R.id.bottom);

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //лево 180
                grid.executeCommand(new RotateCommand(-90));

            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //право 0
                grid.executeCommand(new RotateCommand(90));

            }
        });

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //вверх 270
                grid.executeCommand(new MoveCommand(100));

            }
        });

        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //вниз 90
                grid.executeCommand(new MoveCommand(-100));

            }
        });

        Button codding = findViewById(R.id.codding);
        codding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ProgrammingActivity.class));
            }
        });

        if(!GlobalFields.getInstance().getCommands().isEmpty()){
           grid.executeCommands(GlobalFields.getInstance().getCommands());
        }
    }

    public void updateGameMap(){
        roverImage.setX(grid.getRover().getX());
        roverImage.setY(grid.getRover().getY());
    }
}