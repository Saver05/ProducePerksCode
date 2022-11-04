/** 
 * This class is completed for you.
 * 
 * Each record holds an integer key, a Customer, and a record type.
 * 
 * The record type is stored as an enumerated value.  Enumerated values allow
 * us to create a data type that holds a finite number of options. This allows it
 * to run efficiently and makes it so less error checking needs to be done.  
 * 
 * You shouldn't need to deal with the enumerated type. Just call the is<Type>()
 * methods to check if the type matches.
 * 
 * The only other class that should directly interact with the record class is
 * MyHashTable.java
 * 
 * This class is complete and should not need updated.
 */
 
package producePerks;

/**
 * @author Katie Timmerman
 * @author < your name >
 * 
 * Course: Data Structures and Algorithms
 * Semester: 
 */


// There are three types of records in a closed hash:
// Normal records, empty records, and tombstones
enum RecordType { normalRecord, emptyRecord, tombstone };


public class Record{

    private int key;
    private Customer value;
    private RecordType type;

    /**
     * Create an empty record.
     */
    public Record() {
        key = 0;
        type = RecordType.emptyRecord;
    }

    /**
     * Create a normal record.
     * @param key
     * @param c 
     */
    public Record(int key, Customer c) {
        this.key = key;
        this.value = c;
        type = RecordType.normalRecord;
    }

    // Convert a record to a tombstone
    public void deleteRecord() {
        key = 0;
        value = null;
        type = RecordType.tombstone;
    }

    // Get the integer key of a record
    public int getKey() {
        return key;
    }

    // Get the value of a record
    public Customer getValue() {
        return value;
    }

    // Check if a record is empty
    public boolean isEmpty() {
        return (type == RecordType.emptyRecord);
    }

    // Check if a record is a normal record
    public boolean isNormal() {
        return (type == RecordType.normalRecord);
    }

    // Check if a record is a tombstone
    public boolean isTombstone() {
        return (type == RecordType.tombstone);
    }

    // Overload the toString operator for printing records
    @Override
    public String toString() {
        if (isTombstone()) {
            return "<<Tombstone>>";
        } else if (isEmpty()) {
            return "<<Empty>>";
        } else {
            return "Key: " + key + ", Value: " + value;
        }
    }

}
