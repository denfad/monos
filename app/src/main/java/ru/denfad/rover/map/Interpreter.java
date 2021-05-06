package ru.denfad.rover.map;

import java.util.List;
import java.util.Stack;

public class Interpreter {


    public static List<Command> interpreter(List<Command> commands){
        Stack<StartCycleCommand> stack = new Stack<>();

        while (check(commands)) {
            int start = 0, end = 0,count  = 0;
            for(int i = 0; i<commands.size(); i++){
                if(commands.get(i) instanceof StartCycleCommand){
                    if(start == 0){
                        start = i;
                        count = ((StartCycleCommand)commands.get(i)).getCount();
                    }
                    stack.push((StartCycleCommand) commands.get(i));
                }
                if(commands.get(i) instanceof StopCycleCommand){
                    stack.pop();
                    if(stack.empty()){
                        end = i;
                        break;
                    }
                }
            }
            commands.remove(start);
            commands.remove(end);
            List<Command> c = commands.subList(start+1,end-1);
            for(int i = 0; i<count;i++){
                commands.addAll(start,c);
                start = start+c.size();
            }
        }
        return commands;
    }

    private static boolean check(List<Command> commands){
        boolean f = false;
        for(Command c:commands){
            if(c instanceof StartCycleCommand){
                f= true;
            }
        }
        return f;
    }
}
