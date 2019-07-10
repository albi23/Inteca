package albert.piekielny.intecaproject;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

public class Product {

    private int creditId;
    private int value;
    private String productName;

    public Product() {
    }

    public Product(int creditId, int value, String productName) {
        this.creditId = creditId;
        this.value = value;
        this.productName = productName;
    }

    public int getCreditId() {
        return creditId;
    }

    public void setCreditId(int creditId) {
        this.creditId = creditId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "creditId=" + creditId +
                ", value=" + value +
                ", productName='" + productName + '\'' +
                "}\n";
    }
}
