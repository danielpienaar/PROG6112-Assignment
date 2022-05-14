package question1;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Products {

    //Arraylist storing captured products
    static ArrayList<ReportData> productArr = new ArrayList<>();

    public static void main(String[] args) {
        //Header
        System.out.println("BRIGHT FUTURE TECHNOLOGIES APPLICATION\n**************************************");
        //Start program
        continueOrExit();
    }

    public static void continueOrExit() {
        //Display menu or exit
        Scanner input = new Scanner(System.in);
        System.out.print("Enter (1) to launch menu or any other key to exit \n>> ");
        if (input.next().equals("1")) {
            displayMenu();
        } else {
            exitApplication();
        }
    }

    public static void searchProduct() {
        //Get search input
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the product code to search >> ");
        String search = input.nextLine();
        //Check if product exists
        boolean found = false;
        for (ReportData rd : productArr) {
            if (search.equals(rd.getCode())) {
                found = true;
                //Display search results
                System.out.println("**********************"
                        + "\nPRODUCT SEARCH RESULTS"
                        + "\n**********************");
                rd.displayReport();
                System.out.println("**********************");
            }
        }
        if (!found) {
            System.out.println("The specified product can not be found.");
        }

        continueOrExit();
    }

    //Method not needed in final implementation
    //public static void saveProduct() {

    //}

    public static void updateProduct() {
        String selection;
        boolean valid = false;
        String clear;
        //Get search input
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the product code to update >> ");
        String update = input.nextLine();
        //Check if product exists
        boolean found = false;
        for (ReportData rd : productArr) {
            if (update.equals(rd.getCode())) {
                found = true;
                System.out.println("Updating product: " + rd.getName());
                //Ask user for updates
                //Update warranty
                while (!valid) {
                    System.out.print("Update the warranty? y/n\n>> ");
                    selection = input.nextLine();
                    if (selection.equalsIgnoreCase("y")) {
                        System.out.print("Enter the new warranty for " + rd.getName() + ". Enter (1) for 6 months or any other key for 2 years:\n>> ");
                        String warranty = input.nextLine();
                        rd.setWarranty(warranty);
                        valid = true;
                    } else if (selection.equalsIgnoreCase("n")) {
                        valid = true;
                    } else {
                        System.out.println("Please enter either (y) or (n).");
                    }
                }
                //Update price
                valid = false;
                while (!valid) {
                    System.out.print("Update the price? y/n\n>> ");
                    selection = input.nextLine();
                    if (selection.equalsIgnoreCase("y")) {
                        System.out.print("Enter the new price for " + rd.getName() + ":\n>> ");
                        //This try catch block handles errors that arise from the user not entering a double for the price
                        //The try catch implementations in this program were adapted from:
                        //https://www.w3schools.com/java/java_try_catch.asp
                        //Accessed 19 September 2021
                        try {
                            double price = input.nextDouble();
                            rd.setPrice(price);
                            valid = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter a valid price (numbers and decimals only).");
                            valid = false;
                        }
                        clear = input.nextLine();
                    } else if (selection.equalsIgnoreCase("n")) {
                        valid = true;
                    } else {
                        System.out.println("Please enter either (y) or (n).");
                    }
                }
                //Update stock level
                valid = false;
                while (!valid) {
                    System.out.print("Update the stock level? y/n\n>> ");
                    selection = input.nextLine();
                    if (selection.equalsIgnoreCase("y")) {
                        System.out.print("Enter the new stock level for " + rd.getName() + ":\n>> ");
                        try {
                            int stock = input.nextInt();
                            if (validateStock(stock)) {
                                rd.setStock(stock);
                                valid = true;
                            } else {
                                valid = false;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter a valid stock level (numbers only).");
                            valid = false;
                        }
                        clear = input.nextLine();
                    } else if (selection.equalsIgnoreCase("n")) {
                        valid = true;
                    } else {
                        System.out.println("Please enter either (y) or (n).");
                    }
                }
            }
        }
        if (!found) {
            System.out.println("The specified product can not be found.");
        }

        continueOrExit();
    }

    public static void deleteProduct() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the product to delete >> ");
        String delete = input.nextLine();
        //Check if product exists
        boolean found = false;
        //Instead of iterating over the Arraylist using a foreach loop, I use a regular for loop to avoid a ConcurrentModificationException
        //Explanation adapted from:
        //https://www.javatpoint.com/concurrentmodificationexception-in-java
        //Accessed 19 September 2021
        for (int i = 0; i < productArr.size(); i++) {
            if (delete.equals(productArr.get(i).getCode())) {
                found = true;
                System.out.print("Deleting product: " + productArr.get(i).getName()
                        + "\nAre you sure? Enter (y) to delete or any other key to cancel.\n>> ");
                String confirm = input.nextLine();
                if (confirm.equalsIgnoreCase("y")) {
                    productArr.remove(i);
                    System.out.println("Product successfully deleted.");
                    break;
                } else {
                    System.out.println("Cancelling operation.");
                }
            }
        }
        if (!found) {
            System.out.println("The specified product can not be found.");
        }
        
        continueOrExit();
    }

    public static void displayMenu() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please select one of the following menu items:"
                + "\n(1) Capture a new product."
                + "\n(2) Search for a product."
                + "\n(3) Update a product."
                + "\n(4) Delete a product."
                + "\n(5) Print report."
                + "\n(6) Exit application."
                + "\n>> ");
        //Call requested method
        String selection = input.next();
        switch (selection) {
            case "1":
                captureProduct();
                break;
            case "2":
                searchProduct();
                break;
            case "3":
                updateProduct();
                break;
            case "4":
                deleteProduct();
                break;
            case "5":
                printReport();
                break;
            case "6":
                exitApplication();
                break;
            default:
                //If selection is invalid, inform user and display menu again
                System.out.println("Invalid option entered.\n");
                displayMenu();
        }
    }
    
    public static void printReport() {
        System.out.println("\nPRODUCT REPORT"
                + "\n==========================================================");
        //Print details of each product in the arraylist
        int count = 0;
        double value = 0;
        for (ReportData rd : productArr) {
            System.out.println("PRODUCT " + (count + 1)
            + "\n----------------------------------------------------------");
            rd.displayReport();
            System.out.println("----------------------------------------------------------");
            value += rd.getPrice();
            count++;
        }
        //Calculate average value
        double average = value / count;
        //Print product statistics
        System.out.println("=========================================================="
                + "\nTOTAL PRODUCT COUNT:\t" + count
                + "\nTOTAL PRODUCT VALUE:\tR" + ReportData.df.format(value)
                + "\nAVERAGE PRODUCT VALUE:\tR" + ReportData.df.format(average)
                + "\n==========================================================");
        
        continueOrExit();
    }

    public static void captureProduct() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nCAPTURE A NEW PRODUCT"
                + "\n*********************");
        //Capture code
        boolean validCode = false;
        String code = "";
        while (!validCode) {
            System.out.print("Enter the product code >> ");
            code = input.nextLine();
            validCode = validateCode(code);
        }
        //Capture name
        System.out.print("Enter the product name >> ");
        String name = input.nextLine();
        //Capture category
        boolean validCategory = false;
        String category = "";
        while (!validCategory) {
            System.out.print("Enter the product category:"
                    + "\n(1) Desktop computer"
                    + "\n(2) Laptop"
                    + "\n(3) Tablet"
                    + "\n(4) Printer"
                    + "\n(5) Gaming console"
                    + "\n >> ");
            category = input.nextLine();
            validCategory = validateCategory(category);
        }
        //Capture warranty
        System.out.print("Indicate the product warranty. Enter (1) for 6 months or any other key for 2 years:\n >> ");
        String warranty = input.nextLine();
        //Capture price
        String clear;
        boolean validPrice = false;
        double price = 0;
        while (!validPrice) {
            try {
                System.out.print("Enter the price for " + name + " >> ");
                price = input.nextDouble();
                validPrice = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid price (numbers and decimals only).");
                //Advances scanner past invalid input to prevent an infinite loop
                //Logic adapted from:
                //https://stackoverflow.com/questions/1794281/java-infinite-loop-using-scanner-in-hasnextint
                //User answered:
                //https://stackoverflow.com/users/276101/polygenelubricants
                //Accessed 19 September 2021
                clear = input.nextLine();
                validPrice = false;
            }
        }
        clear = input.nextLine();
        //Capture stock level
        boolean validStock = false;
        int stock = 0;
        while (!validStock) {
            try {
                System.out.print("Enter the stock level for " + name + " >> ");
                stock = input.nextInt();
                validStock = validateStock(stock);
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid stock level (numbers only).");
                //Advances scanner past invalid input to prevent an infinite loop
                //Logic adapted from:
                //https://stackoverflow.com/questions/1794281/java-infinite-loop-using-scanner-in-hasnextint
                //User answered:
                //https://stackoverflow.com/users/276101/polygenelubricants
                //Accessed 19 September 2021
                clear = input.nextLine();
                validStock = false;
            }
        }
        clear = input.nextLine();
        //Capture supplier
        System.out.print("Enter the supplier for " + name + " >> ");
        String supplier = input.nextLine();

        //Create new ReportData object with captured data and add it to the array
        ReportData rd = new ReportData(code, name, warranty, category, price, stock, supplier);
        System.out.println("Product details successfully saved.");
        productArr.add(rd);

        continueOrExit();
    }

    //Returns true or false depending on whether or not the code has a duplicate
    public static boolean validateCode(String c) {
        for (ReportData prod : productArr) {
            if (c.equals(prod.getCode())) {
                System.out.println("Code cannot be duplicate.");
                return false;
            }
        }
        return true;
    }

    //Returns true if the category is a number from 1 to 5
    public static boolean validateCategory(String c) {
        //Return false if not number
        int category;
        try {
            category = Integer.parseInt(c);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number.");
            return false;
        }
        //Check if valid number
        if (category < 1 || category > 5) {
            System.out.println("Please enter a number from 1 to 5.");
            return false;
        }
        return true;
    }

    //Checks if entered stock is greater than or equal to 0
    public static boolean validateStock(int s) {
        if (s < 0) {
            System.out.println("Stock cannot be lower than 0.");
            return false;
        }
        return true;
    }

    public static void exitApplication() {
        System.out.println("\nThank you for using the application.");
        System.exit(0);
    }

}
