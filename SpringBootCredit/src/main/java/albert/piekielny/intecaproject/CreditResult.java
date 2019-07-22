package albert.piekielny.intecaproject;

public class CreditResult {

    private String firstName;
    private String lastName;
    private String pesel;
    private String productName;
    private int productValue;
    private String creditName;

    public CreditResult(String firstName, String lastName, String pesel,
                        String productName, int productValue, String creditName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.productName = productName;
        this.productValue = productValue;
        this.creditName = creditName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductValue() {
        return productValue;
    }

    public void setProductValue(int productValue) {
        this.productValue = productValue;
    }

    public String getCreditName() {
        return creditName;
    }

    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }
}
