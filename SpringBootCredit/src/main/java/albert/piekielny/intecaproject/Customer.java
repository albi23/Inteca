package albert.piekielny.intecaproject;

public class Customer {


    private int creditId;
    private String firstName;
    private String surname;
    private String pesel;

    public Customer(){

    }

    public Customer(int creditId, String firstName,  String surname, String pesel) {
        this.creditId = creditId;
        this.firstName = firstName;
        this.surname = surname;
        this.pesel = pesel;
    }

    public int getCreditId() {
        return creditId;
    }

    public void setCreditId(int creditId) {
        this.creditId = creditId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "creditId=" + creditId +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", pesel='" + pesel + '\'' +
                "}\n";
    }
}