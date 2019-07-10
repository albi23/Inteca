package albert.piekielny.springbootproduct;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Product {

    @Id
    @PrimaryKeyJoinColumn
    private int creditId;

    @NotNull
    private int value;
    @NotNull
    private String productName;

    public Product() {
    }

    public Product(int creditId, @NotNull int value, @NotNull String productName) {
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
