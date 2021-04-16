package ru.denfad.rover;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.denfad.rover.map.Command;
import ru.denfad.rover.map.GlobalFields;
import ru.denfad.rover.map.MoveCommand;
import ru.denfad.rover.map.RotateCommand;
import ru.denfad.rover.ui.MyListAdapter;

public class ProgrammingActivity extends AppCompatActivity {

    private List<Command> commands = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.programming_activity);

        GlobalFields.getInstance().getCommands().clear();
        RecyclerView list = findViewById(R.id.list);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        list.setLayoutManager(llm);
        MyListAdapter adapter = new MyListAdapter(commands);
        list.setAdapter(adapter);

        EditText console = findViewById(R.id.command_line);

        Button addCommand = findViewById(R.id.add_command);
        addCommand.setOnClickListener(v -> {
            Command c = getCommand(console.getText().toString());
            if(c != null){
                commands.add(c);
            }
            adapter.notifyDataSetChanged();
            list.scrollToPosition(commands.size()-1);
        });

        Button start = findViewById(R.id.start);
        start.setOnClickListener(v -> {
            GlobalFields.getInstance().setCommands(commands);
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        });
    }

    private Command getCommand(String s){
        String[] commandS = s.split(" ");
        Command command = null;
        switch (commandS[0]){
            case "/forward":
                command = new MoveCommand(Integer.valueOf(commandS[1]));
                break;
            case "/back":
                command =new MoveCommand(-Integer.valueOf(commandS[1]));
                break;
            case "/left":
                command =new RotateCommand(-90);
                break;
            case "/right":
                command =new RotateCommand(90);
                break;
        }
        return command;
    }

}
