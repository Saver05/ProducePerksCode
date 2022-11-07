/**
 * This is where you will implement your hash table.
 */
package producePerks;

import java.math.BigInteger;

/**
 * @author Katie Timmerman
 * @author Jack Frambes
 *
 * Course: Data Structures and Algorithms Semester:
 */
public class MyHashTable {

    private final int CAPACITY; //Capacity of the table
    private Record[] hashAry; // the array of records
    private int size; //Number of values in the table

    /**
     * This constructor builds a hash table of the passed in size. It then fills
     * the table with a bunch of empty records.
     *
     * @param size
     */
    public MyHashTable(int size) {
        this.CAPACITY = size;
        hashAry = new Record[CAPACITY];
        this.size = 0;
        for (int i = 0; i < CAPACITY; i++) {
            Record r = new Record();
            hashAry[i] = r;
        }
    }

    /*Inserts the key/value into the hashtable if there is room and the
    key isn't already in the table*/
    public boolean insert(int key, Customer value) {
        if (size==CAPACITY)
        {
            return false;
        }
        int index = hashFunction(key);
        int home = index;
        int collisions =0;
        boolean repeat = true;
        while (!hashAry[index].isEmpty())
        {
            if (hashAry[index].isTombstone()) // for some reason if this was in the while loop it was looping forever
            {
                Record r = new Record(key,value);
                hashAry[index]=r;
                size++;
                return true;
            }
            index = probeFunction(key,home,collisions);
            collisions++;
        }
        Record r = new Record(key,value);
        hashAry[index]=r;
        size++;
        return true;
    }

    /**
     * Returns the home Index for the given key.
     */
    private int hashFunction(int key)
    {
        BigInteger r = BigInteger.valueOf(key*key);
        String m = r.toString();
        int mid = Integer.valueOf(m.substring(m.length()/2,m.length()/2+1));
        while (mid>=CAPACITY)
        {
            int add = mid-CAPACITY;
            mid = add;
        }
        return mid;
    }

    /**
     * Returns the next index that should be investigated. For example, if my
     * home index is 4 and I am calling a +1 linear probe for the 3rd time, this
     * function should return 7.
     *
     * you may not use all of the parameters with the basic probe function
     */
    private int probeFunction(int key, int homeIndex, int collisions) {
        int r = (hashFunction(key) + collisions * (7-(key%7)));
        while (r>=CAPACITY)
        {
            int add = r-CAPACITY;
            r = add;
        }
        return r;
    }

    /**
     * This is a private method only to be used internally. It returns the index
     * where the record with the key is stored in the table. It returns -1 if
     * the key is not found in the table.
     *
     * @param key
     * @return
     */
    private int indexOf(int key) {
        int index= hashFunction(key);
        int home = index;
        int collisions =0;
        while(hashAry[index].getValue()!=null || hashAry[index].isTombstone())
        {
            if (hashAry[index].getKey()==key)
            {
                return index;
            }
            index = probeFunction(key,home,collisions);
            collisions++;
        }
        return -1;
    }

    /*Finds an element with a certain key and returns the associated Customer*/
    public Customer find(int key)
    {
        int index = hashFunction(key);
        int home = index;
        int collisions=0;
        while(hashAry[index].getValue()!=null || hashAry[index].isTombstone())
        {
            if(hashAry[index].getKey()==key)
            {
                return hashAry[index].getValue();
            }
            index = probeFunction(key,home,collisions);
            collisions++;
        }
        return null;
    }

    /*Deletes a table key and returns the associated value*/
    public Customer remove(int key) {
        Customer r = find(key);
        int i = indexOf(key);
        if (r!=null && i!=-1)
        {
            hashAry[i].deleteRecord();
            size--;
            return r;
        }
        return null;
    }

    /**
     * Already Complete.
     * Returns a string representation of the hash table
     *
     * @return
     */
    public String toString() {
        String table = "";
        for (int i = 0; i < this.CAPACITY; i++) {
            table += i + ". " + hashAry[i].toString() + "\n";
        }
        return table;
    }

}
