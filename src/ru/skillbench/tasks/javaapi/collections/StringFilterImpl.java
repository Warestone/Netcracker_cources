package ru.skillbench.tasks.javaapi.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class StringFilterImpl implements StringFilter {

    private static List<String> strings = new ArrayList<>();
    @Override
    public void add(String s) {
        if (s == null) {
            strings.add(null);
            return;
        }
        if (!strings.contains(s))strings.add(s.toLowerCase());
    }

    @Override
    public boolean remove(String s) {
        if (strings.contains(s))
        {
            strings.remove(s.toLowerCase());
            return true;
        }
        else return false;
    }

    @Override
    public void removeAll() {
        strings.clear();
    }

    @Override
    public Collection<String> getCollection() {
        return strings;
    }

    @Override
    public Iterator<String> getStringsContaining(String chars) {
        if (chars == null) return strings.iterator();
        if (chars.equals(""))return strings.iterator();
        List<String> founded = new ArrayList<>();
        for(String string:strings){
            if (string!=null)
            {
                if (string.contains(chars.toLowerCase()))founded.add(string);
            }
        }
        return founded.iterator();
    }

    @Override
    public Iterator<String> getStringsStartingWith(String begin) {
        if (begin==null) return strings.iterator();
        if (begin.equals(""))return strings.iterator();
        List<String> founded = new ArrayList<>();
        for(String string:strings){
            if (string!=null)
            {
                if (string.startsWith(begin.toLowerCase()))founded.add(string);
            }
        }
        return founded.iterator();
    }

    @Override
    public Iterator<String> getStringsByNumberFormat(String format) {
        if (format==null) return strings.iterator();
        if (format.equals(""))return strings.iterator();
        List<String> founded = new ArrayList<>();
        for(String string:strings){
            String replaceNum = string.replaceAll("[0-9]","#");
            if (replaceNum.equals(format))founded.add(string);
        }
        return founded.iterator();
    }

    @Override
    public Iterator<String> getStringsByPattern(String pattern) {
        if (pattern==null) return strings.iterator();
        if (pattern.equals(""))return strings.iterator();
        List<String> founded = new ArrayList<>();
        pattern = pattern.replaceAll("[*]", ".+");
        for(String string:strings){
            if (string!=null)
            {
                if (string.matches(pattern))founded.add(string);
            }
        }
        return founded.iterator();
    }
}
