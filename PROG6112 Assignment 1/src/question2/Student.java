package question2;


public class Student extends Person{
    
    private double percentage;
    private double attendance;
    
    public Student(String name, String surname, String gender, String id, int classNum, double percentage, double attendance) {
        super(name, surname, gender, id, classNum);
        
        this.percentage = percentage;
        this.attendance = attendance;
    }
    
    @Override
    public String toString() {
        return getId() + ": " + getName() + " " + getSurname();
    }
    
    @Override
    public String report() {
        String report = getName() + " " + getSurname() + " - Student"
                + "\n==="
                + "\nGENDER:\t\t\t" + getGender()
                + "\nID:\t\t\t" + getId()
                + "\nCLASS:\t\t\tGroup " + getClassNum()
                + "\nCURRENT GRADE:\t\t" + getGrade() + " (" + getPercentage() + "%)"
                + "\nCURRENT ATTENDANCE:\t" + getAttendance() + "%";
        return report;
    }
    
    //Getters
    
    public String getGrade() {
        if (this.percentage >= 90) {
            return "A";
        } else if (this.percentage >= 80) {
            return "B";
        } else if (this.percentage >= 70) {
            return "C";
        } else if (this.percentage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    public double getPercentage() {
        return percentage;
    }

    public double getAttendance() {
        return attendance;
    }
    
    //Setters

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public void setAttendance(double attendance) {
        this.attendance = attendance;
    }
    
}
