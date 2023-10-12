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

public class Driver{

    private static String[] Category;
    private static MenuNode[] MenuVar;

    private static StockNode[] StockVar;
    public static void main(String[] args) {
        RUHungry restaurant = new RUHungry();
        String[] methodsToTest = {"Menu", "StockRoom", "Transactions","SeatAllGuests", "Quit"};
        String[] moreMethods = {"order", "donate", "restock"};
        int controlChoice = 1;
        int choice = 0;

        do {
            restaurant.resetFrontNode();
            StdOut.println("\nWhat method would you like to test?");

            for (int i = 0; i < methodsToTest.length; i++) {
                StdOut.printf("%d. %s\n", i+1, methodsToTest[i]);
            }
            
            StdOut.print("Enter a number => ");
            choice = Integer.parseInt(StdIn.readLine());
            switch (choice) {
                case 1:
                // Menu
                    StdOut.print("Slay! Enter the menu input file [there's only one ;) ] => ");
                    String menuInputFile = StdIn.readLine();
                    StdOut.println();

                    testMenu(menuInputFile, restaurant);
                    break;
                case 2:
                // Stock Room
                    MenuVar = restaurant.Menu("menu.in");
                    Category = restaurant.getCategoryArray();
                    StdIn.resetFile();

                    StdOut.print("Okie Dokie! Enter the stock input file [there's only one ;) ] => ");
                    String stockInputFile = StdIn.readLine();
                    StdOut.println();
                    
                    testStockHashtable(stockInputFile, restaurant);
                    break;
                case 3:
                // Transactions
                    MenuVar = restaurant.Menu("menu.in");
                    Category = restaurant.getCategoryArray();
                    StdIn.resetFile();
                    StockVar = restaurant.createStockHashTable("stock.in");
                    restaurant.updatePriceAndProfit();
                    StdIn.resetFile();

                    StdOut.print("Quick Question! Do you want to test an individual transaction method (order, donation, restock)? [ Y OR N ] => ");
                    String yesORno = StdIn.readLine();
                    if (yesORno.equalsIgnoreCase("Y")) {

                        StdOut.println("Alrighty! Which one would you like to test?"); // we can make separate input files for peeps who want to test each individual method
                        for (int i = 0; i < moreMethods.length; i++) {
                            StdOut.printf("%d. %s\n", i+1, moreMethods[i]);
                        }
                        StdOut.print("Enter a number => ");
                        int secondChoice = Integer.parseInt(StdIn.readLine());
                        
                        switch (secondChoice) {
                            case 1:
                                StdOut.print("Slay! Enter an order input file => ");
                                String orderInputFile = StdIn.readLine();

                                testIndividualMethods(orderInputFile, "order", restaurant);
                                break;
                            case 2:
                                StdOut.print("Slay Queen (or King)! Enter a donation input file => ");
                                String donationInputFile = StdIn.readLine();

                                testIndividualMethods(donationInputFile, "donation", restaurant);
                                break;
                            case 3:
                                StdOut.print("Awesome! Enter a restock input file => ");
                                String restockInputFile = StdIn.readLine();

                                testIndividualMethods(restockInputFile, "restock", restaurant);
                                break;
                            default:
                                StdOut.print("Omg, that isn't a valid option but no worries, try again! <3");
                        }
                    }
                    else if (yesORno.equalsIgnoreCase("N")) {
                        StdOut.print("Okay lit! Enter a transaction input file => ");
                        String transactionInputFile = StdIn.readLine();

                        testTransactionMethods(transactionInputFile, restaurant);
                    }
                    else {
                        StdOut.println("Omg, that isn't a valid option but no worries, try again! <3");
                    }
                    break;
                case 4:
                // seatAllGuests
                    StdOut.print("Slay! Enter a table input file => ");
                    String tableInputFile = StdIn.readLine();
                    StdOut.print("Okay Perfect! Now enter a seat guests input file => ");
                    String seatGuestsInputFile = StdIn.readLine();
                    StdOut.println();

                    testSeatAllGuests(seatGuestsInputFile, tableInputFile, restaurant);
                    StdOut.println();
                    break;
                case 5:
                // Quit
                    controlChoice = 2;
                    break;
                default:
                    StdOut.println("Omg, that isn't a valid option but no worries, try again! <3");
            }
            StdIn.resetFile();
        } while (controlChoice == 1);
    }

    /***********************************************/

    public static void testMenu (String inputFile, RUHungry restaurant) {
        MenuVar = restaurant.Menu(inputFile);
        Category = restaurant.getCategoryArray();
        StdOut.println();

        for (int i=0; i < Category.length; i++) {
            StdOut.print(Category[i] + ":");
            StdOut.println();

            MenuNode ptr = MenuVar[i];
            while (ptr != null) {
                StdOut.print(ptr.getDish().getDishName() + "  Price: $" +
                ((Math.round(ptr.getDish().getPriceOfDish() * 100.0)) / 100.0) + " Profit: $" + ((Math.round(ptr.getDish().getProfit() * 100.0)) / 100.0));
                StdOut.println();

                ptr = ptr.getNextMenuNode();
            }

            StdOut.println();
        }
    }

    public static void testStockHashtable (String inputFile, RUHungry restaurant) {
        StockVar = restaurant.createStockHashTable(inputFile);
        restaurant.updatePriceAndProfit();

        for (int i=0; i < 10; i++) {
            StdOut.println("index " + i);
            StockNode ptr = StockVar[i];
            while (ptr != null) {
                StdOut.print(ptr.getIngredient().getName() + "  ID: " + ptr.getIngredient().getID() + " Price: " +
                ((Math.round(ptr.getIngredient().getPriceToPurchase() *100.0)) / 100.0) + " Stock Level: " + ptr.getIngredient().getStockLevel());
                StdOut.println();

                ptr = ptr.getNextStockNode();
            }

            StdOut.println();
        }

        StdOut.println("Here's the menu again! Make sure to check price and profit:");
        StdOut.println();


        for (int i=0; i < Category.length; i++) {
            StdOut.print(Category[i] + ":");
            StdOut.println();

            MenuNode ptr = MenuVar[i];
            while (ptr != null) {
                StdOut.print(ptr.getDish().getDishName() + "  Price: $" +
                ((Math.round(ptr.getDish().getPriceOfDish() * 100.0)) / 100.0) + " Profit: $" + ((Math.round(ptr.getDish().getProfit() * 100.0)) / 100.0));
                StdOut.println();

                ptr = ptr.getNextMenuNode();
            }

            StdOut.println();
        }
    }

    // the methods to test transaction methods create a simple TransactionNode and adds it to the transaction LL 
    // so that you will be able to run these methods
    
    public static void testIndividualMethods(String inputOrdersFile, String transactionType, RUHungry restaurant){
        restaurant.createStockHashTable("stock.in");
        restaurant.Menu("menu.in");
        restaurant.updatePriceAndProfit();

        StdIn.setFile(inputOrdersFile);

        int numOrders = StdIn.readInt();
        if (transactionType.equalsIgnoreCase("order")){
            for (int orderNumber = 0; orderNumber < numOrders; orderNumber ++){
                int amountOrdered = StdIn.readInt();
                StdIn.readChar();
                String itemOrdered = StdIn.readLine();
                // call orderMethod from RUHungry
                restaurant.orderMethod(itemOrdered, amountOrdered);
            }
        }
        else if (transactionType.equalsIgnoreCase("donation")){
            restaurant.addToTransactionLL(new TransactionData("N/A", "N/A",0, 112, false));
            for (int orderNumber = 0; orderNumber < numOrders; orderNumber ++){
                int amountOrdered = StdIn.readInt();
                StdIn.readChar();
                String itemOrdered = StdIn.readLine();
                // call donation Method from RUHungry
                restaurant.donationMethod(itemOrdered, amountOrdered);
            }
        }
        else if (transactionType.equalsIgnoreCase("restock")){
            restaurant.addToTransactionLL(new TransactionData("N/A", "N/A",0, 112, false));
            for (int orderNumber = 0; orderNumber < numOrders; orderNumber ++){
                int amountOrdered = StdIn.readInt();
                StdIn.readChar();
                String itemOrdered = StdIn.readLine();
                // call restock method from RUHungry
                restaurant.restockMethod(itemOrdered, amountOrdered);
            }
        }

        TransactionNode ptr = restaurant.getFrontTransactionNode();
        int successes = 0;
        int failures = 0;
        while (ptr != null) {
            String type = ptr.getData().getType();
            String item = ptr.getData().getItem();
            int amount = ptr.getData().getAmount();
            double profit = ptr.getData().getProfit();
            boolean success = ptr.getData().getSuccess();
            if (success == true){
                successes += 1;
            }
            else if (success == false){
                failures += 1;
            }

            StdOut.println("Type: " + type + ", Name: " + item + ", Amount: " + amount + ", Profit: $" + ((Math.round(profit * 100.0)) / 100.0) + ", Was it a Success? " + success);
            
            ptr = ptr.getNext();
        }
        StdOut.println("Total number of successful transactions: " + successes);
        StdOut.println("Total number of unsuccessful transactions: " + failures);
        StdOut.println("Total profit remaining: $" + ((Math.round(restaurant.profit(restaurant.getFrontTransactionNode()) * 100.0)) / 100.0));
    }

    public static void testTransactionMethods(String inputOrdersFile, RUHungry restaurant){
        restaurant.createStockHashTable("stock.in");
        restaurant.Menu("menu.in");
        restaurant.updatePriceAndProfit();

        StdIn.setFile(inputOrdersFile);

        int numOrders = StdIn.readInt(); // reads how orders there are
        for (int orderNumber = 0; orderNumber < numOrders; orderNumber ++){
            String type = StdIn.readString(); // can be either "order" "donation" "restock"
            StdIn.readChar(); // empty
            int amountOrdered = StdIn.readInt(); // reads how much was ordered/restocked
            StdIn.readChar();
            String itemOrdered = StdIn.readLine(); // reads what item was ordered/requested/delivered (for donations and deliveries we must Parse string to int)
            if (type.equalsIgnoreCase("order")){
                // call orderMethod from RUHungry
                restaurant.orderMethod(itemOrdered, amountOrdered);
            }

            else if (type.equalsIgnoreCase("donation")){
                // call donation Method from RUHungry
                restaurant.donationMethod(itemOrdered, amountOrdered);
            }

            else if (type.equalsIgnoreCase("restock")){
                // call restock method from RUHungry
                restaurant.restockMethod(itemOrdered, amountOrdered);
            }

        }

        TransactionNode ptr = restaurant.getFrontTransactionNode();
        int successes = 0;
        int failures = 0;
        while (ptr != null) {
            String type = ptr.getData().getType();
            String item = ptr.getData().getItem();
            int amount = ptr.getData().getAmount();
            double profit = ptr.getData().getProfit();
            boolean success = ptr.getData().getSuccess();
            if (success == true){
                successes += 1;
            }
            else if (success == false){
                failures += 1;
            }

            StdOut.println("Type: " + type + ", Name: " + item + ", Amount: " + amount + ", Profit: $" + ((Math.round(profit* 100.0)) / 100.0) + ", Was it a Success? " + success);
        
            ptr = ptr.getNext();
        }
        StdOut.println("Total number of successful transactions: " + successes);
        StdOut.println("Total number of unsuccessful transactions: " + failures);
        StdOut.println("Total profit remaining: $" + ((Math.round(restaurant.profit(restaurant.getFrontTransactionNode()) * 100.0)) / 100.0));
    }

    public static void testSeatAllGuests (String inputSeatFile, String inputTableFile, RUHungry restaurant) {
        restaurant.createTables(inputTableFile);

        StdIn.setFile(inputSeatFile);
        int num = StdIn.readInt();
        Queue<PeopleObject> seatingQueueVar = new LinkedList<PeopleObject>();
        for (int i=0; i < num; i++) {
            int numberInParty = StdIn.readInt();
            String nameOfParty = StdIn.readString();
            PeopleObject newObject = new PeopleObject(numberInParty, nameOfParty, 0);
            seatingQueueVar.add(newObject);
        }

        Queue<PeopleObject> leftQueue = restaurant.seatAllGuests(seatingQueueVar);
        StdOut.println(("In order of leaving"));
        int counter = 0;
        while (!leftQueue.isEmpty()) {
            PeopleObject removed = leftQueue.remove();
            counter += 1;
            if (leftQueue.peek() != null){
                StdOut.println(counter + ": " + removed.getNameOfParty());
            }
            else if (leftQueue.peek() == null){
                StdOut.println(counter + ": " + removed.getNameOfParty());
            }
        }
    }
}