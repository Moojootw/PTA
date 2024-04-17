package sisims.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class TransactionItems {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;
	private Long employeeId; //FK
	private Long transactionItemCount;
	private Double transactionTotal;
	private String transactionDate;
	private String transactionDetails;
}
