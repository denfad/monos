package ru.denfad.rover;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.denfad.rover.map.Command;
import ru.denfad.rover.map.GlobalFields;
import ru.denfad.rover.ui.MyListAdapter;

public class ProgrammingActivity extends AppCompatActivity {

    private List<Command> commands = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.programming_activity);

        GlobalFields.getInstance().getCommands().clear();
        RecyclerView list = findViewById(R.id.list);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        list.setLayoutManager(llm);
        MyListAdapter adapter = new MyListAdapter(commands);
        list.setAdapter(adapter);

        Button addCommand = findViewById(R.id.add_command);
        addCommand.setOnClickListener(v -> {
            commands.add(new Command(270,1));
            adapter.notifyDataSetChanged();
        });

        Button start = findViewById(R.id.start);
        start.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            GlobalFields.getInstance().setCommands(adapter.getCommands());
        });
    }


}
