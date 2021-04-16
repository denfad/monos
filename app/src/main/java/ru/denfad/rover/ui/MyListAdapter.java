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

    public MyListAdapter(List<Command> commands) {
        this.commands = commands;
    }

    @NonNull
    @Override
    public CommandViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.programming_item, parent, false);
        CommandViewHolder cvh = new CommandViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CommandViewHolder holder, int position) {
       // holder.angle.setText(commands.get(position).getAngleS());
        //holder.distance.setText(String.valueOf(commands.get(position).getDistance()));
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

    public static class CommandViewHolder extends RecyclerView.ViewHolder {

        TextView distance;
        TextView angle;

        CommandViewHolder(View itemView) {
            super(itemView);
            angle = itemView.findViewById(R.id.angle);
            distance = itemView.findViewById(R.id.distance);
        }
    }

}
