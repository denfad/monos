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

    private Grid map;
    private ImageView roverImage;
    private int width,height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //настройка карты
        map = findViewById(R.id.map);

        //создание сущности ровера
        roverImage = new Rover(0,0,getApplicationContext(),map.roverWidth,map.roverHeight);
        roverImage.setImageResource(R.drawable.rover_image);
        map.addView(roverImage);
        map.setRover((Rover)roverImage);

        //элементы упавления
        Button left = findViewById(R.id.left);
        Button right = findViewById(R.id.right);
        Button up = findViewById(R.id.up);
        Button bottom = findViewById(R.id.bottom);

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //лево 180
                map.executeCommand(new RotateCommand(-90));

            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //право 0
                map.executeCommand(new RotateCommand(90));

            }
        });

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //вверх 270
                map.executeCommand(new MoveCommand(113));

            }
        });

        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //вниз 90
                map.executeCommand(new MoveCommand(-113));

            }
        });

        Button codding = findViewById(R.id.codding);
        codding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ProgrammingActivity.class));
            }
        });

        Button regenerate = findViewById(R.id.regenerate);
        regenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.regenerateObjects();
            }
        });
        if(!GlobalFields.getInstance().getCommands().isEmpty()){
           map.executeCommands(GlobalFields.getInstance().getCommands());
        }
    }

    public void updateGameMap(){
        roverImage.setX(map.getRover().getX());
        roverImage.setY(map.getRover().getY());
    }
}