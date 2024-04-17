package sisims.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemId;
	private Long categoryId; //this is a foreign key
	private Long itemQuantity;
	private Double itemPrice;
	private Boolean itemIsFastSeller;
	private Long itemShelfLimit;
	private String itemName;
	private Boolean itemIsDiscontinued;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "categoryId")
	private Category category;
}
