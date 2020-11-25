package tricky_tasks_2;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class GuessWord {
    private static int trials = 0;
    private static boolean passed = false;

    public static void main(String [] args) throws IOException {
        String [] words = getWords().split(" ");
        System.out.println("Please, enter your name: ");
        Scanner scanner = new Scanner(System.in);
        String playerName = scanner.nextLine();
        if (playerName.equals(""))playerName="Player";
        Random random = new Random();
        int wordIndex = random.nextInt(words.length);
        System.out.println("Hello, "+playerName+"! your word has "+words[wordIndex].length()+" letters!");
        startPlay(words[wordIndex], new String[words[wordIndex].length()]);
        if (passed) System.out.println("\n\nCongratulations, "+playerName+"! You pass with "+trials+" try!");
        else System.out.println("\n\nDon't worry, "+playerName+"! Be lucky another time!");
        if (!playerName.equals("Player"))writeToFile(words[wordIndex], playerName);
    }

    private static String getWords(){
        String words="";
        try {
            File currentDirectory = new File(new File(".").getAbsolutePath());
            String file = currentDirectory.getCanonicalPath()+"\\src\\tricky_tasks_2\\Words.txt";
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            words = reader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        if (words.equals(""))
        {
            System.out.println("No words in file.");
            System.exit(0);
        }
        return words;
    }

    private static void startPlay(String word, String []word_m){                               // exit in recursive method if: 1 - full word has been guess, 2 - all letters has been guess, 3 - input is 'exit' value
        if (word.equals(String.join(",", word_m).replaceAll(",","")))  //check current word with key
        {
            passed = true;
            return;
        }
        System.out.println("Trial "+trials+": ");
        for (String letter:word_m) {                                                           // print letters in word
            if (letter == null) System.out.print("_ ");
            else System.out.print(letter+" ");
        }
        System.out.println("\nKey in one character or your guess word (Enter 'exit' for game over): ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        if (input.equals("exit"))return;
        if (input.equals("")) startPlay(word,word_m);
        trials++;
        if (input.length()>1)                                                                   // if input is word
        {
            if (input.equals(word))
            {
                passed=true;
                return;
            }
            else startPlay(word, word_m);
        }
        for (int i = 0; i < word.length(); i++)                                                 // if input is char
        {
            if (word.toCharArray()[i]==input.charAt(0))
            {
                word_m[i]=Character.toString(word.toCharArray()[i]);
            }
        }
        startPlay(word,word_m);
    }

    private static void writeToFile(String word, String name) throws IOException {
        File currentDirectory = new File(new File(".").getAbsolutePath());
        String file = currentDirectory.getCanonicalPath()+"\\src\\tricky_tasks_2\\Players.txt";
        try(FileWriter writer = new FileWriter(file, true))
        {
            String text = "\n----------------------\nPlayer: "+name+"\nWord: "+word+"\nTrials: "+trials+"\nPassed: "+passed;
            writer.write(text);
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
