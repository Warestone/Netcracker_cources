package ru.skillbench.tasks.text;

import java.io.PrintStream;
import java.text.Collator;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounterImpl implements WordCounter {
    private String text_input = null;
    @Override
    public void setText(String text) {
        text_input = text;
    }

    @Override
    public String getText() {
        return text_input;          // check null
    }

    @Override
    public Map<String, Long> getWordCounts() {
        if (text_input==null) throw new IllegalStateException();
        Map<String, Long>words= new HashMap<>();
        List<String> all_text = new ArrayList<>();
        Pattern pattern = Pattern.compile("[ёА-я<>!№;#$%^&*A-z0-9,.()\":\\-_'\\/]+");
        Matcher matcher = pattern.matcher(text_input.toLowerCase().replaceAll("[.,;:-]"," "));
        while (matcher.find())
        {
            if (matcher.group().charAt(0)!='<' && matcher.group().charAt(matcher.group().length()-1)!='>')all_text.add(matcher.group());
        }
        for (String word:all_text) {
            if (!words.containsKey(word))
            {
                Long counter = 0L;
                for (String word_2:all_text)if (word_2.equals(word)) counter++;
                words.put(word,counter);
            }
        }
        return words;
    }

    @Override
    public List<Map.Entry<String, Long>> getWordCountsSorted() {
        Map<String, Long>words = getWordCounts();
        Comparator<Map.Entry<String, Long>> comparator = new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> t1, Map.Entry<String, Long> t2) {
                if (t1.getValue()<t2.getValue()) return 1;
                if (t1.getValue()>t2.getValue()) return -1;
                if (t1.getValue()==t2.getValue())
                {
                    Collator collator = Collator.getInstance();
                    return  collator.compare(t1.getKey(),t2.getKey());
                }
                return 0;
            }
        };
        return sort(words,comparator);
    }

    @Override
    public <K extends Comparable<K>, V extends Comparable<V>> List<Map.Entry<K, V>> sort(Map<K, V> map, Comparator<Map.Entry<K, V>> comparator) {
        List<Map.Entry<K, V>> words_s = new LinkedList<>();
        words_s.addAll(map.entrySet());
        boolean sorted = false;
        List<Map.Entry<K,V>> tmp = new LinkedList<>();
        while (!sorted)
        {
            sorted = true;
            for (int i = 0; i < words_s.size() - 1; i++) {
                int result_compare = comparator.compare(words_s.get(i),words_s.get(i+1));
                if (result_compare > 0) {
                    tmp.add(words_s.get(i));
                    words_s.set(i,words_s.get(i+1));
                    words_s.set(i+1,tmp.get(0));
                    tmp.clear();
                    sorted = false;
                }
            }
        }
        return words_s;
    }

    @Override
    public <K, V> void print(List<Map.Entry<K, V>> entries, PrintStream ps) {
        for (Map.Entry<K,V> value:entries) {
            ps.println(value.getKey()+" "+value.getValue());
        }
    }
}
