package ru.denfad.rover;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.denfad.rover.map.Command;
import ru.denfad.rover.ui.MyListAdapter;

public class ProgrammingActivity extends AppCompatActivity {

    private List<Command> commands = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.programming_activity);

        commands.add(new Command(200,90));
        commands.add(new Command(32454,80));
        commands.add(new Command(200,90));
        commands.add(new Command(32454,80));
        commands.add(new Command(200,90));
        commands.add(new Command(32454,80));
        commands.add(new Command(200,90));
        commands.add(new Command(32454,80));
        commands.add(new Command(200,90));
        commands.add(new Command(32454,80));
        commands.add(new Command(200,90));
        commands.add(new Command(32454,80));
        commands.add(new Command(200,90));
        commands.add(new Command(32454,80));
        commands.add(new Command(200,90));
        commands.add(new Command(32454,80));
        commands.add(new Command(200,90));
        commands.add(new Command(32454,80));


        RecyclerView list = findViewById(R.id.list);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        list.setLayoutManager(llm);
        MyListAdapter adapter = new MyListAdapter(commands);
        list.setAdapter(adapter);
    }


}
