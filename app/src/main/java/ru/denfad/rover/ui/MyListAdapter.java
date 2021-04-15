package ru.denfad.rover.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.denfad.rover.R;
import ru.denfad.rover.map.Command;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.CommandViewHolder> {

    private List<Command> commands;
    public MyListAdapter(List<Command> commands){
        this.commands = commands;
    }
    @NonNull
    @Override
    public CommandViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.programming_item,parent,false);
        CommandViewHolder cvh = new CommandViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CommandViewHolder holder, int position) {
        commands.set(position,holder.command);
    }

    @Override
    public int getItemCount() {
        return commands.size();
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    public static  class CommandViewHolder extends RecyclerView.ViewHolder{
        Button changeAngle;
        Button less;
        Button more;
        TextView distance;
        Command command;
        int[] angles = {0,90,180,270};
        String[] strAngles = {"вправо","вниз","влево","вверх"};
        int i = 0;
        CommandViewHolder(View itemView){
            super(itemView);
            changeAngle = itemView.findViewById(R.id.change_angle);
            less = itemView.findViewById(R.id.less);
            more = itemView.findViewById(R.id.more);
            distance = itemView.findViewById(R.id.distance);
            distance.setText("1");
            command = new Command(1,270);

            less.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    command.setDistance(command.getDistance() - 50);
                    distance.setText(String.valueOf(command.getDistance()));
                }
            });

            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    command.setDistance(command.getDistance() +50);
                    distance.setText(String.valueOf(command.getDistance()));
                }
            });

            changeAngle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(i!=4){
                        command.setAngle(angles[i]);
                        changeAngle.setText(strAngles[i]);
                        i++;
                    }
                    else{
                        i=0;
                        changeAngle.setText(strAngles[i]);
                        command.setAngle(angles[i]);
                    }
                }
            });
        }
    }

}
