package ru.denfad.rover;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.denfad.rover.map.Command;
import ru.denfad.rover.map.GlobalFields;
import ru.denfad.rover.map.MoveCommand;
import ru.denfad.rover.map.RotateCommand;
import ru.denfad.rover.ui.MyListAdapter;

import static android.view.KeyEvent.KEYCODE_ENTER;

public class ProgrammingActivity extends AppCompatActivity {

    private List<Command> commands = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.programming_activity);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ProgrammingActivity.this);

        GlobalFields.getInstance().getCommands().clear();
        RecyclerView list = findViewById(R.id.list);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        list.setLayoutManager(llm);
        MyListAdapter adapter = new MyListAdapter(commands);
        list.setAdapter(adapter);

        EditText console = findViewById(R.id.command_line);
        console.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KEYCODE_ENTER) {
                    Command c = getCommand(console.getText().toString());
                    if(c != null){
                        commands.add(c);
                    }
                    adapter.notifyDataSetChanged();
                    list.scrollToPosition(commands.size()-1);
                    console.setText(null);
                }
                return false;
            }
        });


        EditText name = findViewById(R.id.name_line);

        ImageButton start = findViewById(R.id.start);
        start.setOnClickListener(v -> {
            GlobalFields.getInstance().setCommands(commands);
//            GsonBuilder gsonBuilder = new GsonBuilder();
//            String s = gsonBuilder.create().toJson(commands);
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            List<String> programs = new ArrayList<>(Arrays.asList(gsonBuilder.create().fromJson(sharedPreferences.getString("programs", "[]"), String[].class)));
//            programs.add(name.getText().toString());
//            editor.putString(name.getText().toString(),s).apply();
//            s = gsonBuilder.create().toJson(programs);
//            editor.putString("programs",s).apply();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        });

        ImageButton back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MenuActivity.class));
            }
        });
    }

    private Command getCommand(String s){
        String[] commandS = s.split(" ");
        Command command = null;
        switch (commandS[0]){
            case "/forward":
                command = new MoveCommand(GlobalFields.getInstance().getCellHeight()*Integer.valueOf(commandS[1]));
                break;
            case "/back":
                command =new MoveCommand(-GlobalFields.getInstance().getCellHeight()*Integer.valueOf(commandS[1]));
                break;
            case "/left":
                command =new RotateCommand(-90);
                break;
            case "/right":
                command =new RotateCommand(90);
                break;
            case "/delete":
                commands.remove(commands.size()-1);
                break;
        }
        return command;
    }

}
