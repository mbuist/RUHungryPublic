package ruhungry;
import java.util.*;


/**
 * To read from a file use StdIn.setFile(inputfilename)
 * To write to a file use StdOut.setFile(outputfilename)
 * 
 * Compiling and executing:
 * 1. use the run or debug function to run the driver and test your methods 
 * 
 * @author Mary Buist
 * @author Kushi Sharma
*/

public class RUHungry{
    
    // private global arrays for seat Menu and getter methods
    private MenuNode[] MenuVar; // array full of MenuNodes where each index is a category
    public MenuNode[] getMenu() { return MenuVar; }
    
    private String[] CategoryVar; // array where each index contains the name of the category (parallel array)
    public String[] getCategoryArray() { return CategoryVar;}

    // private global array for Stock and getter methods
    private StockNode[] StockVar = new StockNode[10]; // array full of StockNodes (use hashfunction to organize Nodes: id % 10)
    public StockNode[] getStockVar() { return StockVar; } // getter method
 
    // private global frontnode for transactionLL and getter method
    private TransactionNode front; // refers to the first TransactionNode in Transaction linked list
    public TransactionNode getFrontTransactionNode() { return front; } // getter method
    public TransactionNode resetFrontNode() {return front = null;} // method to reset the TransactionLL each time driver is run

    // private global variables for seatGuests and getter methods
    private Queue<PeopleObject> leftQueueVar; // queue for people who've left the restaurant
    public Queue<PeopleObject> getLeftQueueVar() { return leftQueueVar; } // getter method

    private int[][] tables; // 2-D integer array where the first row contains how many seats there are at each table (each index)
                            // and the second row contains "0" or "1", where 1 is the table is not available and 0 the opposite
    public int[][] getTables() { return tables; }

/***********************************************/

    // Menu Methods

    /**
     * PICK UP LINE OF THE METHOD:
     * *I’m pretty StdIn(to you) but I can’t read you well; I wanna double check that 
     * you like me too and aren’t just stringing me along*
     * 
     * *********
     * This method populates MenuVar which is a "hashtable" (not exactly since we won't have a hash function). At each index 
     * (each index represents a category) in this array there is a linked list with MenuNodes.
     * 
     * It ALSO populates CategoryVar which is a side by side array to MenuVar where the index of the array corresponds to the category name.
     * 
     * 1. read the input file:
     *      a) the first number corresponds to the number of categories (aka length of MenuVar)
     *      b) the next line states the name of the category (populate CategoryVar as you read each category name)
     *      c) the next number represents how many dishes are in that category
     *      d) the next line states the name of the dish
     *      e) the first number in the next line represents how many ingredient IDs there are
     *      f) the next few numbers (all in the 100s) are each the ingredient ID
     * 
     * 2. As you read through the input file:
     *      a) populate the CategoryVar array
     *      b) populate MenuVar depending on which index (aka which category) you are in
     *          i) make a dish object (with filled parameters -- don't worry about "price" and "profit" in the dish object for right now)
     *          ii) create MenuNode and insert into MenuVar (NOTE! there will be multiple MenuNodes in one index)
     * 
     * @param inputFile - menu.in file which contains all the dishes
     * @return MenuNode[] named MenuVar
     */

                        /* YOUR CODE HERE */

    /** 
     * Find a specific dish in the MenuVar "hashtable" and returns the data of that dish (aka the dish object)
     * 
     *      ** GIVEN METHOD **
     *      ** DO NOT EDIT **
     * 
     * @param dishName - the name of the dish
     * @return the dish object corresponding to searched dish
     */

    public MenuNode findDish (String dishName) {
        MenuNode menuNode = null;
        for (int categoryInMenu = 0; categoryInMenu < MenuVar.length; categoryInMenu++){
            MenuNode ptr = MenuVar[categoryInMenu]; // set ptr as first menuNode
            while (ptr != null){ // while loop that searches the LL of the category to find the itemOrdered
                if (ptr.getDish().getDishName().equals(dishName)){
                    menuNode = ptr;
                    break;
                }
                else{
                    ptr = ptr.getNextMenuNode();
                }
            }
        }
        return menuNode;
    }

    /**
     * Find integer that corresponds to the index in MenuVar and category array that has that category
     *              
     *      ** GIVEN METHOD **
     *      ** DO NOT EDIT **
     *
     * @param category - the category name
     * @return integer
     */

    public int findCategoryIndex (String category){
        int index = 0;
        for (int i = 0; i < CategoryVar.length; i ++){
            if (category.equalsIgnoreCase(CategoryVar[i])){
                index = i;
                break;
            }
        }
        return index;
    }

/***********************************************/

    // STOCKROOM METHODS

    /**
     * PICK UP LINE OF THE METHOD:
     * *can I insert myself into your life? cuz you always help me sort 
     * out my problems and bring stability to my mine*
     * 
     * ***********
     * This method adds a StockNode into the StockVar hashtable.
     * 
     * 1. get the id of the given newNode and use a hash function to get the index at which the
     *    newNode is being inserted.
     * 
     * RECOMMENDED HASH FUNCTION: id % 10
     * 
     * 2. insert at the front of the linked list at the specific index
     * 
     * @param newNode - StockNode that needs to be inserted into StockVar
     * @return void
     */

                        /* YOU CODE HERE */

    /**
     * This method deletes an ingredient (aka the specific stockNode) from StockVar.
     * 
     * 1. find the node that corresponds to the ingredient (based on the ingredientName)
     * 
     * 2. delete the node from StockVar
     *      a) you have to visit each index and look at each node in the corresponding linked list
     * 
     * @param ingredientName - name of the ingredient
     */

                        /* YOUR CODE HERE */

    /**
     * This method finds an ingredient from StockVar (given the ingredientID)
     * 
     * 1. find the node based upon the ingredient ID (you can go to the specific index using the hash function!)
     * 
     * 2. find and return the node
     * 
     * ASSUME THE ID GIVEN WILL ALWAYS BE FOUND IN THIS METHOD!
     * 
     * @param IngredientID - the ID of the ingredient
     * @return the StockNode corresponding to the ingredient
     */
   
                        /* YOUR CODE HERE */

    /**
     * This method is to find an ingredient from StockVar (given the ingredient name).
     * 
     *      ** GIVEN METHOD **
     *      ** DO NOT EDIT **
     * 
     * @param ingredientName - the name of the ingredient
     * @return the specific ingredient StockNode
     */

    public StockNode findIngredient (String ingredientName) {
        StockNode stockNode = null;
        for (int index = 0; index < StockVar.length; index ++){
            StockNode ptr = StockVar[index];
            while (ptr != null){
                if (ptr.getIngredient().getName().equalsIgnoreCase(ingredientName)){
                    stockNode = ptr;
                    break;
                }
                else {  
                    ptr = ptr.getNextStockNode();
                }
            }
        }
        return stockNode;
    }

    /**
     * This method is to update the stock amount of an ingredient.
     * 
     * 1. you will be given the ingredientName and the ingredientID but ONE of them will be null:
     *      a) the ingredientName is NOT null: find the ingredient and add the given stock amount to the
     *         current stock amount
     *      b) the ingredientID is NOT null: find the ingredient and add the given stock amount to the
     *         current stock amount
     * 
     * (FOR FUTURE USE) SOMETIMES THE STOCK AMOUNT TO ADD MAY BE NEGATIVE (TO REMOVE STOCK)
     * 
     * @param ingredientName - the name of the ingredient
     * @param ingredientID - the id of the ingredient
     * @param stockAmountToAdd - the amount to add to the current stock amount
     * @return void
     */
    
                        /* YOUR CODE HERE */

    /**
     * PICK UP LINE OF THE METHOD:
     * *are you a single ‘for’ loop? cuz i only have i’s for you*
     * 
     * ***********
     * This method now goes back to MenuVar to update the price and profit of each dish,
     * using the stockVar hashtable and menu "hashtable".
     * 
     * 1. For each dish in menuVar, add up the priceToPurchase for each ingredient (found in stockVar), 
     *    and multiply the total by 1.2 to get the final price
     *      a) update the price of each dish
     *  HINT! --> you can use the methods you've previously made!
     * 
     * 2. Calculate the profit of each dish by getting the totalPrice of ingredients and subtracting from 
     *    the price of the dish itself.
     * 
     * @return void
     */

                        /* YOUR CODE HERE */

    /**
     * PICK UP LINE OF THE METHOD:
     * *are you a decimal? cuz the thought of you 
     * always floats in my head and the two of use would make double*
     * 
     * ************
     * This method populates StockVar which is a hashtable where each index contains a 
     * linked list with StockNodes.
     * 
     * 1. set and read the inputFile (stock.in):
     *      a) first integer (on line 1) is the size of StockVar
     *      b) first integer of next line represents the ingredientID
     *          i) example: 101 on line 2
     *      c) use StdIn.readChar() to get rid of the space between the id and the name
     *      d) the string that follows is the ingredient name (NOTE! --> there are spaces between certain strings)
     *          i) example: Lettuce
     *      e) the double on the next line corresponds to the priceToPurchase the ingredient
     *          i) example: 3.12 on line 3
     *      f) the next integer is the stock amount for that ingredient
     *          i) example: 30 on line 3
     * 
     * 2. create a Ingredient object followed by a StockNode then add to StockVar
     *      HINT! --> you may use previous methods written to help you!
     * 
     * @param inputFile - the input file with the ingredients and all their information (stock.in)
     * @return the StockVar hashtable filled with Linked Lists of StockNodes
     */

                        /* YOUR CODE HERE */

/***********************************************/

    // TRANSACTION METHODS

    /**
     * This method adds a Transaction Node to the Transaction Linked List
     * 
     * 1. base case checks if the LL is empty
     *
     * 2. create a new Transaction Node with the TransactionData and add at the end of the linked list
     * 
     * @param data - TransactionData node to be added to Transaction LL
     * @return void
     */

                        /* YOUR CODE HERE */

    /**
     * PICK UP LINE OF THE METHOD:
     * *are you the break command? cuz everything else stops when I see you*
     * 
     * *************
     * This method checks if there's enough stock level for the reqested dish
     * 
     * 1. use findDish() method to find the MenuNode node for itemOrdered
     * 
     * 2. traverse ingredient array within the dish
     * 
     * 3. return boolean based on whether you can sell the dish or not
     * HINT! --> once you determine you can't sell the dish, break and return
     * 
     * @param itemOrdered - String of dish that's being requested
     * @param amountOrdered - int of how many of that dish is being ordered
     * @return boolean
     */

                        /* YOUR CODE HERE */

    /**
     * PICK UP LINE OF THE METHOD:
     * *if you were a while loop and I were a boolean, we could run 
     * together forever because I’ll always stay true to you*
     * 
     * ***************
     * This method simulates the ordering and selling of a dish
     * 
     * 1. suggested: intialize a counter, MenuNode for requestedDish, MenuNode of the first dish in the category and int categorySize
     * 
     * 2. do while loop
     *      a) update categorySize
     *      b) continue to check whether the requested dish has enough ingredients by calling the check method
     *      c) add all transactions to TransactionLL (SOMETIMES THE STOCK AMOUNT TO ADD MAY BE NEGATIVE (TO REMOVE STOCK))
     *      HINT! --> if the check fails, find the next dish in the same category and continue to check until you have a successful sale
     *          or you've traversed the entire category
     * 
     * @param itemOrdered - String of dish that's been ordered
     * @param amountOrdered - int of how many of that dish has been ordered
     * @return void
     */

                        /* YOUR CODE HERE */

    /**
     * This method a returns the total profit for the day
     * 
     * 1. traverses the TransactionLL adding up all the profits for the day
     * 
     * @param transactionNode - TransactionNode that is the front of the TransactionLL
     * @return profit - double value of the total profit for the day
     */

                        /* YOUR CODE HERE */

    /**
     * This method simulates donations and donation requests
     * 
     * 1. check whether the profit is > 50 and whether there's enough ingredients in stock
     * 
     * 2. add all transactions to TransactionLL
     * 
     * @param itemOrdered - String of ingredient that's been requested
     * @param amountOrdered - int of how many of that ingredient has been ordered
     * @return void
     */

                        /* YOUR CODE HERE */

    /**
     * This method simulates restock orders
     * 
     * 1. check whether the profit is sufficient to pay for the total cost of ingredient
     *      a) (how much each ingredient is) * (the amount ordered)
     *      b) if there is enough profit, adjust stock and profit accordingly
     * 
     * 2. add transaction to TransactionLL
     * 
     * @param itemOrdered - String of ingredient that's been requested
     * @param amountOrdered - int of how many of that ingredient has been ordered
     * @return void
     */

                        /* YOUR CODE HERE */

    /*******************************/

    // SEAT GUESTS METHODS

    /**
     * Method to populate tables (which is a 2d integer array) based upon input file
     * 
     * First row of tables[][]: contains the total number of seats available at a table (each table is an index in first row)
     * Second row of tables[][]: initializes all indices to 0
     *      0 --> table is available
     *      1 --> table is occupied
     * 
     *          ** GIVEN METHOD **
     *          ** DO NOT EDIT **
     * 
     * @param inputFile - tables1.in (contains all the tables in the RUHungry restaurant)
     * @return void (aka nothing)
     */

    public void createTables(String inputFile){
        StdIn.setFile(inputFile);
        int numberOfTables = StdIn.readInt();
        tables = new int[2][numberOfTables];
        for (int tableNumber = 0; tableNumber < numberOfTables; tableNumber ++){
            tables[0][tableNumber] = StdIn.readInt() * StdIn.readInt();
        }
    }

    /**
     * PICK UP LINE OF THE METHOD:
     * *are you a linked list? cuz nothing could stock up to you and 
     * you’re pretty queue(te)*
     * 
     * ***************
     * This method simulates seating guests at tables. You are guaranteed to be able to sit everyone from the seatingQueue eventually.
     * 
     * 1. initialize a PeopleObject array for people that are currently sitting
     * 
     * 2. create a PeopleObject queue that represents the people that have left the restaurant
     * 
     * 3. iterate through the seatingQueueVar AND the tables[][] (2D array) to seat guests
     *      a) if a table has enough seats and it is available, then remove front PeopleObject from queue,
     *         set tableIndex to the table they are sat at, add the PeopleObject to the sittingArray (based on incremented counter),
     *         and mark the table as occupied within the tables[][]
     *      b) if it is the last table in tables[][] and the party has still not been seated, then iterate through sittingArray and kick out party who has been there 
     *         the longest AND has enough seats (so they have now left --> add to left queue), then repeat step a for every party in 
     *         seatingQueueVar
     * 
     * 
     * @param seatingQueueVar - queue containing people waiting to be seated (each element in queue is a PeopleObject <-- you are given this class!)
     * @return leftQueue - a PeopleObject Queue that represents the people that have left the restaurant
     */

                        /* YOUR CODE HERE */
}