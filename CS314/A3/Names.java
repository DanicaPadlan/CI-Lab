/*  Student information for assignment:
 *
 *  On my honor, Danica Padlan, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: dmp3357
 *  email address: danica_padlan@yahoo.com
 *  Number of slip days I am using: 0
 */

import javax.naming.Name;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
* A collection of NameRecords. 
* Stores NameRecord objects and provides methods to select
* NameRecords based on various criteria.
*/
public class Names {

    private ArrayList<NameRecord> nameData;

    /**
     * Construct a new Names object based on the data source the Scanner 
     * sc is connected to. Assume the first two lines in the 
     * data source are the base year and number of decades to use.
     * Any lines without the correct number of decades are discarded 
     * and are not part of the resulting Names object. 
     * Any names with ranks of all 0 are discarded and not
     * part of the resulting Names object.
     * @param sc Is connected to a data file with baby names 
     * and positioned at the start of the data source.
     */
    public Names(Scanner sc) {
        if(sc == null){
            throw new IllegalArgumentException("Violation of pre-condition: Names(), sc != null");
        }

        nameData = new ArrayList<NameRecord>();
        int baseDecade = sc.nextInt();
        int numDecades = sc.nextInt();

        Scanner line;
        //checks for nextLine in given data
        while(sc.hasNextLine()){

            //creates new Scanner for each line
            line = new Scanner(sc.nextLine());

            //checks if line has more elements
            while(line.hasNext()){

                ArrayList<Integer> ranks = new ArrayList<>();

                //assumes String will be first on each line
                String name = line.next();

                while(line.hasNextInt()){
                    ranks.add(line.nextInt());
                }

                //checks if array list size matches numDecades and is not empty to be created a NameRecord
                if(ranks.size() == numDecades && !isEmpty(ranks)){
                    nameData.add(new NameRecord(name, baseDecade, ranks));
                }
            }
        }
        Collections.sort(nameData);
    }

    //checks if arrayList is all 0's/empty data
    private boolean isEmpty(ArrayList<Integer> data){
        int count = 0;

        //goes through data
        for(int x = 0; x < data.size(); x++){

            //counts how many times 0 is present
            if(data.get(x) == 0){
                count++;
            }
        }
        return count == data.size();
    }

   /**
    * Returns an ArrayList of NameRecord objects that contain a 
    * given substring, ignoring case.  The names must be in sorted order based 
    * on name.
    * @param partialName != null, partialName.length() > 0
    * @return an ArrayList of NameRecords whose names contains
    * partialName. If there are no NameRecords that meet this
    * criteria returns an empty list. 
    */
   public ArrayList<NameRecord> getMatches(String partialName) {
       //checks for errors in pre-conditions
       if(partialName == null || partialName.length() < 0){
           throw new IllegalArgumentException("Violation of pre-condition: getMatches(), " +
                   "partialName != null, partialName.length() > 0");
       }

       ArrayList<NameRecord> temp = new ArrayList<NameRecord>();

       //goes through NameRecords
       for(int x = 0; x < nameData.size(); x++){

           //checks if current NameRecord's name.length >= partialName.length
           if(nameData.get(x).getName().length() >= partialName.length()){

               //calculates names length difference to check for partialName correctly
               int begSubLim = nameData.get(x).getName().length() - partialName.length();

               //goes through NameRecord's name to for partial name
               for(int y = 0; y <= begSubLim; y++ ){

                   //created string variable from substring y to the partialName's length
                   String port = nameData.get(x).getName().substring(y, y + partialName.length());

                   //checks for match
                   if(port.equalsIgnoreCase(partialName)){
                       temp.add(nameData.get(x));

                       //ends the loop
                       y+= begSubLim;
                   }
               }
           }
       }
       return temp;
   }

   /**
    * Returns an ArrayList of Strings of names that have been ranked in the
    * top 1000 or better for every decade. The Strings  must be in sorted 
    * order based on name. 
    * @return A list of the names that have been ranked in the top
    * 1000 or better in every decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list.
    */
   public ArrayList<String> rankedEveryDecade() {
       ArrayList<String> temp = new ArrayList<String>();
        for(int x = 0; x < nameData.size(); x++){

            //checks if NameRecord is top 1000 in every decade
            if(nameData.get(x).topThousandEveryDecade()){
                temp.add(nameData.get(x).getName());
            }
        }
       return temp;
   }

   /**
    * Returns an ArrayList of Strings of names that have been ranked in the 
    * top 1000 or better in exactly one decade. The Strings must be in sorted 
    * order based on name. 
    * @return A list of the names that have been ranked in the top
    * 1000 or better in exactly one decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list.
    */
   public ArrayList<String> rankedOnlyOneDecade() {
       ArrayList<String> temp = new ArrayList<String>();
       for(int x = 0; x < nameData.size(); x++){

           //checks if NameRecords is in top 1000 once
           if(nameData.get(x).topThousandOnce()){
               temp.add(nameData.get(x).getName());
           }
       }
       return temp;
   }

   /**
    * Returns an ArrayList of Strings of names that have been getting more
    * popular every decade. The Strings  must be in sorted order based on name.
    * @return A list of the names that have been getting more popular in
    * every decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list. 
    */
   public ArrayList<String> alwaysMorePopular() {
       ArrayList<String> temp = new ArrayList<String>();
       for(int x = 0; x < nameData.size(); x++){

           //checks for increasing NameRecords
           if(nameData.get(x).increaseRank()){
               temp.add(nameData.get(x).getName());
           }
       }
       return temp;
   }

   /**
    * Returns an ArrayList of Strings of names that have been getting less
    * popular every decade. The Strings  must be in sorted order based on name.
    * @return A list of the names that have been getting less popular in
    * every decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list. 
    */
   public ArrayList<String> alwaysLessPopular() {
       ArrayList<String> temp = new ArrayList<String>();
       for(int x = 0; x < nameData.size(); x++){

           //checks for decreasing NameRecords
           if(nameData.get(x).decreaseRank()){
               temp.add(nameData.get(x).getName());
           }
       }
       return temp;
   }
   
   /**
    * Return the NameRecord in this Names object that matches the given String.
    * <br>
    * <tt>pre: name != null</tt>
    * @param name The name to search for.
    * @return The name record with the given name or null if no NameRecord in this Names
    * object contains the given name.
    */
   public NameRecord getName(String name) {
       //checks for errors in pre-conditions
       if(name == null) {
           throw new IllegalArgumentException("The parameter name cannot be null");
       }

       //goes through data
       for(int x = 0; x < nameData.size(); x++){

           //checks for matching names
           if(nameData.get(x).getName().equalsIgnoreCase(name)){
               return nameData.get(x);
           }
       }
       return null;
   }
}
