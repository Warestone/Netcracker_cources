package ru.skillbench.tasks.text.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternsImpl implements Patterns{
    @Override
    public Pattern getSQLIdentifierPattern() {
        return Pattern.compile("[a-zA-Z]+[A-z0-9]{0,29}");
    }

    @Override
    public Pattern getEmailPattern() {
        return Pattern.compile("[a-z0-9][0-9a-z_.-]{1,20}[a-z0-9]@([a-z0-9][.a-z0-9\\\\-]+[a-z0-9])+[.](ru|com|net|org)");
    }

    @Override
    public Pattern getHrefTagPattern() {
        return Pattern.compile("<[ \\t\\n\\r]?[Aa][ \\t\\n\\r]?.+?[ \\t\\n\\r]?[hrefHREF][ \\t\\n\\r]?.+?=[ \\t\\n\\r]?.+?(\".*?\"|[^\\s>]+)?[ \\t\\n\\r]?\\\\?>");
    }

    @Override
    public List<String> findAll(String input, Pattern pattern) {
        List<String>founded = new ArrayList<>();
        Matcher matcher = pattern.matcher(input);
        while (matcher.find())founded.add(matcher.group());
        return founded;
    }

    @Override
    public int countMatches(String input, String regex) {
        input = input.toLowerCase();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        int counter = 0;
        while (matcher.find())counter++;
        return counter;
    }
}
