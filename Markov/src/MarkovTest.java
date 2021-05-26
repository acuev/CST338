/*
Author: Abigail Cuevas
Title: MarkovTest.java
Date: 02/19/2021
Description: This is the where we will test the Markov project. I will
be testing the getWords(), getSentence(), randomWord(), endsWithPunctuation(),
toString() and the constructor.
 */

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MarkovTest {
    @Test
    void getWords() {
        HashMap<String, ArrayList<String>> temp = new HashMap<>();
        Markov markov = new Markov();
        markov.addFromFile("HelloWorld.txt");
        temp = markov.getWords();
        assertEquals(temp, markov.getWords());
    }

    @Test
    void getSentence() {
        Markov markov = new Markov();
        markov.addFromFile("HelloWorld.txt");
        markov.getSentence();
    }

    @Test
    void randomWord() {
        Markov markov = new Markov();
        markov.addFromFile("HelloWorld.txt");
        String word = markov.randomWord("Hello");
        assertEquals(word, "World!");
    }

    @Test
    void Punctuation() {
        Markov markov = new Markov();
        markov.addFromFile("HelloWorld.txt");
        assertFalse(markov.endsWithPunctuation("Hello"));
        assertTrue(markov.endsWithPunctuation("There!"));
    }
    @Test
    void testToString() {
        HashMap<String, ArrayList<String>> first = new HashMap<>();
        HashMap<String, ArrayList<String>> second = new HashMap<>();
        ArrayList<String> value = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();
        values.add("Hello");
        values.add("there");
        first.put("First",values);
        value.add("Hello");
        value.add("there");
        second.put("Secon",value);

        int secondVal = second.toString().length();
        int firstVal = first.toString().length();

        assertEquals(secondVal,firstVal);
    }
    @Test
    void Constructor(){
        Markov marko = new Markov();
        System.out.println(marko);
        //prints out the object created, which is the PUNCTUATION with a blank Arraylist
    }
}