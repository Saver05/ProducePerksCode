/**
 * The customer class represents a single individual who uses Produce Perks.
 * 
 * The customer has an id and two lists of transactions. One list were coupons were
 * distributed to the customer and one list were coupons were redeemed by the customer.
 * 
 * Note that the add methods add a single transaction.
 * The getter methods return the entire arrayList.
 * 
 * This class uses the Transaction class.
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

public class Customer {

    private final int ID;
    private ArrayList<Transaction> distributed;
    private ArrayList<Transaction> redeemed;
   
   /**
    * construct a customer with the given id and no transactions.
    * @param id 
    */
    public Customer(int id){
        this.ID = id;
        distributed = new ArrayList();
        redeemed = new ArrayList();
    }
    
    /**
     * add a transaction to the distributed list.
     * @param t 
     */
    public void addDistributed(Transaction t){
        getDistributed().add(t);
    }
    
    /**
     * add a transaction to the redeemed list
     * @param t 
     */
    public void addRedeemed(Transaction t){
        getRedeemed().add(t);
    }
    
    /**
     * get the list of distributed transactions.
     * @return 
     */
    public ArrayList<Transaction> getDistributed() {
        return distributed;
    }

    /**
     * get the list of redeemed transactions.
     * @return 
     */
    public ArrayList<Transaction> getRedeemed() {
        return redeemed;
    }
    
    /**
     * returns a string representation of the customer.
     * @return 
     */
    public String toString(){
        return "ID " + ID + " - Num Dist Trans " + getDistributed().size() 
                + " - Num Rede Trans " + getRedeemed().size();
    }
}
