package ru.denfad.rover.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.denfad.rover.R;
import ru.denfad.rover.map.Command;
import ru.denfad.rover.map.GlobalFields;
import ru.denfad.rover.map.MoveCommand;
import ru.denfad.rover.map.RotateCommand;

public class MyListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Command> commands;

    public MyListAdapter(List<Command> commands) {
        this.commands = commands;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case 1:
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.programming_item_rotate, parent, false);
                return new RotateViewHolder(v);
            case 2:
                View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.programming_item_move, parent, false);
                return  new MoveViewHolder(V);
            default:
                return null;

        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
       switch (holder.getItemViewType()){
           case 1:
               RotateViewHolder c1 = (RotateViewHolder) holder;
               if(((RotateCommand)commands.get(position)).getDangle() == 90){
                   c1.angle.setText("RIGHT");
               }
               else{
                   c1.angle.setText("LEFT");
               }
               break;
           case 2:
               MoveViewHolder c2 = (MoveViewHolder) holder;
               if(((MoveCommand)commands.get(position)).getDistance() < 0){
                   c2.angle.setText("BACK");
                   c2.distance.setText(String.valueOf(-((MoveCommand)commands.get(position)).getDistance()/ GlobalFields.getInstance().getCellHeight()));
               }
               else{
                   c2.angle.setText("FORWARD");
                   c2.distance.setText(String.valueOf(((MoveCommand)commands.get(position)).getDistance()/ GlobalFields.getInstance().getCellHeight()));
               }
       }
    }

    @Override
    public int getItemViewType(int position) {
        if(commands.get(position) instanceof RotateCommand){
            return 1;
        }
        else{
            return 2;
        }
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

    public static class RotateViewHolder extends RecyclerView.ViewHolder {

        TextView angle;

        RotateViewHolder(View itemView) {
            super(itemView);
            angle = itemView.findViewById(R.id.angle);
        }
    }

    public static class MoveViewHolder extends RecyclerView.ViewHolder {

        TextView distance;
        TextView angle;

        MoveViewHolder(View itemView) {
            super(itemView);
            angle = itemView.findViewById(R.id.angle);
            distance = itemView.findViewById(R.id.distance);
        }
    }

}
