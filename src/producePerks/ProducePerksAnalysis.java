/**
 * You shouldn't use this file until you have finished all other parts of your project.
 *
 * This class utilizes your hash table in order to answer questions for Produce Perks.
 * 
 * You will implement the final methods numberOfCouponsUsed completeAnalysis().
 * 
 *
 */
package producePerks;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Katie Timmerman
 * @author <Your name>
 *
 *
 */
public class ProducePerksAnalysis {

    private static ArrayList<Integer> customer_keys;
    private static MyHashTable table;

    /**
     * This is essentially the "main method" for this part of the project. It
     * will act as the driver for your analysis. It will call the other parts
     * within this class.
     *
      */
    static void startAnalysis() {
        loadData();
        numberOfCouponsUsed();
        answerQuestion();
    }

    /**
     * This method loads the data from the files. It utilizes other files to do
     * so.
     *
     * post condition: MyHashTable will be set to a hash table of size 12007. It
     * will hold customers. Each customer will have 2 arraylists that will hold
     * transactions. One arraylist will hold transactions where they received a
     * coupon to get free produce. The other array will hold transactions where
     * they used a coupon to get free produce. customer_keys holds all the IDs
     * of the customers.
     *
     */
    private static void loadData() {
        customer_keys = getCustomerIds();
        table = new MyHashTable(12007);

        //Create and Add Customers
        for (int i = 0; i < customer_keys.size(); i++) {
            int key = customer_keys.get(i);
            table.insert(key, new Customer(key));
        }

        try {
            //Read in transactions
            readInTransactions(2019, 1);
            readInTransactions(2020, 1);
            readInTransactions(2019, 2);
            readInTransactions(2020, 2);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.exit(0);
        }

        //System.out.println(table); //FOR DEBUGGING PURPOSES ONLY
    }

    /**
     * This loads all the customer IDs for use in other parts of the program,
     * such as the analysis.
     *
     * @return ArrayList of customer IDs
     */
    private static ArrayList<Integer> getCustomerIds() {
        ArrayList<Integer> keys = new ArrayList();

        try {
            File file_ids = new File("Customer Ids.csv");
            Scanner in = new Scanner(file_ids);

            while (in.hasNext()) {
                keys.add(in.nextInt());
            }

            in.close();

        } catch (FileNotFoundException ex) {
            System.err.println("File Customer Ids.csv not found");
            System.exit(0);
        }

        return keys;

    }

    /**
     * This method reads in transactions from the file <Type> <year>
     * transactions.csv Each line of the file is a transaction. See the
     * transaction class for details of how that line should be organized. The
     * transaction is added to the corresponding Customer in the class Hash
     * Table table.
     *
     * @param year year of the file to be uploaded.
     * @param type pass 1 for distribution or 2 for redeemed
     * @Exception invalid type of transaction.
     */
    private static void readInTransactions(int year, int type) throws Exception {
        String fileName = "Distributed " + year + " transactions.csv";
        if (type == 2) {
            fileName = "Redeemed " + year + " transactions.csv";
        } else if (type != 1) {
            throw new Exception("type must be 1 (distributed) or 2 (redeemed)");
        }

        try {
            File file = new File(fileName);
            Scanner in = new Scanner(file);

            in.nextLine(); //remove headers

            while (in.hasNext()) {
                String line = in.nextLine();
                Transaction t = new Transaction(line);
                int id = t.getId();

                Customer c = table.find(id);
                if (c == null) {
                    System.err.println("Customer " + id + " was not located");
                } else if (type == 1) {
                    c.addDistributed(t);
                } else if (type == 2) {
                    c.addRedeemed(t);
                } // end else if
            } // end while in.hasNext

            in.close();

        } catch (FileNotFoundException ex) {
            System.err.println("File " + fileName + " not found.");
        }
    }
    
    
       
    /**
     * This is where your code will go to calculate coupon use for Produce Perks.
     * 
     * For each customer, print number of transactions that distributed coupons and
     * number of transactions that redeemed coupons.
     * 
     * Output Example:
     * Customer    Dis   Red
     * Customer 1   15   12
     * Customer 2    5    0 
     * 
     * Note that all the data has been loaded into the customers in the hash table
     * already. Additionally, customer_keys has all the IDs for all the Customers.
     */
    private static void numberOfCouponsUsed()
    {
        int counter = 1;
        System.out.printf("Customer     %4s%5s\n", "Dis", "Red");
        int totalD =0;
        int totalR =0;
        for (int c : getCustomerIds())
        {
            Customer Cust = table.find(c);
            System.out.printf("Customer %-4d%4d%4d\n", c, Cust.getDistributed().size(), Cust.getRedeemed().size());
            totalD = totalD + Cust.getDistributed().size();
            totalR = totalR + Cust.getRedeemed().size();
        }
        System.out.println("Total Distributed: "+totalD);
        System.out.println("Total Redeemed: "+totalR);
    }
    
    /**
     * This is where your code will go to answer questions for Produce Perks.
     * Note that all the data has been loaded into the customers in the hash table
     * already. Additionally, customer_keys has all the IDs for all the Customers.
     */
    private static void answerQuestion()
    {
        System.out.println("Code to answer your question goes here.");
    }

}
