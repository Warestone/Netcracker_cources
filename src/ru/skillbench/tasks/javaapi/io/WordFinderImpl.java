package ru.skillbench.tasks.javaapi.io;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class WordFinderImpl implements WordFinder {
    private String text=null;
    private List<String> foundedWords = new ArrayList<>();

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String input) {
        if (input==null)throw new IllegalArgumentException();
        else text=input;
    }

    @Override
    public void setInputStream(InputStream is) throws IOException {
        if(is == null)throw new IllegalArgumentException();
        try(BufferedInputStream bis = new BufferedInputStream(is); ByteArrayOutputStream buf = new ByteArrayOutputStream())
        {
            int result = bis.read();
            while(result != -1) {
                buf.write((byte)result);
                result = bis.read();
            }
            setText(buf.toString());
        }
    }

    @Override
    public void setFilePath(String filePath) throws IOException {
        if(filePath == null)throw new IllegalArgumentException();
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(filePath))); ByteArrayOutputStream buf = new ByteArrayOutputStream())
        {
            int result = bis.read();
            while(result != -1) {
                buf.write((byte)result);
                result = bis.read();
            }
            setText(buf.toString());
        }
    }

    @Override
    public void setResource(String resourceName) throws IOException {
        if(resourceName == null)throw new IllegalArgumentException();
        try(InputStream bis = new BufferedInputStream(WordFinderImpl.class.getResourceAsStream(resourceName));
            ByteArrayOutputStream buf = new ByteArrayOutputStream())
        {
            int result = bis.read();
            while(result != -1) {
                buf.write((byte)result);
                result = bis.read();
            }
            setText(buf.toString());
        }
    }

    @Override
    public Stream<String> findWordsStartWith(String begin) {
        if(text==null) throw new IllegalStateException();
        ArrayList<String> temp=new ArrayList<>();
        foundedWords.clear();
        String[] str=getText().split("[\\s\\n\\r\\t]+");
        for (int i = 0; i < str.length; i++) {
            if(str[i]!=null) {
                temp.add(str[i].toLowerCase());
            }else {
                temp.add(str[i]);
            }
        }
        if(begin!=null && !begin.equals("")) {
            String s = begin.toLowerCase();
            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i).startsWith(s)) {
                    foundedWords.add(temp.get(i));
                }
            }
        }
        else foundedWords = temp;
        return foundedWords.stream();
    }

    @Override
    public void writeWords(OutputStream os) throws IOException {
        if (foundedWords.isEmpty()) throw new IllegalStateException();
        Collections.sort(foundedWords);
        String outputString = String.join(" ", foundedWords);
        os.write(outputString.getBytes());
    }
}
