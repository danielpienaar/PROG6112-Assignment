package question1;

import java.text.DecimalFormat;


public class ReportData {
    
    //Product detail variables
    private String code;
    private String name;
    private String warranty;
    private String category;
    private double price;
    private int stock;
    private String supplier;
    //Price formatter
    //Adapted from:
    //https://www.baeldung.com/java-decimalformat
    //Accessed 19 September 2021
    public static final DecimalFormat df = new DecimalFormat("###.00");
    
    //Constructor
    ReportData(String code, String name, String warranty, String category, double price, int stock, String supplier) {
        this.code = code;
        this.name = name;
        setWarranty(warranty);
        setCategory(category);
        this.price = price;
        this.stock = stock;
        this.supplier = supplier;
    }
    
    //Display the product report on the console
    public void displayReport() {
        System.out.println(""
                + "PRODUCT CODE:\t\t" + getCode()
                + "\nPRODUCT NAME:\t\t" + getName()
                + "\nPRODUCT WARRANTY:\t" + getWarranty()
                + "\nPRODUCT CATEGORY:\t" + getCategory()
                + "\nPRODUCT PRICE:\t\tR" + formatPrice(getPrice())
                + "\nPRODUCT STOCK LEVELS:\t" + getStock()
                + "\nPRODUCT SUPPLIER:\t" + getSupplier());
    }
    
    public String formatPrice(double price) {
        return df.format(price);
    }

    //Getters
    
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getWarranty() {
        return warranty;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getSupplier() {
        return supplier;
    }

    //Setters
    
    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public final void setWarranty(String warranty) {
        if (warranty.equals("1")) {
            this.warranty = "6 months";
        } else {
            this.warranty = "2 years";
        }
    }

    public final void setCategory(String category) {
        switch(category) {
            case "1":
                this.category = "Desktop Computer";
                break;
            case "2":
                this.category = "Laptop";
                break;
            case "3":
                this.category = "Tablet";
                break;
            case "4":
                this.category = "Printer";
                break;
            case "5":
                this.category = "Gaming Console";
                break;
        }
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
    
    
    
}
