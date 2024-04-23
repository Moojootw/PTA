package sisims.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;
	@Column(unique = true) //no duplcate category names
	private String categoryName;
	private String categoryDescription;
	@OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
	@ToString.Exclude
	private List<Item> items;
	
	@Transient
	//Custom
	public int getItemCountInCategory() {
		return items != null ? items.size() : 0; // returns items.size() or 0 depending on "is items not null ?"
	}
}
