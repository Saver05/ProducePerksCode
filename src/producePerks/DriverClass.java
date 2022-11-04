/**
 * This is the driver class. 
 * Note that it should NEVER interact with the Record class.
 * 
 * MyHashTable.java is your creation of a Hash Table.
 * Record.java is a record object that is stored in the hash table.
 * Transaction.java is a storage object that is formed based on one line of the data file.
 * Customer.java is a storage object that represents one person who uses produce perk.
 * ProducePerksAnalysis.java is the class that you are going to use to answer your questions.
 * 
 * Links between classes
 * The hash table has an array of Records.
 * Each Record holds a (key, value) pair where the key is the SNAP-ID and the value is the Customer.
 * Each Customer has two ArrayLists (dynamic arrays) to hold their transactions: distributed and redeemed.
 * 
 * Update: November 2022
 *  - Refined comments
 *  - Added ProducePerksAnalysis.java to add clarity about the different steps
 *    within the project, simplify look of driver, and to better compartmentalize code
 * 
 */
package producePerks;
import java.util.ArrayList;

/**
 * @author Katie Timmerman
 * @author < your name >
 *
 * Course: Data Structures and Algorithms 
 * Semester:
 */

public class DriverClass {

   
    public static void main(String[] args)  {
        testingHashTable();
        //ProducePerksAnalysis.startAnalysis();

    }

    private static void testingHashTable() {
        MyHashTable myTable = new MyHashTable(7);
        ArrayList<Integer> addedKeys = new ArrayList<Integer>();
        System.out.println("TESTING ADDING ELEMENTS");
        
        for (int i = 0; i < 5; i++) {
            int key_value = (int) (Math.random() * 20);
            if (myTable.insert(key_value, new Customer(key_value))) {
                addedKeys.add(key_value);
            } else {
                i--; //Don't count the insert if key already in table
            }
        }
        System.out.println("My table should have 5 elements.\nEnsure that "
                + "everything was inserted to the correct spot and that something"
                + "used the probe function.\nIdeally, check that rolling over occured.");
        System.out.println(myTable);

        System.out.println("\n\nTESTING FINDING ELEMENTS");
        System.out.println("Ensure that the values are in the correct spot in the table."
                + "\nAgain, you should make sure something that used the probe function was located.");
        for (int i = 0; i < addedKeys.size(); i++) {
            int key = addedKeys.get(i);
            System.out.println("The value " + key + " found: " + myTable.find(key));
        }
        System.out.println("The value 346 should not be found: " + myTable.find(346));
        //System.out.println(myTable);

        System.out.println("\n\nTESTING REMOVING ELEMENTS");
        System.out.println("Ensure that the keys were removed.\nAgain, you want to"
                + "make sure this works with the probe function.");
        for (int i = 0; i < addedKeys.size(); i += 2) {
            int key = addedKeys.get(i);
            System.out.println("The value " + key + " removed: " + myTable.remove(key));
        }
        System.out.println("The table now looks like this:");
        System.out.println(myTable);

        System.out.println("\n\nTESTING FINDING REMOVED ELEMENTS");
        System.out.println("Make sure that the removed values are not found.");
        for (int i = 0; i < addedKeys.size(); i += 2) {
            int key = addedKeys.get(i);
            System.out.println("The value " + key + " found: " + myTable.find(key));
        }
        //System.out.println(myTable);

        System.out.println("\n\nTESTING FINDING VALID ELEMENTS AFTER REMOVAL");
        System.out.println("These are should be found.");
        for (int i = 1; i < addedKeys.size(); i += 2) {
            int key = addedKeys.get(i);
            System.out.println("The value " + key + " found: " + myTable.find(key));
        }
        System.out.println(myTable);
        
        System.out.println("\n\nTESTING ADDING ELEMENTS over tombstones");
        
        for (int i = 0; i < 2; i++) {
            int key_value = (int) (Math.random() * 20);
            if (myTable.insert(key_value, new Customer(key_value))) {
                addedKeys.add(key_value);
            } else {
                i--; //Don't count the insert if key already in table
            }
        }
        System.out.println("My table is now this:\n" + myTable);

    }

   
}
