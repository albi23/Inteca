package albert.piekielny.intecaproject;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumn
    private int Id;

    @NotNull
    private String CreditName;

    public Credit(String creditName) {
        CreditName = creditName;
    }

    public Credit( ) {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCreditName() {
        return CreditName;
    }

    public void setCreditName(String creditName) {
        CreditName = creditName;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "Id=" + Id +
                ", CreditName='" + CreditName + '\'' +
                "}\n";
    }
}
