/*
Title: Histogram.java
Date: 02/01/21
*/

import java.io.*;
import java.util.Scanner;

public class Histogram{
    public static void main(String []args) throws IOException
    {//main
        System.out.println("Enter a file name: ");
        //user inputs file name
            String filename = getFileName();
            File fil = new File(filename);
            if (!fil.exists()) {
                System.out.println("The file " + fil + " doesn't exist.");
                System.exit(0);
            }
            char[] letter = {'A', 'B','C','D','E','F','G','H','I','J','K'} ;
            int[] count = new int[12];

            read(letter, count, filename);
            sort(letter, count);
            display(letter, count);
    }
    // Read the filename from the user
    public static String getFileName() {
        Scanner scan = new Scanner(System.in);
        return scan.next();//scanner to read users file name
    }
    //read the letters from the file that user provided
    public static void read(char[] letter, int[] letterCount, String filename) {
        try{
            int temp;//temporary int
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNext()){//read line
                String str = sc.nextLine();
                temp = str.charAt(0) - 'A';
                letterCount[temp]++;
            }
            sc.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    //sorts the letters in alphabetical order
    public static void sort(char[] letter, int[] letterCount)
    {
        int i = 0, x = 1, temp;
        char temp2;
        //bubble sort, google helped
        while(i != 10){
            int j = i + x;
            while(j != 11){
                if(letterCount[i] < letterCount[j]){
                    temp = letterCount[i]; letterCount[i] = letterCount[j]; letterCount[j]=temp;
                    temp2 = letter[i]; letter[i] = letter[j]; letter[j] = temp2; }
                if(letterCount[i] == letterCount[j]){
                    if(letter[i] < letter[j]){
                        temp = letterCount[i]; letterCount[i] = letterCount[j]; letterCount[j] = temp;
                        temp2 = letter[i]; letter[i] = letter[j]; letter[j] = temp2;
                    }
                } j++;
            } i++;
        }
    }
    //displays the histogram
    public static void display(char[] letter, int[] lettercount)
    {
        String blank = "";//for spaces
        //print out char occurances
        System.out.println("Char Occurrences");
        int i = 10;
        while(i != -1){
            if(lettercount[i] != 0){
                System.out.println(letter[i] + " " + lettercount[i]);
            }
            i--;
        }
        i = 0;
        // print out how many times char occured
        int howManytimes, limit;
        System.out.println("\n=============================");
        while(lettercount[i] != 0 && i < 11 ){
            howManytimes = lettercount[i];
            while(lettercount[i] == howManytimes && i < 11 ){
                blank = letter[i] + " " + blank;i++;
            }
            if(i == 11)
                limit = 0;
            else
                limit = lettercount[i];
            while(limit < howManytimes){
                System.out.println(String.format("|%7d|%19s", howManytimes, blank));
               // System.out.printf("|%7d|%19s", howManytimes, blank);
                howManytimes--;
            }
        }
        i = 0;
        blank = "";
        System.out.println("\n-----------------------------");
        while(i != 11){
            blank = letter[i] + " " + blank;
            i++;
        }
        System.out.println(" " + blank + " ");
    }
}
