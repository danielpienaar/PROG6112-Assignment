package question2;


public class Staff extends Person{
    
    private double salary;
    private String title;
    
    public Staff(String name, String surname, String gender, String id, int classNum, double salary, String title) {
        super(name, surname, gender, id, classNum);
        
        this.salary = salary;
        setTitle(title);
    }

    
    @Override
    public String toString() {
        return getId() + ": " + getTitle() + getSurname();
    }
    
    @Override
    public String report() {
        String report = getTitle() + " " + getName() + " " + getSurname() + " - Staff"
                + "\n==="
                + "\nGENDER:\t\t\t" + getGender()
                + "\nID:\t\t\t" + getId()
                + "\nCLASS:\t\t\tGroup " + getClassNum()
                + "\nSALARY:\t\t\tR" + getSalary();
        return report;
    }
    
    //Getters
    
    public double getSalary() {
        return salary;
    }

    public String getTitle() {
        return title;
    }
    
    //Setters
    
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public final void setTitle(String title) {
        //Perform some validation on the title, and prevent duplicate "."
        int end = title.indexOf(".");
        String validTitle;
        if (end != -1) {
            validTitle = title.substring(0, end + 1);
            this.title = validTitle;
        } else {
            this.title = title + ".";
        }
    }
    
}
