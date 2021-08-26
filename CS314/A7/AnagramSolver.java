
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


import java.util.*;

public class AnagramSolver {

    private HashMap<String, LetterInventory> dictionaryMap;

    /*
     * pre: list != null
     * @param list Contains the words to form anagrams from.
     * Constructor for AnagramSolver
     */
    public AnagramSolver(Set<String> dictionary) {
        // CS314 Students, add your code here
        if(dictionary == null){
            throw new IllegalStateException("Pre-condition not satisfied: dictionary != null");
        }

        dictionaryMap = new HashMap<>();
        //loads hashMap with String and corresponding LetterInventory object
        Iterator <String> read = dictionary.iterator();
        while(read.hasNext()){
            String key = read.next();

            //no duplicates bc Set doesn't allow duplicates
            dictionaryMap.put(key, new LetterInventory(key));
        }
    }


    /*
     * pre: maxWords >= 0, s != null, s contains at least one 
     * English letter.
     * Return a list of anagrams that can be formed from s with
     * no more than maxWords, unless maxWords is 0 in which case
     * there is no limit on the number of words in the anagram
     */
    public List<List<String>> getAnagrams(String s, int maxWords) {
        // CS314 Students, add your code here.
        if(s == null || maxWords < 0){
            throw new IllegalStateException("Pre-condition not met: s != null and maxWords >= 0");
        }
        LetterInventory given = new LetterInventory(s);
        if(given.isEmpty()){
            throw new IllegalStateException("LetterInventory is empty. No English characters found.");
        }

        ArrayList<List<String>> anagrams = new ArrayList<>();
        ArrayList<String> shortDiction = getNewDiction(given);
        Collections.sort(shortDiction);
        //maxWords = 0 means there is NO LIMIT to anagram list sizes
        maxWords = (maxWords == 0) ? Integer.MAX_VALUE: maxWords;
        anagramRecursive(anagrams, shortDiction, 0, new ArrayList<>(), given, maxWords);
        Collections.sort(anagrams, new AnagramCompare());
        return anagrams;

    }

    //change the way its searching through maps
    //finds all anagram combinations
    private void anagramRecursive(ArrayList<List<String>> anagrams, ArrayList<String> shortList, int dictionIndex, ArrayList<String> group, LetterInventory given, int maxWords){
        //checks for base case of empty LetterInventory and words with maxWord count
        if(given.isEmpty() && group.size() <= maxWords){
            //should be in sorted order due to sorted dictionary
            ArrayList<String> copy = new ArrayList<>(group);
            anagrams.add(copy);
        }

        if(!shortList.isEmpty() &&  group.size() < maxWords){
            //goes through short dictionary
            for(int x = dictionIndex; x < shortList.size(); x++ ){
                String cur = shortList.get(x);
                LetterInventory temp = given.subtract(dictionaryMap.get(cur));
                //checks if LetterInventory fits letter requirements of given LetterInventory
                if(temp != null){
                    group.add(cur);
                    anagramRecursive(anagrams, shortList, x, group, temp, maxWords);
                    group.remove(group.size()-1);
                }
            }
        }
    }

    //creates new list of possible words based on given letterInventory
    private ArrayList<String> getNewDiction(LetterInventory s){
        ArrayList<String> list = new ArrayList<>();
        Iterator reader = dictionaryMap.keySet().iterator();
        while(reader.hasNext()){
            String temp = reader.next().toString();
            //checks if LetterInventory fits letter requirements of given LetterInventory
            if(s.subtract(dictionaryMap.get(temp)) != null){
                list.add(temp);
            }
        }
        return list;
    }

    //compares anagrams list to be sorted
    class AnagramCompare implements Comparator<List<String>>{

        //compares lists' sizes
        @Override
        public int compare(List<String> a1, List<String> a2){
            //returns difference between first and second array size
            if(a1.size() != a2.size()){
                return a1.size() - a2.size();
            }
            return alphabetCompare(a1, a2);
        }

        //compares Lists lexicographically on each String
        private int alphabetCompare(List<String> a1, List<String> a2) {
            for (int x = 0; x < a1.size(); x++) {
                String a1Word = a1.get(x);
                String a2Word = a2.get(x);

                //compares each word
                if(!a1Word.equals(a2Word)){
                    return a1Word.compareTo(a2Word);
                }
            }
            return 0;
        }
    }
}
