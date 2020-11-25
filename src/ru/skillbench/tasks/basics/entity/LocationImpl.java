package ru.skillbench.tasks.basics.entity;

import java.util.regex.Pattern;

public class LocationImpl implements Location {
    private String Name_place;
    private Type Type_place;
    private Location Parent;

    @Override
    public String getName() {
        return Name_place;
    }

    @Override
    public void setName(String name) {
        Name_place = name;
    }

    @Override
    public Type getType() {
        return Type_place;
    }

    @Override
    public void setType(Type type) {
        Type_place = type;
    }

    @Override
    public void setParent(Location parent) {
        Parent=parent;
    }

    @Override
    public String getParentName() {
        if (Parent==null) return "--";
        else return Parent.getName();
    }

    @Override
    public Location getTopLocation() {
        if (Parent==null) return this;
        else return Parent.getTopLocation();
    }

    @Override
    public boolean isCorrect() {
        if (Parent.getType().ordinal()<Type_place.ordinal()) return true;
        else return false;
    }

    @Override
    public String getAddress() {
        StringBuilder sb = new StringBuilder();
        String name = getName();
        if (name.endsWith(".") || name.substring(0,name.indexOf(' ')).contains(".")) sb.insert(0,name);
        else sb.insert(0,Type_place.getNameForAddress()+getName());
        Location superParent = this;
        while (superParent!=null)
        {
            name = superParent.getName();
            if (name.endsWith(".")) sb.insert(0,name+", ");
            else sb.insert(0,superParent.getType().getNameForAddress()+getName()+", ");
            Location checkL = superParent.getTopLocation();
        }
        return sb.toString();
    }

    public String toString()
    {
        return Name_place+" ("+Type_place.toString()+")";
    }
}
