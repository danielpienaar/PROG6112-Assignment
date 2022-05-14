package question2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SchoolManagement {

    static String separator = "--------------------------------------------------";
    static Scanner input = new Scanner(System.in);
    //CLASSES and MEMBERS variables. The capacity limit for the school is by default 5 CLASSES and 15 MEMBERS per class. Change here if necessary.
    static final int CLASSES = 5;
    static final int MEMBERS = 15;
    static Person[][] classTable = new Person[MEMBERS][CLASSES];
    //Tracks sizes of the 5 CLASSES. Initialized to 1, because staff space is reserved.
    static int[] classSizes = {1, 1, 1, 1, 1};

    public static void main(String[] args) {
        //Console header
        System.out.println(separator
                + "\nWelcome to the IT Coding School management program"
                + "\n" + separator + "\n");
        displayMenu();
    }

    public static void displayMenu() {
        //Show menu
        System.out.print(separator
                + "\n(1) Add a person"
                + "\n(2) Search for a person's details"
                + "\n(3) Update a person's details"
                + "\n(4) Delete a person"
                + "\n(5) Display school class report"
                + "\n(6) Exit\n>> ");
        String selection = input.nextLine();
        System.out.println(separator);
        //Call requested method
        switch (selection) {
            case "1":
                addPerson();
                break;
            case "2":
                searchPerson();
                break;
            case "3":
                updatePerson();
                break;
            case "4":
                deletePerson();
                break;
            case "5":
                printClassTable();
                break;
            case "6":
                System.out.println("\n" + separator + "\nThank you for using. Exiting program.\n" + separator);
                System.exit(0);
                break;
            default:
                System.out.println("Invalid selection.");
                displayMenu();
        }
    }

    public static String generateID(String personType) {
        String id = "";
        boolean unique = false;
        while (!unique) {
            //Append person type
            if (personType.equals("1")) {
                id += "STA";
            } else {
                id += "STU";
            }
            //Generate unique 5 digit code
            for (int i = 0; i < 5; i++) {
                int randNum = (int) (Math.random() * (10));
                id += randNum;
            }
            unique = true;
            //Search CLASSES for any duplicate ID
            for (int i = 0; i < MEMBERS; i++) {
                for (int j = 0; j < CLASSES; j++) {
                    try {
                        if (classTable[i][j].getId().equals(id)) {
                            unique = false;
                        }
                    } catch (NullPointerException e) {
                        //If there is no person at the point in the array, do nothing
                    }
                }
            }
        }
        return id;
    }

    public static void printClassTable() {
        System.out.println("\n" + separator);
        //Date header for table
        //Code adapted from:
        //https://www.baeldung.com/java-datetimeformatter
        //Accessed 21 September 2021
        LocalDate myDateObj = LocalDate.now();
        String formattedDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(myDateObj);
        System.out.println("IT CODING SCHOOL CLASS REPORT: " + formattedDate + "\n");
        for (int i = 0; i < MEMBERS; i++) {
            for (int j = 0; j < CLASSES; j++) {
                //Display a . if the space has not yet been filled
                if (classTable[i][j] == null) {
                    //Format the output into a table
                    //Code adapted from:
                    //https://www.javatpoint.com/how-to-print-table-in-java-using-formatter
                    //Accessed 21 September 2021
                    System.out.format("|%1$-30s", ".");
                } else {
                    System.out.format("|%1$-30s", classTable[i][j]);
                }
            }
            //Underline staff
            if (i == 0) {
                System.out.println("");
                for (int j = 0; j < CLASSES; j++) {
                    System.out.format("|%1$-30s", "===");
                }
            }
            System.out.println("");
        }
        //Continue
        System.out.print("\nPress enter to continue\n>> ");
        input.nextLine();
        System.out.println(separator + "\n");

        displayMenu();
    }

    public static void addPerson() {
        System.out.println("\n" + separator);
        //valid variable used to prevent user from continuing with invalid input
        boolean valid = false;
        //Variables to hold input
        String name = "";
        String surname = "";
        String gender = "";
        String id;
        int classNum = 1;
        double salary = 0;
        String title = "";
        double percentage = 0;
        double attendance = 0;
        //Select to add a teacher or student
        System.out.print("(1) Add a staff member"
                + "\n(2) Add a student\n>> ");
        String selection = input.nextLine();
        if (!selection.equals("1") && !selection.equals("2")) {
            System.out.println("Invalid selection.");
            System.out.println(separator);
            addPerson();
        }
        //General data entry
        while (!valid) {
            System.out.print("Enter first name >> ");
            name = input.nextLine();
            if (!name.isBlank()) {
                valid = true;
            } else {
                System.out.println("Name cannot be blank.");
            }
        }
        valid = false;
        while (!valid) {
            System.out.print("Enter surname >> ");
            surname = input.nextLine();
            if (!surname.isBlank()) {
                valid = true;
            } else {
                System.out.println("Name cannot be blank.");
            }
        }
        valid = false;
        while (!valid) {
            System.out.print("Enter gender >> ");
            gender = input.nextLine();
            if (!gender.isBlank()) {
                valid = true;
            } else {
                System.out.println("Gender cannot be blank.");
            }
        }
        valid = false;
        System.out.println("Generating id...");
        id = generateID(selection);
        System.out.println("Done. ID: " + id);
        //Unique data entry
        //Staff
        if (selection.equals("1")) {
            while (!valid) {
                System.out.print("Enter salary >> R");
                try {
                    salary = input.nextDouble();
                    valid = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input.");
                    input.nextLine();
                    valid = false;
                }
            }
            input.nextLine();
            valid = false;
            while (!valid) {
                System.out.print("Enter title (e.g. Mr, Mrs, Miss) >> ");
                title = input.nextLine();
                if (!title.isBlank()) {
                    valid = true;
                } else {
                    System.out.println("Title cannot be blank.");
                }
            }
            valid = false;
            while (!valid) {
                System.out.print("Enter class (1-5) >> ");
                try {
                    classNum = input.nextInt();
                    if (classNum >= 1 && classNum <= 5) {
                        valid = true;
                    } else {
                        System.out.println("Invalid class number.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input.");
                    input.nextLine();
                    valid = false;
                }
            }
            input.nextLine();
            //Add to array
            if (classTable[0][classNum] == null) {
                Staff s = new Staff(name, surname, gender, id, classNum, salary, title);
                classTable[0][classNum - 1] = s;
            } else {
                System.out.print("Class already has an assigned staff member. Would you like to overwrite them? y/n\n>> ");
                String overwrite = input.nextLine();
                if (overwrite.equalsIgnoreCase("y")) {
                    Staff s = new Staff(name, surname, gender, id, classNum, salary, title);
                    classTable[0][classNum - 1] = s;
                } else {
                    System.out.println("Cancelling operation.");
                }
            }
            //Student
        } else {
            while (!valid) {
                System.out.print("Enter percentage >> ");
                try {
                    percentage = input.nextDouble();
                    valid = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input.");
                    input.nextLine();
                    valid = false;
                }
            }
            input.nextLine();
            valid = false;
            while (!valid) {
                System.out.print("Enter attendance percentage >> ");
                try {
                    attendance = input.nextDouble();
                    valid = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input.");
                    input.nextLine();
                    valid = false;
                }
            }
            input.nextLine();
            valid = false;
            while (!valid) {
                System.out.print("Enter class (1-5) >> ");
                try {
                    classNum = input.nextInt();
                    if (classNum >= 1 && classNum <= 5) {
                        valid = true;
                    } else {
                        System.out.println("Invalid class number.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input.");
                    input.nextLine();
                    valid = false;
                }
            }
            input.nextLine();
            //Add to array
            if (classSizes[classNum] < 15) {
                Student s = new Student(name, surname, gender, id, classNum, percentage, attendance);
                classTable[classSizes[classNum]][classNum - 1] = s;
                classSizes[classNum] += 1;
            } else {
                System.out.println("No more space for student's in this class. Consider updating or deleting.");
            }
        }
        //Continue
        System.out.print("\nPress enter to continue\n>> ");
        input.nextLine();
        System.out.println(separator + "\n");

        displayMenu();
    }

    public static void searchPerson() {
        System.out.println("\n" + separator);
        System.out.print("Enter the ID of the person you want to search for >> ");
        String search = input.nextLine();
        boolean found = false;
        //Print report of person if found
        for (int i = 0; i < MEMBERS; i++) {
            for (int j = 0; j < CLASSES; j++) {
                try {
                    if (classTable[i][j].getId().equals(search)) {
                        System.out.println(classTable[i][j].report());
                        found = true;
                        break;
                    }
                } catch (NullPointerException e) {
                    //If null, do nothing
                }
            }
        }
        if (!found) {
            System.out.println("A person with the specified ID could not be found. Try searching the school class report for existing IDs.");
        }
        //Continue
        System.out.print("\nPress enter to continue\n>> ");
        input.nextLine();
        System.out.println(separator + "\n");

        displayMenu();
    }

    public static void updatePerson() {
        System.out.println("\n" + separator);
        System.out.print("Enter the ID of the person you want to update >> ");
        String update = input.nextLine();
        boolean found = false;
        String confirm;
        //Update Person if found
        for (int i = 0; i < MEMBERS; i++) {
            for (int j = 0; j < CLASSES; j++) {
                try {
                    if (classTable[i][j].getId().equals(update)) {
                        //Check if person is staff or student using ID
                        if (classTable[i][j].getId().substring(0, 3).equals("STA")) {
                            System.out.print("Do you want to change " + classTable[i][j].getName() + "'s salary?\nEnter y to confirm or any other key to cancel >> ");
                            confirm = input.nextLine();
                            if (confirm.equalsIgnoreCase("y")) {
                                boolean valid = false;
                                double salary = 0;
                                while (!valid) {
                                    System.out.print("Enter new salary >> R");
                                    try {
                                        salary = input.nextDouble();
                                        valid = true;
                                    } catch (InputMismatchException e) {
                                        System.out.println("Invalid input.");
                                        input.nextLine();
                                        valid = false;
                                    }
                                }
                                input.nextLine();
                                Staff temp;
                                temp = (Staff) classTable[i][j];
                                temp.setSalary(salary);
                                classTable[i][j] = temp;
                                System.out.println("Successfully updated salary.");
                            }
                            System.out.print("Do you want to change " + classTable[i][j].getName() + "'s class?\nEnter y to confirm or any other key to cancel >> ");
                            confirm = input.nextLine();
                            if (confirm.equalsIgnoreCase("y")) {
                                System.out.print("Enter the target class num (1-5) >> ");
                                try {
                                    int classNum = input.nextInt();
                                    if (classNum == classTable[i][j].getClassNum()) {
                                        System.out.println("You entered the same class. Cancelling class update.");
                                    } else if(classNum < 1 || classNum > 5) {
                                        
                                    }else if (classTable[0][classNum] != null) {
                                        System.out.println("Space is already occupied.");
                                    } else {
                                        classTable[i][j].setClassNum(classNum);
                                        classTable[0][classNum-1] = classTable[i][j];
                                        classTable[i][j] = null;
                                        System.out.println("Successfully updated class.");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Invalid class.");
                                }
                                input.nextLine();
                            }
                        } else {
                            System.out.print("Do you want to change " + classTable[i][j].getName() + "'s percentage?\nEnter y to confirm or any other key to cancel >> ");
                            confirm = input.nextLine();
                            if (confirm.equalsIgnoreCase("y")) {
                                boolean valid = false;
                                double percentage = 0;
                                while (!valid) {
                                    System.out.print("Enter new percentage >> ");
                                    try {
                                        percentage = input.nextDouble();
                                        valid = true;
                                    } catch (InputMismatchException e) {
                                        System.out.println("Invalid input.");
                                        input.nextLine();
                                        valid = false;
                                    }
                                }
                                input.nextLine();
                                System.out.println("Percentage: " + percentage);
                                Student temp;
                                temp = (Student) classTable[i][j];
                                temp.setPercentage(percentage);
                                classTable[i][j] = temp;
                                System.out.println("Successfully updated percentage.");
                            }
                            System.out.print("Do you want to change " + classTable[i][j].getName() + "'s attendance?\nEnter y to confirm or any other key to cancel >> ");
                            confirm = input.nextLine();
                            if (confirm.equalsIgnoreCase("y")) {
                                boolean valid = false;
                                double attendance = 0;
                                while (!valid) {
                                    System.out.print("Enter new attendance >> ");
                                    try {
                                        attendance = input.nextDouble();
                                        valid = true;
                                    } catch (InputMismatchException e) {
                                        System.out.println("Invalid input.");
                                        input.nextLine();
                                        valid = false;
                                    }
                                }
                                input.nextLine();
                                System.out.println("Attendance: " + attendance);
                                Student temp;
                                temp = (Student) classTable[i][j];
                                temp.setAttendance(attendance);
                                classTable[i][j] = temp;
                                System.out.println("Successfully updated attendance.");
                            }
                        }
                        found = true;
                        break;
                    }
                } catch (NullPointerException e) {
                    //If null, do nothing
                }
            }
        }
        if (!found) {
            System.out.println("A person with the specified ID could not be found. Try searching the school class report for existing IDs.");
        }
        //Continue
        System.out.print("\nPress enter to continue\n>> ");
        input.nextLine();
        System.out.println(separator + "\n");

        displayMenu();
    }

    public static void deletePerson() {
        System.out.println("\n" + separator);
        System.out.print("Enter the ID of the person you want to delete >> ");
        String delete = input.nextLine();
        boolean found = false;
        //Confirm deletion of person if found
        for (int i = 0; i < MEMBERS; i++) {
            for (int j = 0; j < CLASSES; j++) {
                try {
                    if (classTable[i][j].getId().equals(delete)) {
                        System.out.print("Are you sure you want to delete " + classTable[i][j].getName() + " from Group " + classTable[i][j].getClassNum() + "?"
                                + "\nEnter y to confirm or any other key to cancel >> ");
                        String sure = input.nextLine();
                        if (sure.equalsIgnoreCase("y")) {
                            classTable[i][j] = null;
                            //Shift any other students back in the array
                            for (int k = i; k < MEMBERS - 1; k++) {
                                classTable[k][j] = classTable[k + 1][j];
                            }
                            System.out.println("Successfully deleted.");
                        } else {
                            System.out.println("Canceled.");
                        }
                        found = true;
                        break;
                    }
                } catch (NullPointerException e) {
                    //If null, do nothing
                }
            }
        }
        if (!found) {
            System.out.println("A person with the specified ID could not be found. Try searching the school class report for existing IDs.");
        }
        //Continue
        System.out.print("\nPress enter to continue\n>> ");
        input.nextLine();
        System.out.println(separator + "\n");

        displayMenu();
    }

}
