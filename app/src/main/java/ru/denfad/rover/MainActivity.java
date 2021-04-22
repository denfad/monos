package ru.denfad.rover;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.denfad.rover.json.CommandDeserializer;
import ru.denfad.rover.map.Command;
import ru.denfad.rover.map.GlobalFields;
import ru.denfad.rover.map.Grid;
import ru.denfad.rover.map.MoveCommand;
import ru.denfad.rover.map.RotateCommand;
import ru.denfad.rover.map.Rover;
import ru.denfad.rover.ui.NoDefaultSpinner;

public class MainActivity extends AppCompatActivity {

    private Grid map;
    private ImageView roverImage;
    private int width,height;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        //настройка карты
        map = findViewById(R.id.map);

        //элементы упавления
        ImageButton left = findViewById(R.id.left);
        ImageButton right = findViewById(R.id.right);
        ImageButton up = findViewById(R.id.up);
        ImageButton bottom = findViewById(R.id.bottom);

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
                map.executeCommand(new MoveCommand(GlobalFields.getInstance().getCellHeight()));

            }
        });

        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //вниз 90
                map.executeCommand(new MoveCommand(-GlobalFields.getInstance().getCellHeight()));

            }
        });

        ImageButton codding = findViewById(R.id.codding);
        codding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ProgrammingActivity.class));
            }
        });

        ImageButton regenerate = findViewById(R.id.regenerate);
        regenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.regenerateObjects();
            }
        });

        ImageButton back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MenuActivity.class));
            }
        });

        //создание сущности ровера
        roverImage = new Rover(0,0,getApplicationContext(),map.cellWidth,map.cellHeight);
        roverImage.setImageResource(R.drawable.rover);
        map.addView(roverImage);
        map.setRover((Rover)roverImage);


        if(!GlobalFields.getInstance().getCommands().isEmpty()){
           map.executeCommands(GlobalFields.getInstance().getCommands());
        }

        Spinner spin = findViewById(R.id.spinner);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Command.class, new CommandDeserializer());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_item,gsonBuilder.create().fromJson(sharedPreferences.getString("programs","[]"),String[].class));
        spin.setAdapter(adapter);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        map.backRover();
                        String[] programs = gsonBuilder.create().fromJson(sharedPreferences.getString("programs", "[]"), String[].class);
                        System.out.println(programs[position]);
                        String c = sharedPreferences.getString(programs[position], "[]");
                        System.out.println(c);
                        List<Command> commands = new ArrayList<>(Arrays.asList(gsonBuilder.create().fromJson(c, Command[].class)));
                        System.out.println(Arrays.toString(commands.toArray()));
                        GlobalFields.getInstance().setCommands(commands);
                        map.executeCommands(GlobalFields.getInstance().getCommands());
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void updateGameMap(){
        roverImage.setX(map.getRover().getX());
        roverImage.setY(map.getRover().getY());
    }
}