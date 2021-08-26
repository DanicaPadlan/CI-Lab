/* CS 314 STUDENTS: FILL IN THIS HEADER.
 *
 * Student information for assignment:
 *
 *  On my honor, Danica Padlan, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: dmp3357
 *  email address: danica_padlan@yahoo.com
 *  TA name: Noah
 *  Number of slip days I am using: 0
 */

//class that is made to be a model of a letter inventory
public class LetterInventory {

    final private int BANK_SIZE = 26;
    final private int A_KEY = 'a';
    final private int Z_KEY = 'z';
    private int curSize;
    private int[] letterFreqs;

    //constructor for LetterInventory
    public LetterInventory(String word){
        if(word == null){
            throw new IllegalStateException("Pre-conditions not satisfied: word != null");
        }

        letterFreqs = new int[BANK_SIZE];
        fillBank(word.toLowerCase());
    }

    //fills frequency bank based on given word
    private void fillBank(String word){
        for(int x = 0; x < word.length(); x++){
            char temp = word.charAt(x);

            //checks if char is in English alphabet
            if(A_KEY <= temp && temp <= Z_KEY){
                letterFreqs[getIndex(temp)]++;
                curSize++;
            }
        }
    }

    //getsIndex of given char
    private int getIndex(char ch){
        //must turn all chars into lowercase
        int charVal = getLowerCase(ch);
        //math for index tracking
        //25 - ('z' - charVal)
        return (BANK_SIZE - 1) - (Z_KEY - charVal);
    }

    //gets char based off of index
    private char getChar(int index){
        return (char) (A_KEY + index);
    }

    //gets frequency count of given char
    public int get(char ch){
        ch = getLowerCase(ch);
        if(ch < A_KEY || ch > Z_KEY){
            throw new IllegalStateException("Pre-condition: char is not in English alphabet");
        }
        return letterFreqs[getIndex(ch)];
    }

    //translates and returns lowercase version of char
    private char getLowerCase(char ch){
        String lower = "" + ch;
        lower = lower.toLowerCase();
        return lower.charAt(0);
    }

    //returns amount of all frequencies in letter bank
    public int size(){
        return curSize;
    }

    //returns if size of letter bank is empty or not
    public boolean isEmpty(){
        return curSize == 0 ? true: false;
    }

    //returns string of all letters available
    public String toString(){
        if(isEmpty()){
            return "";
        }
        StringBuilder temp = new StringBuilder();
        for(int freqIndex = 0; freqIndex < letterFreqs.length; freqIndex++){
            if(letterFreqs[freqIndex] > 0){
                for(int times = 0; times < letterFreqs[freqIndex]; times++){
                    temp.append(getChar(freqIndex));
                }
            }
        }
        return temp.toString();
    }

    //creates new LetterInventory based off new combination of word with another LetterInventory
    public LetterInventory add(LetterInventory other){
        if(other == null){
            throw new IllegalStateException("Pre-condition not satisfied: other != null");
        }

        //sends combined Strings of both LetterInventory's for new LetterInventory
        return new LetterInventory(toString() + other.toString());
    }

    //creates new LetterInventory with deducted frequencies from another LetterInventory
    public LetterInventory subtract(LetterInventory other){
        if(other == null){
            throw new IllegalStateException("Pre-condition not satisfied: other != null");
        }

        //creates copy to not alter current LetterInventory
        LetterInventory copy = new LetterInventory(toString());
        for(int x = 0; x < BANK_SIZE; x++){
            copy.letterFreqs[x] -= other.letterFreqs[x];

            //if new value is negative, array is invalid for new LetterInventory
            if(copy.letterFreqs[x] < 0){
                return null;
            }
        }
        return new LetterInventory(copy.toString());
    }

    //overrides equals to compare LetterInventory objects
    @Override
    public boolean equals(Object o){
        //checks if object is LetterInventory
        if(o instanceof LetterInventory){
            for(int x = 0; x < BANK_SIZE; x++){

                //checks if each integer is the same
                if(letterFreqs[x] != ((LetterInventory) o).letterFreqs[x]){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
