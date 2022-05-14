package question2;


public class Person {
    
    private String name;
    private String surname;
    private String gender;
    private String id;
    private int classNum;
    
    public Person(String name, String surname, String gender, String id, int classNum) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.id = id;
        this.classNum = classNum;
    }
    
    public String report() {
        String report = getName() + " " + getSurname() + " - Person"
                + "\n==="
                + "\nGENDER:\t\t" + getGender()
                + "\nID:\t\t" + getId()
                + "\nCLASS:\t\tGroup " + getClassNum();
        return report;
    }

    //Getters
    
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
    
    public String getGender() {
        return gender;
    }
    
    public String getId() {
        return id;
    }
    
    public int getClassNum() {
        return classNum;
    }
    
    //Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
    }
}
