package ru.denfad.rover.json;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import ru.denfad.rover.map.Command;
import ru.denfad.rover.map.MoveCommand;
import ru.denfad.rover.map.RotateCommand;

public class CommandDeserializer implements JsonDeserializer<Command> {

    @Override
    public Command deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jObject = (JsonObject) json;
        JsonElement typeObj = jObject.get("TypeName");

        if(typeObj!= null ){
            String typeVal = typeObj.getAsString();
            switch (typeVal){
                case "MoveCommand":
                    return context.deserialize(json, MoveCommand.class);
                case "RotateCommand":
                    return context.deserialize(json, RotateCommand.class);
            }
        }

        return null;
    }
}


