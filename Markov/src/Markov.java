/*
Title: Markov.java
Date: 02/19/2021
 */
import java.io.File;
import java.util.Scanner;
import java.util.*;
import java.io.*;

public class Markov {

    private static final String PUNCTUATION = "__$";
    private static final String PUNCTUATION_MARKS = ".";

    private HashMap<String, ArrayList<String>> words ;
    private String prevWord;


    public Markov(){
        words = new HashMap<>();
        ArrayList<String> arrayList = new ArrayList<>();
        words.put(PUNCTUATION, arrayList);
        prevWord = PUNCTUATION;
    }

    public HashMap<String, ArrayList<String>> getWords() {
        return words;
    }
    public void addFromFile(String filename){
        try{
            String str = "";
            Scanner sc = new Scanner(new File(filename));
            while(sc.hasNext()) {
                str += sc.next() + " ";
            }
            sc.close();
            addLine(str);
        } catch(FileNotFoundException e){
            System.out.println("Could not find the file " + e);
            System.exit(0);
            //return;
        }
    }
    public void addLine(String str){
        if(str.length() > 0){
            String []arrOfStr = str.split(" ");
            for(String word: arrOfStr){
                addWord(word);
            }
        }
    }
    public void addWord(String str){
        boolean temp = endsWithPunctuation(prevWord);

        if(temp){
            ArrayList<String> second = words.get(PUNCTUATION);
            second.add(str);
            words.replace(PUNCTUATION,second);
        }
        else{
            boolean results = words.containsKey(prevWord);
            ArrayList<String> list = new ArrayList<>();

            if(!results ) {
               list.add(str);
               words.put(prevWord,list);
            }
            else{
                ArrayList<String> temps= words.get(prevWord);
                temps.add(str);
                words.replace(prevWord,temps);
            }
        }
        prevWord = str;
    }

    public String getSentence(){
        String sentence = "";
        String space = " ";
        boolean result;

        Random rand = new Random();
        ArrayList<String> temp = words.get(PUNCTUATION);
        int randomElement = rand.nextInt(temp.size());
        String random = temp.get(randomElement);
        sentence+=random;
        if(!(endsWithPunctuation(random)))
        {
            do{
                String randWord = randomWord(random);
                sentence = sentence+space+randWord ;
                random = randWord;
                result = endsWithPunctuation(randWord);
            }while(!result);
        }
        return sentence;
    }

   public String randomWord(String str){
        Random rand = new Random();
        ArrayList<String> temp = getWords().get(str);
        int randomElement = rand.nextInt(temp.size());
        return temp.get(randomElement);
    }
    public Boolean endsWithPunctuation(String str){
        String temp = str.substring(str.length()-1);
        return (temp.equals("!") || temp.equals(PUNCTUATION_MARKS) || temp.equals("?") || str.equals(PUNCTUATION));
    }

    @Override
    public String toString() {
        return words + " ";
    }

    public static void main(String[] args) {
        Markov markov = new Markov();
        markov.addFromFile("spam.txt");
        System.out.println(markov);
        for (int i = 0; i < 10; i ++){
            System.out.println(markov.getSentence());
        }
    }
}
