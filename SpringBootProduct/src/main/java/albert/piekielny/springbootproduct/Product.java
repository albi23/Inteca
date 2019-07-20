package albert.piekielny.springbootproduct;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    @Transient
    private List<Product> products;

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
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
