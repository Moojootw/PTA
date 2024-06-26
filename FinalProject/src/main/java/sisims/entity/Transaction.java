package sisims.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    private Long transactionItemCount;
    private Double transactionTotal;
    private String transactionDate;
    private String transactionDetails;
    
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = true)  //makes sure that the application is ok with employee_id (PK) is null in this table
    private Employee employee;
    
    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransactionItems> transactionItems;
}




