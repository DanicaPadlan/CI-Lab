/*  Student information for assignment:
 *
 *  On my honor, Danica Padlan, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: dmp3357
 *  email address: danica_padlan@yahoo.com
 *  Number of slip days I am using: 0
 */

import java.util.ArrayList; //import the ArrayList

public class NameRecord implements Comparable<NameRecord> {

    private String name;
    private int baseDecade;
    private final int TEN = 10;
    private ArrayList<Integer> ranks;

    //creates new NameRecord
    public NameRecord(String name, int baseDecade, ArrayList<Integer> data){
        ranks = new ArrayList<Integer>();
        this.name = name;
        this.baseDecade = baseDecade;

        //goes through given arrayList and adds values to ranks
        for(int x = 0; x < data.size(); x++){
            ranks.add(data.get(x));
        }
    }

    //returns name
    public String getName(){
        return name;
    }

    //returns base decade
    public int getBaseDecade(){
        return baseDecade;
    }

    //returns rank of desired decade
    //pre: 0 <= decade < ranks.size()
    public int getRank(int decade){
        if(decade < 0 || decade >= ranks.size()){
            throw new IllegalArgumentException("Pre-conditions have been violated: "
            + "0 <= decade < ranks.size()");
        }
        return ranks.get(decade);
    }

    //returns best decade that is closer to 1
    public int bestDecade(){
        int bestYear = 0;
        int best = Integer.MAX_VALUE;
        for(int x = 0; x < ranks.size(); x++){
            if(ranks.get(x) < best && ranks.get(x) != 0){
                best = ranks.get(x);
                bestYear = x;
            }
        }
        return (bestYear * TEN) + baseDecade;
    }

    //checks how many times name is in top 1000
    public int numTopThousand(){
        int top = 0;
        for(int x = 0; x < ranks.size(); x++){

            //checks for ranks that are greater than 0
            if(ranks.get(x) > 0){
                top++;
            }
        }
        return top;
    }

    //
    //checks if name has been in top 1000 for all decades
    public boolean topThousandEveryDecade(){
        return numTopThousand() == ranks.size();
    }

    //checks if name has been in top 1000 only once
    public boolean topThousandOnce(){
        return numTopThousand() == 1;
    }

    //checks if name getting closer to 1
    public boolean increaseRank(){
        for(int x = 1; x < ranks.size(); x++){

            //checks for repeating values, values that are greater than previous rank, or compare unranked to top 1000
            //hanging format to avoid long code on one line
            if(ranks.get(x-1) == ranks.get(x) || (ranks.get(x) == 0 && ranks.get(x-1) > ranks.get(x)) ||
                    (ranks.get(x-1) < ranks.get(x))){
                return false;
            }
        }
        return true;
    }

    //checks if name is growing away from 1
    public boolean decreaseRank(){
        for(int x = 1; x < ranks.size(); x++){

            //checks for repeating values, values that are lesser than previous rank, or compare unranked to top 1000
            //hanging format to avoid long code on one line
            if(ranks.get(x-1) == ranks.get(x)  || (ranks.get(x-1) == 0 && ranks.get(x-1) < ranks.get(x)) ||
                    (ranks.get(x) != 0 && ranks.get(x-1) > ranks.get(x))){
                return false;
            }
        }
        return true;
    }

    @Override
    //returns results of name and decades' ranks
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append(getName() + "\n");

        //prints out decade and results
        for(int x = 0; x < ranks.size(); x++){
            result.append("" + ((x*TEN) + baseDecade) + ": " + ranks.get(x) + "\n");
        }
        return result.toString();
    }

    @Override
    //compares String values of compared names
    //pre: NameRecord o != null
    public int compareTo(NameRecord o) {
        if(o == null){
            throw new IllegalArgumentException("Pre-conditions have been violated: "
                    + "NameRecord o != null");
        }

        //returns difference of String values
        return getName().compareTo(o.getName());
    }
}
